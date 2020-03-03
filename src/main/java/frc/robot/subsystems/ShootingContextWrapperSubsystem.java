package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.context.ShootingContext;

public class ShootingContextWrapperSubsystem extends SubsystemBase {
    public ShootingContext shooting_context = ShootingContext.getInstance();

    public void set_close_shooting() {
        shooting_context.update_shooting_context(shooting_context.CLOSE_SHOT);
    }

    public void set_init_shooting() {
        shooting_context.update_shooting_context(shooting_context.INIT_LINE);
    }

    public void set_trench_corner_shooting() {
        shooting_context.update_shooting_context(shooting_context.TRENCH_CORNER);
    }

    public void set_trench_back_shooting() {
        shooting_context.update_shooting_context(shooting_context.TRENCH_BACK);
    }

    public void set_full_court_shooting() {
        shooting_context.update_shooting_context(shooting_context.FULL_COURT);
    }

    public void set_not_shooting() {
        shooting_context.update_shooting_context(shooting_context.NOT_SHOOTING);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Active Shooter Context: ", shooting_context.get_active_context());
    }

    public void idler() {

    }

    public void low_level_update(int context) {
        shooting_context.update_shooting_context(context);
    }
}
