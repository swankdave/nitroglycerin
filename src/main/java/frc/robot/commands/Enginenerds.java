package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.*;

public class Enginenerds extends SequentialCommandGroup {

    public Enginenerds(
            ShooterTiltSubsystem shooterTiltSubsystem,
            ShooterSubsystem shooterSubsystem,
            DrivetrainSubsystem drivetrainSubsystem,
            IndexSubsystem indexSubsystem,
            PreShooterStageSubsystem preShooterStageSubsystem
    ) {
        addCommands(
                new ParallelDeadlineGroup(
                        new WaitCommand(1.5),
                        new RunCommand(shooterSubsystem::baby_shot_rev, shooterSubsystem),
                        new RunCommand(() -> shooterTiltSubsystem.pid(-41.7), shooterTiltSubsystem)
                ),

                new ParallelDeadlineGroup(
                    new WaitCommand(2),
                    new RunCommand(() -> indexSubsystem.run_at_percent(0.7), indexSubsystem),
                    new RunCommand(() -> preShooterStageSubsystem.run_at_percent(1), preShooterStageSubsystem),
                    new RunCommand(() -> shooterSubsystem.baby_shot(), shooterSubsystem)
                ),

                new ParallelDeadlineGroup(
                    new WaitCommand(2),
                    new RunCommand(() -> drivetrainSubsystem.drive(-0.4, -0.4), drivetrainSubsystem)
                )
        );
    }

}
