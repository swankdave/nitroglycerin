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
    }

    public static final class shooter {
        public static final class pid_constants {
            public static final class top_motor {
                public static final double P = 0.0;
                public static final double I = 0.0;
                public static final double D = 0.0;
                public static final double FF = 0.0;
            }
            public static final class bottom_motor {
                public static final double P = 0.0;
                public static final double I = 0.0;
                public static final double D = 0.0;
                public static final double FF = 0.0;
            }

        }

        public static final int TOP_MOTOR_ID = 20;
        public static final int BOTTOM_MOTOR_ID = 21;
        public static final int AZIMUTH_MOTOR_ID = 22;

    }
}
