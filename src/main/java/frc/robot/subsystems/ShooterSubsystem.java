package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {

    private TalonFX top_shooter_motor = new TalonFX(Constants.shooter.TOP_MOTOR_ID);
    private TalonFX bottom_shooter_motor = new TalonFX(Constants.shooter.BOTTOM_MOTOR_ID);


    public ShooterSubsystem() {
        //Set Brake neutral mode.
        top_shooter_motor.setNeutralMode(NeutralMode.Brake);
        bottom_shooter_motor.setNeutralMode(NeutralMode.Brake);
        //Top shooter motor pid configs
        top_shooter_motor.selectProfileSlot(0, 0);
        top_shooter_motor.config_kP(0, Constants.shooter.pid_constants.top_motor.P);
        top_shooter_motor.config_kI(0, Constants.shooter.pid_constants.top_motor.I);
        top_shooter_motor.config_kD(0, Constants.shooter.pid_constants.top_motor.D);
        //Bottom shooter motor pid configs
        bottom_shooter_motor.selectProfileSlot(0, 0);
        bottom_shooter_motor.config_kP(0, Constants.shooter.pid_constants.bottom_motor.P);
        bottom_shooter_motor.config_kI(0, Constants.shooter.pid_constants.bottom_motor.I);
        bottom_shooter_motor.config_kD(0, Constants.shooter.pid_constants.bottom_motor.D);
    }

    public void test_shooter() {
        double speed = 0.58;
        top_shooter_motor.set(TalonFXControlMode.PercentOutput, -speed * 0.75);
        bottom_shooter_motor.set(TalonFXControlMode.PercentOutput, speed * 1.0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Top Shooter Velocity: ", top_shooter_motor.getSelectedSensorVelocity());
        SmartDashboard.putNumber("Top Shooter Output Current: (amps)", top_shooter_motor.getStatorCurrent());
        SmartDashboard.putNumber("Bottom Shooter Output Current: (amps)", bottom_shooter_motor.getStatorCurrent());
        SmartDashboard.putNumber("Bottom Shooter Velocity: ", bottom_shooter_motor.getSelectedSensorVelocity());
    }

    public void run_at_rpm(int rpm) {
        top_shooter_motor.set(TalonFXControlMode.Velocity, -rpm);
        bottom_shooter_motor.set(TalonFXControlMode.Velocity, rpm);
    }

    public void shooter_neutral() {
        top_shooter_motor.neutralOutput();
        bottom_shooter_motor.neutralOutput();

    }
}