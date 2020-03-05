/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants.drivetrain;

import static java.lang.Math.abs;


public class DrivetrainSubsystem extends SubsystemBase {

  private CANSparkMax left_master = new CANSparkMax(drivetrain.LEFT_MASTER_ID, MotorType.kBrushless);
  private CANSparkMax left_slave = new CANSparkMax(drivetrain.LEFT_SLAVE_ID, MotorType.kBrushless);
  private CANSparkMax right_master = new CANSparkMax(drivetrain.RIGHT_MASTER_ID, MotorType.kBrushless);
  private CANSparkMax right_slave = new CANSparkMax(drivetrain.RIGHT_SLAVE_ID, MotorType.kBrushless);


  public DrivetrainSubsystem() {
    left_master.setIdleMode(IdleMode.kBrake);
    left_slave.setIdleMode(IdleMode.kBrake);
    right_master.setIdleMode(IdleMode.kBrake);
    right_slave.setIdleMode(IdleMode.kBrake);
    left_master.setOpenLoopRampRate(0.4);
    left_slave.setOpenLoopRampRate(0.4);
    right_master.setOpenLoopRampRate(0.4);
    right_slave.setOpenLoopRampRate(0.4);

    left_slave.follow(left_master);
    right_slave.follow(right_master);

  }

  public void drive(double left_speed, double right_speed) {
    left_master.set(-left_speed);
    right_master.set(right_speed);
  }

  public double deadband_handler(double speed) {
    if (abs(speed) > 0.1) {
      return speed;
    } else {
      return 0.0;
    }
//    return speed;

  }

  public double square_joysticks(double value) {
    return Math.copySign(value * value, value);
  }

}
