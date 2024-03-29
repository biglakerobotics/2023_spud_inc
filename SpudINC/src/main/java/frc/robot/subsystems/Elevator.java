// RobotBuilder Version: 5.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Subsystem.

package frc.robot.subsystems;


import frc.robot.Constants;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.*;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Elevator extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    private final WPI_TalonFX Front = new WPI_TalonFX(5);
    private final WPI_TalonFX Back = new WPI_TalonFX(12);
    private final Timer timer = new Timer();
    
    AnalogPotentiometer elevatorFrontPot = new AnalogPotentiometer(1,10,0); 
    AnalogPotentiometer elevatorBackPot = new AnalogPotentiometer(0, 360, 0);

    DigitalInput FrontLimitSwitch = new DigitalInput(8);
    DigitalInput BackLimitSwitch = new DigitalInput(9);



    double elevatorUpSpeed = .2;
    double elevatorDownSpeed = -.2;

    public Elevator() {
        Front.configFactoryDefault();
        Front.setNeutralMode(NeutralMode.Brake);
        Back.configFactoryDefault();
        Back.setNeutralMode(NeutralMode.Brake);
        Front.configPeakOutputReverse(-0.5);
        Back.configPeakOutputReverse(-0.5);
        Front.setInverted(false);
        Back.setInverted(true);
        Back.setSensorPhase(true);

        timer.start();
        Front.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,0,10);
        Front.getSensorCollection().setIntegratedSensorPosition(0, 0);
        Front.config_kF(0, Constants.kF); 
        Front.config_kD(0, Constants.kD); 
        Front.config_kP(0, Constants.kP); 
        Front.config_kI(0, Constants.kI); 
        Front.configPeakOutputForward(Constants.peakOutPut, 50); 
        Front.configClosedloopRamp(1);

        Back.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,0,10);
        Back.getSensorCollection().setIntegratedSensorPosition(0, 0);
        Back.config_kF(0, Constants.kF); 
        Back.config_kD(0, Constants.kD); 
        Back.config_kP(0, Constants.kP); 
        Back.config_kI(0, Constants.kI); 
        Back.configPeakOutputForward(Constants.peakOutPut, 50); 
        Back.configClosedloopRamp(1);
        
        addChild("ElevatorFrontPot",elevatorFrontPot);
        addChild("ElevatorBackPot",elevatorBackPot);

        addChild("FrontLimitSwitch",FrontLimitSwitch);
        addChild("BackLimitSwitch",BackLimitSwitch);

    }

    public void ElevatorUp(){    
        ElevatorFrontOnlyUp(); 
        ElevatorBackOnlyUp();
    }

    public void ElevatorDown(){
        ElevatorFrontOnlyDown();
        ElevatorBackOnlyDown();
    }

    public void ElevatorStop(){
        Front.set(0);
        Back.set(0);
    }

    public void ElevatorFrontOnlyUp(){
        if (timer.hasElapsed(1)) {
            // System.out.print("\nFront sensor position: ");
            // System.out.print(Front.getSensorCollection().getIntegratedSensorPosition());
            // if (Front.getSensorCollection().getIntegratedSensorPosition() < 50000) {
            //     Front.set(elevatorUpSpeed);
            // } else {
            //     Front.set(0);
            // }
            Front.set(ControlMode.Position, 130000);
        }
    }

    public void ElevatorFrontOnlyDown(){
        if (timer.hasElapsed(1)) {
           // System.out.print("\nFront sensor position: ");
            //System.out.print(Front.getSensorCollection().getIntegratedSensorPosition());
            if (Front.getSensorCollection().getIntegratedSensorPosition() > 1000) {
                Front.set(elevatorDownSpeed);
            } else {
                Front.set(0);

            }
        }
    }

    public void ElevatorBackOnlyUp(){
        if (timer.hasElapsed(1)) {
            Back.set(ControlMode.Position, 120000);
        //     //System.out.print("\nBack sensor position: ");
        //    // System.out.print(Back.getSensorCollection().getIntegratedSensorPosition());
        //     if (Back.getSensorCollection().getIntegratedSensorPosition() > -50000) {
        //         Back.set(elevatorUpSpeed);
        //     } else {
        //         Back.set(0);
        //     }
        }
    }

    public void ElevatorBackOnlyDown(){
        if (timer.hasElapsed(1)) {
            // System.out.print("\nBack sensor position: ");
             //System.out.print(Back.getSensorCollection().getIntegratedSensorPosition());
             if (Back.getSensorCollection().getIntegratedSensorPosition() < 1000) {
                 Back.set(elevatorDownSpeed);
             } else {
                 Back.set(0);
             }
         }
}

    public void ElevatorBalance(){
        if (timer.hasElapsed(1)) {
             if (BackLimitSwitch.get()==false) {
                 Back.set(elevatorDownSpeed);
             } else {
                 Back.set(0);
             }
            // System.out.print("\nFront sensor position: ");
             //System.out.print(Front.getSensorCollection().getIntegratedSensorPosition());
             if (FrontLimitSwitch.get()==false) {
                 Front.set(elevatorDownSpeed);
             } else {
                 Front.set(0);
             }
         }
        }


    public void ElevatorStart(){                   //note to future programmers, CHANGE THESE NUMBERS!!!!
        if (timer.hasElapsed(1)) {
            if (Back.getSensorCollection().getIntegratedSensorPosition() < -990 || Back.getSensorCollection().getIntegratedSensorPosition() < -1010) {
               if (Back.getSensorCollection().getIntegratedSensorPosition() < -990){
                   Back.set(elevatorDownSpeed);
               }
               if (Back.getSensorCollection().getIntegratedSensorPosition() > -1010){
                   Back.set(elevatorUpSpeed);
               }
            }else{
               Back.set(0);
            }
            if (Front.getSensorCollection().getIntegratedSensorPosition() < 1010 || Front.getSensorCollection().getIntegratedSensorPosition() > 990) {
                if(Front.getSensorCollection().getIntegratedSensorPosition() < 1000){
                   Front.set(elevatorUpSpeed);
                }
                if(Front.getSensorCollection().getIntegratedSensorPosition() > 1000){
                   Front.set(elevatorDownSpeed);
                }
            } else {
                Front.set(0);
            }
        }
    }

    public void ElevatorMidPreset(){
        if (timer.hasElapsed(1)) {
            System.out.print("\nMid Elevator Preset");
            System.out.print("\nFront Position: "); System.out.print(Front.getSelectedSensorPosition());
            System.out.print("\nBack Position: "); System.out.print(Back.getSelectedSensorPosition());
            Front.set(TalonFXControlMode.Position, Constants.ELEVATOR_MID_PRESET_FRONT);
            Back.set(TalonFXControlMode.Position, Constants.ELEVATOR_MID_PRESET_BACK);
        }
    }

    public void ElevatorPickUpPreset(){           //note to future programmers, CHANGE THESE NUMBERS!!!
        if (timer.hasElapsed(1)) {
            Front.set(TalonFXControlMode.Position, 2000);
            Back.set(TalonFXControlMode.Position, 20000);
        }
    }

    public void ElevatorHighPreset(){

    }

    public void ElevatorLowPreset(){
        
    }



    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}