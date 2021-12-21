// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.Faults;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class Drivetrain extends SubsystemBase {
    WPI_TalonFX rightFront = new WPI_TalonFX(1);
    WPI_TalonFX rightBack = new WPI_TalonFX(10);
    WPI_TalonFX leftFront = new WPI_TalonFX(2);
    WPI_TalonFX leftBack = new WPI_TalonFX(20);
    
    /** Creates a new ExampleSubsystem. */
    public Drivetrain() {}

    @Override
    public void periodic() {
    // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
    }
}
