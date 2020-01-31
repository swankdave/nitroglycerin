package frc.robot.util;

import frc.robot.Constants;

public class Distance {

    public double get_distance(double y_offset_angle) {
        return (Constants.limelight.TARGET_HEIGHT_INCHES - Constants.limelight.LIMELIGHT_HEIGHT_INCHES) / Math.sin(Math.toRadians(Constants.limelight.DEGREES_FROM_HORIZONTAL + y_offset_angle));
    }

}
