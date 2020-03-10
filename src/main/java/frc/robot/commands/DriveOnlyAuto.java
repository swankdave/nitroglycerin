package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DriveOnlyAuto extends SequentialCommandGroup {

    public DriveOnlyAuto (
            DrivetrainSubsystem drivetrainSubsystem
    ) {
        addCommands(
                new ParallelDeadlineGroup(
                        new WaitCommand(1),
                        new RunCommand(() -> drivetrainSubsystem.drive(-0.4, -0.4), drivetrainSubsystem)
                )
        );

    }
}
