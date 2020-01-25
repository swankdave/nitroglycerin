package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterTiltSubsystem extends SubsystemBase {

    private CANSparkMax tilt_motor = new CANSparkMax(Constants.shooter.TILT_MOTOR_ID, CANSparkMax.MotorType.kBrushless);
    private CANEncoder tilt_encoder = tilt_motor.getEncoder();

    public ShooterTiltSubsystem() {
        //Init code here
    }

    public void test_tilt(double percent_output) {
        tilt_motor.set(percent_output);
        SmartDashboard.putNumber("Azimuth RPM: ", tilt_encoder.getVelocity());
        SmartDashboard.putNumber("Azimuth % Out: ", tilt_motor.getOutputCurrent());
    }
}
