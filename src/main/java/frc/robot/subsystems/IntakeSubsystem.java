package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {

    private CANSparkMax roller_bar = new CANSparkMax(Constants.intake.ROLLER_BAR_ID, CANSparkMaxLowLevel.MotorType.kBrushless);

    public IntakeSubsystem() {
        roller_bar.restoreFactoryDefaults();
        roller_bar.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }

    @Override
    public void periodic() {

        SmartDashboard.putNumber("Intake Speed: ", roller_bar.get());
    }

//    public void run_intake() {
//        roller_bar.set(Constants.intake.SPEED);
//    }
    public void run_intake(double input) {
        roller_bar.set(input);
    }

    public void neutral_intake() {
        roller_bar.set(0);
    }
}
