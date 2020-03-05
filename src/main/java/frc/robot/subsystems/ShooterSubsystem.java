package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.context.ShootingContext;

public class ShooterSubsystem extends SubsystemBase {

    private TalonFX top_shooter_motor = new TalonFX(Constants.shooter.TOP_MOTOR_ID);
    private TalonFX bottom_shooter_motor = new TalonFX(Constants.shooter.BOTTOM_MOTOR_ID);
    private boolean enabled = false;
    private ShootingContext shootingContext = ShootingContext.getInstance();
//    public double bottom_kP, bottom_kI, bottom_kD, bottom_kIz;
//    public double top_kP, top_kI, top_kD, top_kIz;


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
        top_shooter_motor.setInverted(false);
        bottom_shooter_motor.setInverted(true);
        top_shooter_motor.configClosedLoopPeakOutput(0, 0.8);
        bottom_shooter_motor.configClosedLoopPeakOutput(0, 1);
        //Open loop ramp rate
        top_shooter_motor.configOpenloopRamp(0);
        bottom_shooter_motor.configOpenloopRamp(0);

//        SmartDashboard.putNumber("Bottom Shooter P", 0.0);
//        SmartDashboard.putNumber("Top Shooter P", 0.0);
//        SmartDashboard.putNumber("Bottom Shooter I", 0.0);
//        SmartDashboard.putNumber("Top Shooter I", 0.0);
//        SmartDashboard.putNumber("Bottom Shooter D", 0.0);
//        SmartDashboard.putNumber("Top Shooter D", 0.0);
//        SmartDashboard.getNumber("Top Velocity", 0.0);
//        SmartDashboard.getNumber("Top Velocity", 0.0);

        SmartDashboard.putBoolean("Reset Encoders", false);

    }

//    public void test_shooter() {
//        double speed = 1;
//        top_shooter_motor.set(TalonFXControlMode.PercentOutput, speed * 0.8);
//        bottom_shooter_motor.set(TalonFXControlMode.PercentOutput, speed * 1.0);
////        basic_shooter_shoot();
//    }

    public void shoot_percent() {
        double speed = 1;
        top_shooter_motor.set(TalonFXControlMode.PercentOutput, speed * 0.5);
        bottom_shooter_motor.set(TalonFXControlMode.PercentOutput, speed * 1.0);
    }

    private void basic_shooter_shoot() {
//        top_shooter_motor.set(TalonFXControlMode.Velocity, rpm_to_count(250));
//        bottom_shooter_motor.set(TalonFXControlMode.Velocity, rpm_to_count(380));
//        top_shooter_motor.set(TalonFXControlMode.Velocity, rpm_to_count(475));
//        bottom_shooter_motor.set(TalonFXControlMode.Velocity, rpm_to_count(675));
        top_shooter_motor.set(TalonFXControlMode.Velocity, rpm_to_count(400));
        bottom_shooter_motor.set(TalonFXControlMode.Velocity, rpm_to_count(550));
    }

    @Override
    public void periodic() {
//        run_from_context();
        SmartDashboard.putNumber("Top % Out", top_shooter_motor.getMotorOutputPercent());
        SmartDashboard.putNumber("Top Shooter Velocity: ", counts_to_rpm(top_shooter_motor.getSelectedSensorVelocity()));
        SmartDashboard.putNumber("Top Shooter Error: ", counts_to_rpm(top_shooter_motor.getClosedLoopError()));
        SmartDashboard.putNumber("Bottom % Out", bottom_shooter_motor.getMotorOutputPercent());
        SmartDashboard.putNumber("Bottom Shooter Velocity: ", counts_to_rpm(bottom_shooter_motor.getSelectedSensorVelocity()));
        SmartDashboard.putNumber("Bottom Shooter Error: ", counts_to_rpm(bottom_shooter_motor.getClosedLoopError()));
//        double bottom_p = SmartDashboard.getNumber("Bottom Shooter P", 0.0);
//        double top_p = SmartDashboard.getNumber("Top Shooter P", 0.0);
//        double bottom_i = SmartDashboard.getNumber("Bottom Shooter I", 0.0);
//        double top_i = SmartDashboard.getNumber("Top Shooter I", 0.0);
//        double bottom_d = SmartDashboard.getNumber("Bottom Shooter D", 0.0);
//        double top_d = SmartDashboard.getNumber("Top Shooter D", 0.0);
//
//        if (bottom_d != bottom_kD) {
//
//        }
//
//        top_shooter_motor.config_kP(0, top_p);
//        top_shooter_motor.config_kI(0, top_i);
//        top_shooter_motor.config_kD(0, top_d);
//
//        bottom_shooter_motor.config_kP(0, bottom_p);
//        bottom_shooter_motor.config_kI(0, bottom_i);
//        bottom_shooter_motor.config_kD(0, bottom_d);


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

    public void run_at_rpms(double top_rpm, double bottom_rpm) {
        top_shooter_motor.set(TalonFXControlMode.Velocity, rpm_to_count(top_rpm));
        bottom_shooter_motor.set(TalonFXControlMode.Velocity, rpm_to_count(bottom_rpm));
    }

    public void shooter_neutral() {
        top_shooter_motor.neutralOutput();
        bottom_shooter_motor.neutralOutput();

    }

    public void test() {
        double top_velocity = SmartDashboard.getNumber("Top Velocity", 0.0);
        double bottom_velocity = SmartDashboard.getNumber("Top Velocity", 0.0);
        run_at_rpms(top_velocity, bottom_velocity);
    }

    public void rev() {
        top_shooter_motor.set(TalonFXControlMode.PercentOutput, 1 * 0.8);
        bottom_shooter_motor.set(TalonFXControlMode.PercentOutput, 1 * 1.0);
    }

//    public void enable() {
//        enabled = true;
//    }
//
//    public void disable() {
//        enabled = false;
//    }
//
//    public void run_from_context() {
//        if (enabled) {
//            double speed = shootingContext.get_shooter_speed();
//            top_shooter_motor.set(TalonFXControlMode.PercentOutput, speed * 0.5);
//            bottom_shooter_motor.set(TalonFXControlMode.PercentOutput, speed * 1.0);
//        } else {
//            top_shooter_motor.neutralOutput();
//            bottom_shooter_motor.neutralOutput();
//        }
//    }

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
