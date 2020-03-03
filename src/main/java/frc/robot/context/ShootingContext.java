package frc.robot.context;

public class ShootingContext {
    public static ShootingContext instance;
    public final int CLOSE_SHOT = 0;
    public final int INIT_LINE = 1;
    public final int TRENCH_CORNER = 2;
    public final int TRENCH_BACK = 3;
    public final int FULL_COURT = 4;
    public final int NOT_SHOOTING = 5;
    private int ACTIVE_CONTEXT = NOT_SHOOTING;

    public static ShootingContext getInstance() {
        if (instance == null) {
            instance = new ShootingContext();
        }

        return instance;
    }

    public double get_shooter_tilt_pos() {
        switch (ACTIVE_CONTEXT) {
            case CLOSE_SHOT:
                return -53.61;
            case INIT_LINE:
                return 0;
            case TRENCH_CORNER:
                return 19.113;
            case TRENCH_BACK:
                return 21;
            case FULL_COURT:
                return 0;
            case NOT_SHOOTING:
                return 0;
            default:
                return 0;
        }
    }

    public double get_shooter_speed() {
        switch (ACTIVE_CONTEXT) {
            case CLOSE_SHOT:
                return 0.75;
            case INIT_LINE:
                return 1;
            case TRENCH_CORNER:
                return 1;
            case TRENCH_BACK:
                return 1;
            case FULL_COURT:
                return 1;
            case NOT_SHOOTING:
                return 0;
            default:
                return 0;
        }
    }

    public void update_shooting_context(int new_context_value) {
        ACTIVE_CONTEXT = new_context_value;
    }

    public int get_active_context() {
        return ACTIVE_CONTEXT;
    }

    public double get_backspin() {
        return 0.8;
    }

    public int get_limelight_pipeline() {
        switch (ACTIVE_CONTEXT) {
            case CLOSE_SHOT:
                return 0;
            case INIT_LINE:
                return 0;
            case TRENCH_CORNER:
                return 0;
            case TRENCH_BACK:
                return 1;
            case FULL_COURT:
                return 0;
            case NOT_SHOOTING:
                return 1;
            default:
                return 0;
        }
    }

}
