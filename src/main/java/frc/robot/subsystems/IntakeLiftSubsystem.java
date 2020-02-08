package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeLiftSubsystem extends SubsystemBase {

    public Servo intake_lift_servo = new Servo(Constants.intake_lift.SERVO_PORT);

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Intake Lift Angle", intake_lift_servo.getAngle());
        SmartDashboard.putNumber("Intake Lift Position", intake_lift_servo.get());
    }

    public void set_position(double position) {
        intake_lift_servo.set(position);
    }

    public void set_angle(double angle) {
        intake_lift_servo.setAngle(angle);
    }
}
