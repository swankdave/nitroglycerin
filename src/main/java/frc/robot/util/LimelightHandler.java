package frc.robot.util;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.context.ShootingContext;

public class LimelightHandler extends SubsystemBase {
    private double pipeline_index = 0;
    private static int LEDS_ON = 3;
    private static int LEDS_OFF = 1;
    private static int CLOSE_PIPELINE = 0;
    private static int FAR_PIPELINE = 1;
    private int led_mode = LEDS_ON;
    private static LimelightHandler instance;
    private ShootingContext shootingContext = ShootingContext.getInstance();

    public static LimelightHandler getInstance() {
        if (instance == null) {
            instance = new LimelightHandler();
        }

        return instance;
    }

    public boolean has_target() {
        double has_target = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
        return has_target == 1;
    }

    public double get_horizontal_offset() {
        if (has_target()) {
            return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        }
        return 0.0;
    }

    public void toggle_light() {
        if (led_mode == LEDS_ON) {
            led_mode = LEDS_OFF;
        } else {
            led_mode = LEDS_ON;
        }
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(led_mode);
    }

    public double get_vertical_offset() {
        if (has_target()) {
            return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
        }
        return 0.0;
    }

    public void set_pipeline(int pipeline) {
        if (pipeline_index == pipeline) {
            return;
        }
        pipeline_index = pipeline;
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(pipeline);
    }

    public void set_pipeline_from_context() {
        if (shootingContext.get_limelight_pipeline() == pipeline_index) {
            return;
        }
        pipeline_index = shootingContext.get_limelight_pipeline();
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(pipeline_index);
    }

    public double get_distance() {
        return (Constants.limelight.TARGET_HEIGHT_INCHES - Constants.limelight.LIMELIGHT_HEIGHT_INCHES) / Math.sin(Math.toRadians(Constants.limelight.DEGREES_FROM_HORIZONTAL + get_vertical_offset()));
    }

    public void setLedsOn() {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(LEDS_ON);
    }

    public void setLedsOff() {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(LEDS_OFF);
    }

    @Override
    public void periodic() {
        set_pipeline_from_context();
    }
}
