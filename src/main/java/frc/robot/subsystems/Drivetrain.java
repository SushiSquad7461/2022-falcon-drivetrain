// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.PerpetualCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.Faults;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
    WPI_TalonFX rightFront;
    WPI_TalonFX rightBack;
    WPI_TalonFX leftFront;
    WPI_TalonFX leftBack;

    WPI_VictorSPX victor;

    DifferentialDrive diffDrive;
        
    /** Creates a new ExampleSubsystem. */
    public Drivetrain() {

        rightFront = new WPI_TalonFX(Constants.kDrivetrain.FRONT_RIGHT_ID);
        rightBack = new WPI_TalonFX(Constants.kDrivetrain.BACK_RIGHT_ID);
        leftFront = new WPI_TalonFX(Constants.kDrivetrain.FRONT_LEFT_ID);
        leftBack = new WPI_TalonFX(Constants.kDrivetrain.BACK_LEFT_ID);

        victor = new WPI_VictorSPX(10);

        diffDrive = new DifferentialDrive(leftFront, rightFront);

        /* factory default values */
        rightFront.configFactoryDefault();
        rightBack.configFactoryDefault();
        leftFront.configFactoryDefault();
        leftBack.configFactoryDefault();

        /* set up followers */
        //rightBack.follow(rightFront);
        //leftBack.follow(leftFront);

        rightFront.setSelectedSensorPosition(0);
        leftFront.setSelectedSensorPosition(0);
        rightBack.setSelectedSensorPosition(0);
        leftBack.setSelectedSensorPosition(0);

        rightFront.setInverted(TalonFXInvertType.CounterClockwise);
        leftFront.setInverted(TalonFXInvertType.Clockwise);
        rightBack.setInverted(TalonFXInvertType.CounterClockwise);
        leftBack.setInverted(TalonFXInvertType.Clockwise);

        rightFront.setSafetyEnabled(false);
        rightBack.setSafetyEnabled(false);
        leftFront.setSafetyEnabled(false);
        leftBack.setSafetyEnabled(false);

        /*
         * WPI drivetrain classes defaultly assume left and right are opposite. call
         * this so we can apply + to both sides when moving forward. DO NOT CHANGE
         */
        //diffDrive.setRightSideInverted(false);
    }

    public void runFalcon(int value) {
        rightFront.set(ControlMode.PercentOutput, value);
    }

    public void curveDrive(double linearVelocity, double angularVelocity, boolean isQuickturn) {
        if (isQuickturn) {
          angularVelocity /= 2;
        }
        SmartDashboard.putNumber("angular velocity", angularVelocity);
        SmartDashboard.putNumber("linear velocity", linearVelocity);
        diffDrive.curvatureDrive(linearVelocity, angularVelocity, isQuickturn);
    }

    public void runHopper() {
        victor.set(ControlMode.PercentOutput, 1);
    }

    public void stopHopper() {
        victor.set(ControlMode.PercentOutput, 0);
    }



    @Override
    public void periodic() {
    // This method will be called once per scheduler run
        SmartDashboard.putNumber("left output", leftFront.getMotorOutputPercent());
        SmartDashboard.putNumber("right output", rightFront.getMotorOutputPercent());
        SmartDashboard.putNumber("left back output", leftBack.getMotorOutputPercent());
        SmartDashboard.putNumber("right back output", rightBack.getMotorOutputPercent());
        
        SmartDashboard.putNumber("front left encoder output", leftFront.getSelectedSensorPosition());
        SmartDashboard.putNumber("back left encoder output", leftBack.getSelectedSensorPosition());
        SmartDashboard.putNumber("front right encoder output", rightFront.getSelectedSensorPosition());
        SmartDashboard.putNumber("back right encoder output", rightBack.getSelectedSensorPosition());
    }

    @Override
    public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
    }
}
