package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Servo;

public class ArmsReleaserSubsytem extends SubsystemBase {

    private Servo arms_releaser = new Servo(2);

    public void set_position(double position) {
        arms_releaser.set(position);
    }

}
