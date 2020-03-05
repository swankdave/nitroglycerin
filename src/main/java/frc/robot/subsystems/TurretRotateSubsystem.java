package frc.robot.subsystems;

import com.revrobotics.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.util.LimelightHandler;

import static java.lang.Math.abs;

public class TurretRotateSubsystem extends SubsystemBase {

    private CANSparkMax turret_azimuth = new CANSparkMax(Constants.turret_azimuth.MOTOR, CANSparkMaxLowLevel.MotorType.kBrushless);
    private CANEncoder turret_encoder = new CANEncoder(turret_azimuth, EncoderType.kHallSensor, 1);
    private CANPIDController turret_pid = turret_azimuth.getPIDController();
//    private double p = 0.06;
//    double i = 0.0;
//    double d = 0.001;

    private double p = 0.018;
    double i = 0.00003;
    double d = 0.005;

    private LimelightHandler limelight;

    double min_output = -0.8;
    double max_output = 0.8;
    private boolean moving_to_angle = false;
    private double moving_to_angle_setpoint = 0.0;

    public TurretRotateSubsystem(LimelightHandler limelight_temp) {
        limelight = limelight_temp;
        turret_azimuth.restoreFactoryDefaults();
        turret_azimuth.setIdleMode(CANSparkMax.IdleMode.kBrake);
        turret_azimuth.setInverted(true);
        turret_azimuth.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, 190);
        turret_azimuth.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
        turret_azimuth.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, -97);
        turret_azimuth.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);

        //@ToDo Verify the position conversion factor is return position as applied angles.
        turret_encoder.setPositionConversionFactor(5.33333333333333333333333333333333333333);
        turret_pid.setFF(0);
        turret_pid.setP(p);
        turret_pid.setI(i);
        turret_pid.setD(d);
        turret_pid.setIZone(0.7);
        turret_pid.setOutputRange(min_output, max_output);
        SmartDashboard.putBoolean("Reset Turret Pos", false);
        SmartDashboard.putBoolean("Is Auto Aiming", false);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Turret Rotate Pos: ", turret_encoder.getPosition());
        if (SmartDashboard.getBoolean("Reset Turret Pos", false)) {
            reset_encoder();
            SmartDashboard.putBoolean("Reset Turret Pos", false);
        }

        SmartDashboard.putNumber("Position Factor", turret_encoder.getPositionConversionFactor());
        SmartDashboard.putBoolean("Has Target:", limelight.has_target());
        SmartDashboard.putNumber("Distance To Target", limelight.get_distance());
    }

    public void reset_encoder() {
        turret_encoder.setPosition(0);
    }

    public void manual_control(double percent_out) {
        SmartDashboard.putBoolean("Is Auto Aiming", false);
        turret_azimuth.set(deadband_handler(percent_out * 0.3));
    }

    public double deadband_handler(double percent) {
        if (abs(percent) > 0.1) {
            return percent;
        }
        return 0.0;
    }

    public void pid_control(double rotations) {
        turret_pid.setReference(rotations, ControlType.kPosition);
    }

    public void angle_control(double input_angle, boolean has_target) {
        SmartDashboard.putBoolean("Is Auto Aiming", true);
        if (has_target) {
            double turret_angle = (double) turret_encoder.getPosition();
            if (moving_to_angle) {
                if (abs(abs(moving_to_angle_setpoint) - abs(turret_angle)) < 4) {
                    moving_to_angle = false;
                    moving_to_angle_setpoint = 0.0;
                }
            } else {
                double desired_angle = input_angle + turret_angle;
                if (desired_angle < -97) {
                    SmartDashboard.putNumber("Test Angle", desired_angle + 360);
//                motor_set_holder(desired_angle + 360);
//                moving_to_angle = true;
//                moving_to_angle_setpoint = desired_angle + 360;
                    motor_set_holder(-97);
                } else if (desired_angle > 190) {
                    SmartDashboard.putNumber("Test Angle", desired_angle - 360);
                    //desired_angle - 360
//                moving_to_angle = true;
//                moving_to_angle_setpoint = desired_angle - 360;
                    motor_set_holder(190);
                } else {
                    motor_set_holder(desired_angle);
                }
            }
        } else {
            manual_control(0);
        }
    }

    public void motor_set_holder(double angle) {
        pid_control(angle);
    }


}
