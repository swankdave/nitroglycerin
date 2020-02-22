/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.RevShooterCommandGroup;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Constants.joystick;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final DrivetrainSubsystem drivetrain_subsystem = new DrivetrainSubsystem();
  private ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  private IndexSubsystem indexSubsystem = new IndexSubsystem();
  private IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private PreShooterStageSubsystem preShooterStageSubsystem = new PreShooterStageSubsystem();
  private IntakeLiftSubsystem intakeLiftSubsystem = new IntakeLiftSubsystem();
  private ShooterTiltSubsystem shooterTiltSubsystem = new ShooterTiltSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final RevShooterCommandGroup rev_shooter_command = new RevShooterCommandGroup(shooterSubsystem, indexSubsystem, preShooterStageSubsystem);

  public Joystick left_joy = new Joystick(joystick.LEFT_JOY_ID);
  public Joystick right_joy = new Joystick(joystick.RIGHT_JOY_ID);
  private Joystick button_monkey = new Joystick(joystick.BUTTON_MONKEY);

  private JoystickButton run_intake = new JoystickButton(left_joy, 1);
  private JoystickButton lift_intake = new JoystickButton(right_joy, 1);
  private JoystickButton run_shooter = new JoystickButton(button_monkey, 6);
  private JoystickButton rev_shooter = new JoystickButton(button_monkey, 1);
  private JoystickButton back_button = new JoystickButton(button_monkey, 4);

  private JoystickButton pre_and_shooter = new JoystickButton(left_joy, 2);

  private JoystickButton shooter_tilt_test = new JoystickButton(right_joy, 6);

  private JoystickButton cancel_rev = new JoystickButton(button_monkey, 3);

  private JoystickButton index_test_button = new JoystickButton(left_joy, 9);





  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    shooterSubsystem.setDefaultCommand(new RunCommand(() -> shooterSubsystem.shooter_neutral(), shooterSubsystem));
    indexSubsystem.setDefaultCommand(new RunCommand(() -> indexSubsystem.neutral(), indexSubsystem));
    preShooterStageSubsystem.setDefaultCommand(new RunCommand(() -> preShooterStageSubsystem.run_at_percent(0), preShooterStageSubsystem));
    intakeSubsystem.setDefaultCommand(new RunCommand(() -> intakeSubsystem.neutral_intake(), intakeSubsystem));
    intakeLiftSubsystem.setDefaultCommand(new RunCommand(() -> intakeLiftSubsystem.set_position(0.3), intakeLiftSubsystem));
    shooterTiltSubsystem.setDefaultCommand(new RunCommand(() -> shooterTiltSubsystem.manual_control(button_monkey.getRawAxis(1)), shooterTiltSubsystem));
    drivetrain_subsystem.setDefaultCommand(new RunCommand(() -> drivetrain_subsystem.drive(drivetrain_subsystem.deadband_handler(left_joy.getY() * Constants.drivetrain.SPEED_MULTIPLIER), drivetrain_subsystem.deadband_handler(right_joy.getY() * Constants.drivetrain.SPEED_MULTIPLIER)), drivetrain_subsystem));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    run_intake
            .whenHeld(
                    new ParallelCommandGroup(
                            new RunCommand(() -> intakeSubsystem.run_intake(0.4), intakeSubsystem),
                            new RunCommand(() -> indexSubsystem.run_at_percent(0.5), indexSubsystem),
                            new RunCommand(() -> preShooterStageSubsystem.run_at_percent(0), preShooterStageSubsystem)
                          ));
//    index_test_button.whenHeld(new RunCommand(() -> indexSubsystem.run_at_percent(0.25), indexSubsystem), true);
    rev_shooter.whenPressed(rev_shooter_command);
    cancel_rev.cancelWhenPressed(rev_shooter_command);
    run_shooter
            .whileHeld(
                    new ParallelCommandGroup(
                            new RunCommand(() -> indexSubsystem.run_at_percent(0.5), indexSubsystem),
                            new RunCommand(() -> preShooterStageSubsystem.run_at_percent(1), preShooterStageSubsystem),
                            new RunCommand(() -> shooterSubsystem.test_shooter(), shooterSubsystem)));

    back_button.whenHeld(new ParallelCommandGroup(
            new RunCommand(() -> indexSubsystem.run_at_percent(-0.7), indexSubsystem)
//            new RunCommand(() -> preShooterStageSubsystem.run_at_percent(-1), preShooterStageSubsystem)
    ));

    pre_and_shooter.whenHeld(new ParallelCommandGroup(
            new RunCommand(() -> shooterSubsystem.test_shooter(), shooterSubsystem),
            new RunCommand(() -> preShooterStageSubsystem.run_at_percent(1), preShooterStageSubsystem)
    ));

    lift_intake.toggleWhenPressed(new RunCommand(() -> intakeLiftSubsystem.set_position(1), intakeLiftSubsystem));

    shooter_tilt_test.whenHeld(new RunCommand(() -> shooterTiltSubsystem.pid(10)));

  }




  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
