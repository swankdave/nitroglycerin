package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeLiftSubsystem extends SubsystemBase {

    public Servo intake_lift_servo = new Servo(Constants.intake_lift.SERVO_PORT);
    public Servo intake_lift_servo2 = new Servo(1);

    public IntakeLiftSubsystem(){
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Servo Pos: ", this.intake_lift_servo.get());
    }

    public void set_position(double position) {
        intake_lift_servo.set(position);
        intake_lift_servo2.set(position);
    }

    public void up() {
        intake_lift_servo.set(0.25);
        intake_lift_servo2.set(0.95);
    }

    public void down() {
        intake_lift_servo.set(1);
        intake_lift_servo2.set(0.13);
    }

    public void set_angle(double angle) {
        intake_lift_servo.setAngle(angle);
    }
}
