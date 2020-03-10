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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Constants.joystick;
import frc.robot.util.LimelightHandler;

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
  private LimelightHandler limelight = LimelightHandler.getInstance();
  private ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  private IndexSubsystem indexSubsystem = new IndexSubsystem();
  private IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private PreShooterStageSubsystem preShooterStageSubsystem = new PreShooterStageSubsystem();
  private IntakeLiftSubsystem intakeLiftSubsystem = new IntakeLiftSubsystem();
  private ShooterTiltSubsystem shooterTiltSubsystem = new ShooterTiltSubsystem();
  private TurretRotateSubsystem turretRotateSubsystem = new TurretRotateSubsystem(limelight);
  private WinchSubsystem winchSubsystem = new WinchSubsystem();
  private ArmsReleaserSubsytem armsReleaserSubsytem = new ArmsReleaserSubsytem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private final RunCommand rev_shooter_command = new RunCommand(() -> shooterSubsystem.rev(), shooterSubsystem);

  private final ParallelCommandGroup backwards = new ParallelCommandGroup(
          new RunCommand(() -> indexSubsystem.run_at_percent(-1), indexSubsystem),
          new RunCommand(() -> preShooterStageSubsystem.run_at_percent(-1), preShooterStageSubsystem)
  );

  private final ParallelCommandGroup intake_down_and_run = new ParallelCommandGroup(
          new RunCommand(() -> intakeLiftSubsystem.down(), intakeLiftSubsystem),
          new RunCommand(() -> intakeSubsystem.run_intake(Constants.intake.INTAKE_SPEED))
  );

  public Joystick left_joy = new Joystick(joystick.LEFT_JOY_ID);
  public Joystick right_joy = new Joystick(joystick.RIGHT_JOY_ID);
  private Joystick button_monkey = new Joystick(joystick.BUTTON_MONKEY);

  private JoystickButton run_intake = new JoystickButton(left_joy, 2);
  private JoystickButton lift_intake = new JoystickButton(left_joy, 1);
  private JoystickButton run_shooter = new JoystickButton(button_monkey, 6);
  private JoystickButton rev_shooter = new JoystickButton(button_monkey, 7);
  private JoystickButton just_intake = new JoystickButton(left_joy, 3);
  private JoystickButton shooter_aim = new JoystickButton(button_monkey, 10);
  private JoystickButton backwards_button = new JoystickButton(button_monkey, 5);
  private JoystickButton close_shot_button = new JoystickButton(button_monkey, 4);
  private JoystickButton trench_corner_button = new JoystickButton(button_monkey, 2);
  private JoystickButton init_line_button = new JoystickButton(button_monkey, 3);
  private JoystickButton trench_back_button = new JoystickButton(button_monkey, 1);
  private JoystickButton winch_extend = new JoystickButton(right_joy, 3);
  private JoystickButton winch_fast_retract = new JoystickButton(right_joy, 4);
  private JoystickButton winch_slow_retract = new JoystickButton(right_joy, 6);
  private JoystickButton full_speed = new JoystickButton(right_joy, 1);
  private JoystickButton intake_backwards = new JoystickButton(left_joy, 4);
  private JoystickButton punt_shooter = new JoystickButton(right_joy, 5);
  private JoystickButton servo_open = new JoystickButton(right_joy, 11);







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
    intakeLiftSubsystem.setDefaultCommand(new RunCommand(() -> intakeLiftSubsystem.up(), intakeLiftSubsystem));
    shooterTiltSubsystem.setDefaultCommand(new RunCommand(() -> shooterTiltSubsystem.manual_control(button_monkey.getRawAxis(1)), shooterTiltSubsystem));
    turretRotateSubsystem.setDefaultCommand(new RunCommand(() -> turretRotateSubsystem.manual_control(button_monkey.getRawAxis(4)), turretRotateSubsystem));
    winchSubsystem.setDefaultCommand(new RunCommand(() -> winchSubsystem.neutral_output(), winchSubsystem));
    armsReleaserSubsytem.setDefaultCommand(new RunCommand(() -> armsReleaserSubsytem.set_position(0.1), armsReleaserSubsytem));
    drivetrain_subsystem.setDefaultCommand(new RunCommand(() -> drivetrain_subsystem.drive(drivetrain_subsystem.deadband_handler(drivetrain_subsystem.square_joysticks(left_joy.getY() * 0.75)), drivetrain_subsystem.deadband_handler(drivetrain_subsystem.square_joysticks(right_joy.getY() * 0.75))), drivetrain_subsystem));
    SmartDashboard.putBoolean("Score In Auto", true);
//    limelight.setLedsOff();
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
                            new RunCommand(() -> indexSubsystem.run_at_percent(0.7), indexSubsystem),
                            new RunCommand(() -> preShooterStageSubsystem.run_at_percent(0), preShooterStageSubsystem)
    ));

    rev_shooter.whenPressed(rev_shooter_command);

    run_shooter
            .whileHeld(
                    new ParallelCommandGroup(
                            new RunCommand(() -> indexSubsystem.run_at_percent(0.7), indexSubsystem),
                            new RunCommand(() -> preShooterStageSubsystem.run_at_percent(1), preShooterStageSubsystem),
                            new RunCommand(() -> shooterSubsystem.shoot_percent(), shooterSubsystem)
    ));

    backwards_button.whileHeld(backwards);

    just_intake.whenHeld(new RunCommand(() -> intakeSubsystem.run_intake(1), intakeSubsystem));

    lift_intake.whileHeld(intake_down_and_run);

//    shooter_aim.toggleWhenPressed(new RunCommand(() -> turretRotateSubsystem.angle_control(limelight.get_horizontal_offset(), limelight.has_target()), turretRotateSubsystem));
    shooter_aim.toggleWhenPressed(new TurretRotateToLimelight(
            turretRotateSubsystem,
            limelight
    ));

    close_shot_button.whenPressed(new ShooterTiltToAngle(shooterTiltSubsystem, -53.61));

    init_line_button.whenPressed(new ShooterTiltToAngle(shooterTiltSubsystem, -2));

    trench_corner_button.whenPressed(new ShooterTiltToAngle(shooterTiltSubsystem, 12.35));

    trench_back_button.whenPressed(new ShooterTiltToAngle(shooterTiltSubsystem, 14.15));

    winch_extend.whenHeld(new ParallelCommandGroup(
            new RunCommand(() -> winchSubsystem.extend(), winchSubsystem),
            new RunCommand(() -> armsReleaserSubsytem.set_position(1), armsReleaserSubsytem),
            new RunCommand(() -> turretRotateSubsystem.pid_control(120), turretRotateSubsystem)
    ));

    winch_fast_retract.whenHeld(new ParallelCommandGroup(
            new RunCommand(() -> winchSubsystem.retract_powerful(), winchSubsystem),
            new RunCommand(() -> turretRotateSubsystem.pid_control(120), turretRotateSubsystem)
    ));

    winch_slow_retract.whenHeld(new ParallelCommandGroup(
            new RunCommand(() -> winchSubsystem.retract_slow(), winchSubsystem),
            new RunCommand(() -> turretRotateSubsystem.pid_control(120), turretRotateSubsystem)
    ));

    full_speed.whileHeld(new RunCommand(() -> drivetrain_subsystem.drive(drivetrain_subsystem.deadband_handler(drivetrain_subsystem.square_joysticks(left_joy.getY() * 1)), drivetrain_subsystem.deadband_handler(drivetrain_subsystem.square_joysticks(right_joy.getY() * 1))), drivetrain_subsystem));

    intake_backwards.whileHeld(new RunCommand(() -> intakeSubsystem.run_intake(-1), intakeSubsystem));

    punt_shooter.whileHeld(
            new ParallelCommandGroup(
                    new RunCommand(() -> shooterSubsystem.baby_shot(), shooterSubsystem),
                    new RunCommand(() -> indexSubsystem.run_at_percent(0.7), indexSubsystem),
                    new RunCommand(() -> preShooterStageSubsystem.run_at_percent(1), preShooterStageSubsystem)
            )
    );

    servo_open.whileHeld(new RunCommand(() -> armsReleaserSubsytem.set_position(1), armsReleaserSubsytem));

  }




  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    if (SmartDashboard.getBoolean("Score In Auto", false)) {
      return new BasicAuto(
              turretRotateSubsystem,
              shooterSubsystem,
              drivetrain_subsystem,
              indexSubsystem,
              preShooterStageSubsystem,
              shooterTiltSubsystem);
    } else {
      return new DriveOnlyAuto(
              drivetrain_subsystem
      );
    }

//      return new Enginenerds(
//               shooterTiltSubsystem,
//               shooterSubsystem,
//               drivetrain_subsystem,
//               indexSubsystem,
//               preShooterStageSubsystem
//      );
    }

}
