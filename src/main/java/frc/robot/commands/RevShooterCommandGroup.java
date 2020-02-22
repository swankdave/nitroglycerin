package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.IndexSubsystem;
import frc.robot.subsystems.PreShooterStageSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class RevShooterCommandGroup extends ParallelCommandGroup {

    public RevShooterCommandGroup(ShooterSubsystem shooter, IndexSubsystem indexSubsystem, PreShooterStageSubsystem preShooterStageSubsystem) {
        addCommands(
                new RunCommand(shooter::test_shooter, shooter)
//                new RunCommand(() -> indexSubsystem.run_at_percent(-1), indexSubsystem).withTimeout(0.25),
//                new RunCommand(() -> preShooterStageSubsystem.run_at_percent(0.6), preShooterStageSubsystem).withTimeout(0.25)
        );

    }
}
