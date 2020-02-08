package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PreShooterStageSubsystem extends SubsystemBase {

    public TalonSRX stage_motor = new TalonSRX(Constants.pre_shooter_stage.BAG_ID);

    public PreShooterStageSubsystem() {
        stage_motor.setNeutralMode(NeutralMode.Brake);
        stage_motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 20);
        stage_motor.selectProfileSlot(0, 0);
        stage_motor.config_kP(0, Constants.pre_shooter_stage.P);
        stage_motor.config_kI(0, Constants.pre_shooter_stage.I);
        stage_motor.config_kD(0, Constants.pre_shooter_stage.D);
        stage_motor.configClosedLoopPeakOutput(0, 1);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Index Velocity", counts_to_rpm(stage_motor.getSelectedSensorVelocity()));
        SmartDashboard.putNumber("Index Error", counts_to_rpm(stage_motor.getClosedLoopError()));
        SmartDashboard.putNumber("Index % Out", stage_motor.getMotorOutputPercent());
    }

    public void run_at_percent(double percent) {
        stage_motor.set(ControlMode.PercentOutput, percent);
    }

    public void run_at_rpm(int rpm) {
        stage_motor.set(ControlMode.Velocity, rpm_to_counts(rpm));
    }

    public double counts_to_rpm(double counts) {
        return (counts / Constants.pre_shooter_stage.MAGIC_NUMBER) * 60;
    }

    public double rpm_to_counts(double rpm) {
        return (rpm / Constants.pre_shooter_stage.MAGIC_NUMBER) / 60;
    }
}
