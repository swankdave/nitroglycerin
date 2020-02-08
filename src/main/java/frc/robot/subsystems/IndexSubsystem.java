package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IndexSubsystem extends SubsystemBase {

    private TalonSRX index_motor = new TalonSRX(Constants.index.BAG_ID);

    public IndexSubsystem() {
        index_motor.setNeutralMode(NeutralMode.Brake);
        index_motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 20);
        index_motor.selectProfileSlot(0, 0);
        index_motor.config_kP(0, Constants.index.P);
        index_motor.config_kI(0, Constants.index.I);
        index_motor.config_kD(0, Constants.index.D);
        index_motor.configClosedLoopPeakOutput(0, 1);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Index Velocity", counts_to_rpm(index_motor.getSelectedSensorVelocity()));
        SmartDashboard.putNumber("Index Error", counts_to_rpm(index_motor.getClosedLoopError()));
        SmartDashboard.putNumber("Index % Out", index_motor.getMotorOutputPercent());
    }

    public void run_at_percent(double speed) {
        index_motor.set(ControlMode.PercentOutput, speed);
    }

    public void run_at_rpm(int rpm) {
        index_motor.set(ControlMode.Velocity, rpm_to_counts(rpm));
    }

    public void neutral() {
        index_motor.neutralOutput();
    }

    public double counts_to_rpm(double counts) {
        return (counts / Constants.index.MAGIC_NUMBER) * 60;
    }

    public double rpm_to_counts(double rpm) {
        return (rpm / Constants.index.MAGIC_NUMBER) / 60;
    }
}
