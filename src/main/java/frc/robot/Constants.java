/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class drivetrain {
        public static final int LEFT_MASTER_ID = 10;
        public static final int RIGHT_MASTER_ID = 11;
        public static final int LEFT_SLAVE_ID = 12;
        public static final int RIGHT_SLAVE_ID = 13;
        public static final double SPEED_MULTIPLIER = 1.0;
    }

    public static final class joystick {
        public static final int LEFT_JOY_ID = 0;
        public static final int RIGHT_JOY_ID = 1;
        public static final int BUTTON_MONKEY = 2;
    }

    public static final class shooter {
        public static final class pid_constants {
            public static final class top_motor {
                //1 AL covered stealth
//                public static final double P = 0.17;
//                public static final double I = 0.0;
//                public static final double D = 0.0018;
                //1 Fairlane
//                public static final double P = 0.305;
//                public static final double I = 0.00018;
//                public static final double D = 0.001;
                //2 Fairlanes
//                public static final double P = 0.6;
//                public static final double I = 0.00016;
//                public static final double D = 0.0012;
                //1 Custom AL wheel
                public static final double P = 0.3;
                public static final double I = 0.0;
                public static final double D = 0.0006;
            }
            public static final class bottom_motor {
                //1 AL covered stealth
//                public static final double P = 0.20;
//                public static final double I = 0.0;
//                public static final double D = 0.0018;
                //1 Fairlane
//                public static final double P = 0.32;
//                public static final double I = 0.00016;
//                public static final double D = 0.001;
                //2 Fairlanes
//                public static final double P = 0.7;
//                public static final double I = 0.00013;
//                public static final double D = 0.0015;
                //1 custom AL wheel
                public static final double P = 0.6;
                public static final double I = 0.0;
                public static final double D = 0.00075;
            }

        }

        public static final int TOP_MOTOR_ID = 20;
        public static final int BOTTOM_MOTOR_ID = 21;
        public static final int TILT_MOTOR_ID = 22;

    }

    public static final class limelight {
        public static final double DEGREES_FROM_HORIZONTAL = 15.85;
        public static final double TARGET_HEIGHT_INCHES = 91.0;
        public static final double LIMELIGHT_HEIGHT_INCHES = 18.5;
    }

    public static final class intake {
        public static final int ROLLER_BAR_ID = 30;
        public static final double INTAKE_SPEED = 0.4;
    }

    public static final class index {
        public static final int BAG_ID = 31;
        public static final int MAGIC_NUMBER = 2048;
        public static final double P = 0.0;
        public static final double I = 0.0;
        public static final double D = 0.0;
    }

    public static final class pre_shooter_stage {
        public static final int BAG_ID = 32;
        public static final int MAGIC_NUMBER = 4096;
        public static final double P = 0.0;
        public static final double I = 0.0;
        public static final double D = 0.0;
    }

    public static final class intake_lift {
        public static final int SERVO_PORT = 0;
    }

    public static final class turret_azimuth {
        public static final int MOTOR = 50;
    }

}
