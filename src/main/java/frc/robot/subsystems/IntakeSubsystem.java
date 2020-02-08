package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {

    private CANSparkMax roller_bar = new CANSparkMax(Constants.intake.ROLLER_BAR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);

    public IntakeSubsystem() {
        roller_bar.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }

    public void run_intake() {
        roller_bar.set(Constants.intake.SPEED);
    }

    public void neutral_intake() {
        roller_bar.set(0);
    }
}
