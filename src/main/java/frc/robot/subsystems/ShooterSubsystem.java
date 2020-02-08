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
        bottom_shooter_motor.setSelectedSensorPosition(0);
        top_shooter_motor.setSelectedSensorPosition(0);
        top_shooter_motor.setInverted(true);
        top_shooter_motor.configClosedLoopPeakOutput(0, 0.65);
        bottom_shooter_motor.configClosedLoopPeakOutput(0, 0.80);
        SmartDashboard.putBoolean("Reset Encoders", false);

    }

    public void test_shooter() {
//        double speed = 0.58;
//        top_shooter_motor.set(TalonFXControlMode.PercentOutput, speed * 0.75);
//        bottom_shooter_motor.set(TalonFXControlMode.PercentOutput, speed * 1.0);
        basic_shooter_shoot();
    }

    private void basic_shooter_shoot() {
//        top_shooter_motor.set(TalonFXControlMode.Velocity, rpm_to_count(250));
//        bottom_shooter_motor.set(TalonFXControlMode.Velocity, rpm_to_count(380));
        top_shooter_motor.set(TalonFXControlMode.Velocity, rpm_to_count(225));
        bottom_shooter_motor.set(TalonFXControlMode.Velocity, rpm_to_count(425));
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Top % Out", top_shooter_motor.getMotorOutputPercent());
        SmartDashboard.putNumber("Top Shooter Position:", counts_to_revolutions(top_shooter_motor.getSelectedSensorPosition()));
        SmartDashboard.putNumber("Top Shooter Velocity: ", counts_to_rpm(top_shooter_motor.getSelectedSensorVelocity()));
        SmartDashboard.putNumber("Top Shooter Error: ", counts_to_rpm(top_shooter_motor.getClosedLoopError()));
        SmartDashboard.putNumber("Bottom % Out", bottom_shooter_motor.getMotorOutputPercent());
        SmartDashboard.putNumber("Bottom Shooter Position:", counts_to_revolutions(bottom_shooter_motor.getSelectedSensorPosition()));
        SmartDashboard.putNumber("Bottom Shooter Velocity: ", counts_to_rpm(bottom_shooter_motor.getSelectedSensorVelocity()));
        SmartDashboard.putNumber("Bottom Shooter Error: ", counts_to_rpm(bottom_shooter_motor.getClosedLoopError()));
        reset_encoders_method();
    }

    private void reset_encoders_method() {
        boolean reset_encoders = SmartDashboard.getBoolean("Reset Encoders", false);
        if (reset_encoders) {
            top_shooter_motor.setSelectedSensorPosition(0);
            bottom_shooter_motor.setSelectedSensorPosition(0);
            SmartDashboard.putBoolean("Reset Encoders", false);
        }
    }

    public void run_at_rpm(int rpm) {
        top_shooter_motor.set(TalonFXControlMode.Velocity, -rpm);
        bottom_shooter_motor.set(TalonFXControlMode.Velocity, rpm);
    }

    public void shooter_neutral() {
        top_shooter_motor.neutralOutput();
        bottom_shooter_motor.neutralOutput();

    }

    public double rpm_to_count(double rpm) {
        return (rpm * 2048) / 60;
    }

    public double revolutions_to_counts(double revolutions) {
        return revolutions * 2048;
    }

    private double counts_to_rpm(double counts) {
        return (counts / 2048) * 60;
    }

    private double counts_to_revolutions(double counts) {
        return counts / 2048;
    }
}
