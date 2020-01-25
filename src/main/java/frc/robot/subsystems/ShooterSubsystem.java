package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
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
        //Add any init code here, pid values etc
        top_shooter_motor.setNeutralMode(NeutralMode.Brake);
        bottom_shooter_motor.setNeutralMode(NeutralMode.Brake);
    }

    public void test_shooter_bottom(double percent_output) {
        top_shooter_motor.set(ControlMode.PercentOutput, percent_output);
        bottom_shooter_motor.set(TalonFXControlMode.PercentOutput, percent_output);
        SmartDashboard.putNumber("Top Shooter Velocity: ", top_shooter_motor.getSelectedSensorVelocity());
        SmartDashboard.putNumber("Top Shooter Output Current: (amps)", top_shooter_motor.getStatorCurrent());
        SmartDashboard.putNumber("Bottom Shooter Output Current: (amps)", bottom_shooter_motor.getStatorCurrent());
        SmartDashboard.putNumber("Bottom Shooter Velocity: ", bottom_shooter_motor.getSelectedSensorVelocity());
    }

}
