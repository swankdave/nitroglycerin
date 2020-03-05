package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class WinchSubsystem extends SubsystemBase {

    private CANSparkMax winch_motor = new CANSparkMax(51, CANSparkMaxLowLevel.MotorType.kBrushless);

    public WinchSubsystem() {
        winch_motor.restoreFactoryDefaults();
        winch_motor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }

    public void extend() {
        winch_motor.set(-0.6);
    }

    public void retract_powerful() {
        winch_motor.set(0.75);
    }

    public void retract_slow() {
        winch_motor.set(0.2);
    }

    public void neutral_output() {
        winch_motor.stopMotor();
    }


}
