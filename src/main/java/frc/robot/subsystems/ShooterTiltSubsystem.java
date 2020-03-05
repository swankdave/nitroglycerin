package frc.robot.subsystems;

import com.revrobotics.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.context.ShootingContext;

import static java.lang.Math.abs;

public class ShooterTiltSubsystem extends SubsystemBase {

    private CANSparkMax tilt_motor = new CANSparkMax(Constants.shooter.TILT_MOTOR_ID, CANSparkMax.MotorType.kBrushless);
    private CANEncoder tilt_encoder = new CANEncoder(tilt_motor, EncoderType.kHallSensor, 42);
    private CANPIDController tilt_pid = tilt_motor.getPIDController();
//    private double p = 0.08;
//    double i = 0.015;
//    double d = 0.0;
    double p = 0.04;
    double i = 0.015;
    double d = 0;

    double min_output = -0.75;
    double max_output = 0.75;
    private boolean enabled = false;
    private ShootingContext shootingContext = ShootingContext.getInstance();

    public ShooterTiltSubsystem() {
        tilt_motor.restoreFactoryDefaults();
        tilt_motor.setIdleMode(CANSparkMax.IdleMode.kCoast);
        tilt_motor.setInverted(true);
        tilt_pid.setFF(0);
        tilt_pid.setP(p);
        tilt_pid.setI(i);
        tilt_pid.setD(d);
        tilt_pid.setIZone(1.5);
        tilt_pid.setOutputRange(min_output, max_output);
        SmartDashboard.putBoolean("Reset Azimuth Enc", false);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Azimuth RPM: ", tilt_encoder.getVelocity());
        SmartDashboard.putNumber("Azimuth Pos: ", tilt_encoder.getPosition());
        SmartDashboard.putNumber("Azimuth % Out: ", tilt_motor.getOutputCurrent());
        if (SmartDashboard.getBoolean("Reset Azimuth Enc", false)) {
            tilt_encoder.setPosition(0);
            SmartDashboard.putBoolean("Reset Azimuth Enc", false);
        }
    }

    public void manual_control(double percent_out) {
        tilt_motor.set(deadband_handler(0.25 * -percent_out));
    }

    public double deadband_handler(double speed) {
        if (abs(speed) > 0.1) {
            return speed;
        } else {
            return 0.0;
        }

    }

    public void close_shooting() {
        final double LOW_SETPOINT = -53.61;
        pid(LOW_SETPOINT);
    }

    public void initiation_line_shooting() {
        final double INIT_SETPOINT = 0;
        pid(INIT_SETPOINT);
    }

    public void trench_corner_shooting() {
        final double TRENCH_CORNER_SETPOINT = 10.313;
        pid(TRENCH_CORNER_SETPOINT);
    }
//
//    public void long_send_shooting() {
//        final double LONG_SEND_SETPOINT = 0;
//        pid(LONG_SEND_SETPOINT);
//    }

    public void pid(double rotations) {
        tilt_pid.setReference(rotations, ControlType.kPosition);
    }

}
