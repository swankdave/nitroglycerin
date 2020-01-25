package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterTiltSubsystem extends SubsystemBase {

    private CANSparkMax azimuth_motor = new CANSparkMax(Constants.shooter.AZIMUTH_MOTOR_ID, CANSparkMax.MotorType.kBrushless);
    private CANEncoder azimuth_encoder = azimuth_motor.getEncoder();

    public ShooterTiltSubsystem() {
        //Init code here
    }

    public void test_azimuth(double percent_output) {
        azimuth_motor.set(percent_output);
        SmartDashboard.putNumber("Azimuth RPM: ", azimuth_encoder.getVelocity());
        SmartDashboard.putNumber("Azimuth % Out: ", azimuth_motor.getOutputCurrent());
    }
}
