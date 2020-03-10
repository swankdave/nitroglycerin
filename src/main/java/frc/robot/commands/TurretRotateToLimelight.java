package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretRotateSubsystem;
import frc.robot.util.LimelightHandler;

public class TurretRotateToLimelight extends CommandBase {

    private TurretRotateSubsystem m_turretRotateSubsystem;
    private LimelightHandler m_limelightHandler;

    public TurretRotateToLimelight (
        TurretRotateSubsystem turretRotateSubsystem,
        LimelightHandler limelightHandler
    ) {
        m_limelightHandler = limelightHandler;
        m_turretRotateSubsystem = turretRotateSubsystem;
        addRequirements(turretRotateSubsystem);
    }

    @Override
    public void initialize() {
//        m_limelightHandler.setLedsOn();
    }

    @Override
    public void execute() {
        m_turretRotateSubsystem.angle_control(m_limelightHandler.get_horizontal_offset(), m_limelightHandler.has_target());
    }

    @Override
    public void end(boolean interrupted) {
//        m_limelightHandler.setLedsOff();
    }
}
