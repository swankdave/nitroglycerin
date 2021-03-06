package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.*;
import frc.robot.util.LimelightHandler;

public class BasicAuto extends SequentialCommandGroup {

    private LimelightHandler limelight = LimelightHandler.getInstance();

    public BasicAuto(
            TurretRotateSubsystem turretRotateSubsystem,
            ShooterSubsystem shooterSubsystem,
            DrivetrainSubsystem drivetrainSubsystem,
            IndexSubsystem indexSubsystem,
            PreShooterStageSubsystem preShooterStageSubsystem,
            ShooterTiltSubsystem shooterTiltSubsystem
    ) {
        addCommands(

                deadline(
                        new WaitCommand(.3),
                        new RunCommand(() -> turretRotateSubsystem.pid_control(90), turretRotateSubsystem)
                ),

                //Rev the shooter and aim for 2.5 seconds
                deadline(
                        new WaitCommand(2.5),
                        new RunCommand(shooterSubsystem::rev, shooterSubsystem),
                        new ShooterTiltToAngle(shooterTiltSubsystem, -2),
                        new TurretRotateToLimelight(
                            turretRotateSubsystem,
                            limelight
                        )
                ),

                // Shoot for 5 seconds
                deadline(
                        new WaitCommand(2),
                        new RunCommand(() -> indexSubsystem.run_at_percent(0.7), indexSubsystem),
                        new RunCommand(() -> preShooterStageSubsystem.run_at_percent(1), preShooterStageSubsystem),
                        new RunCommand(shooterSubsystem::shoot_percent, shooterSubsystem),
                        new ShooterTiltToAngle(shooterTiltSubsystem, -2),
                        new TurretRotateToLimelight(
                                turretRotateSubsystem,
                                limelight
                        )
                ),

                // Drive backward(?) for 3 seconds at 40% speed
                deadline(
                        new WaitCommand(1),
                        new RunCommand(() -> drivetrainSubsystem.drive(-0.4, -0.4), drivetrainSubsystem)
                )
        );



    }
}
