package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterTiltSubsystem;
import frc.robot.util.LimelightHandler;

public class ShooterTiltToAngle extends CommandBase {

    private ShooterTiltSubsystem m_shooterTiltSubsystem;
    private LimelightHandler limelightHandler = LimelightHandler.getInstance();
    private double m_angle;

    public ShooterTiltToAngle(ShooterTiltSubsystem shooterTiltSubsystem, double angle) {
        addRequirements(shooterTiltSubsystem);
        m_shooterTiltSubsystem = shooterTiltSubsystem;
        m_angle = angle;
    }

    @Override
    public void initialize() {
        if (m_angle >= 15) {
            limelightHandler.set_pipeline(1);
        } else {
            limelightHandler.set_pipeline(0);
        }
    }

    @Override
    public void execute() {
        m_shooterTiltSubsystem.pid(m_angle);
    }

    @Override
    public void end(boolean interrupted) {
        m_shooterTiltSubsystem.manual_control(0);
    }
}
