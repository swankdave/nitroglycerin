package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShootingContextWrapperSubsystem;

public class ShootingContextCommand extends CommandBase {

    private ShootingContextWrapperSubsystem m_shootingContextWrapperSubsystem;
    private int m_context_to_set;

    public ShootingContextCommand(ShootingContextWrapperSubsystem shootingContextWrapperSubsystem, int context_to_set) {
        m_shootingContextWrapperSubsystem = shootingContextWrapperSubsystem;
        m_context_to_set = context_to_set;
        addRequirements(shootingContextWrapperSubsystem);
    }

    @Override
    public void initialize() {
        m_shootingContextWrapperSubsystem.low_level_update(m_context_to_set);
    }

    @Override
    public void end(boolean interrupted) {
        if (!interrupted) {
            m_shootingContextWrapperSubsystem.set_not_shooting();
        }
    }
}
