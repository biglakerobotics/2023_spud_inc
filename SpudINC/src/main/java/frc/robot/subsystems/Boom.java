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


//NOTE FOR OTHER PROGRAMMERS: BOOM iS THE ARM THINGY ON THE ROBOT
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.Constants;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.util.sendable.Sendable;
//- BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
// import edu.wpi.first.wpilibj.motorcontrol.MotorController; 
// import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Boom extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS
    private WPI_TalonFX boomMotor = new WPI_TalonFX(Constants.BOOM_MOTOR_ID);
    private final Timer timer = new Timer();

    @Override
    public void addChild(String name, Sendable child) {
    }
    //addChild("BoomPot",boomPot);
    //- BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    /**
    *
    */
    public Boom() {
        //- BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        boomMotor.setInverted(true);
        boomMotor.configFactoryDefault();
        boomMotor.setNeutralMode(NeutralMode.Brake);
        boomMotor.configPeakOutputForward(1);
        boomMotor.configPeakOutputReverse(-1);
        boomMotor.setSensorPhase(false);
        boomMotor.config_kF(0, 300);

        timer.start();
        boomMotor.getSensorCollection().setIntegratedSensorPosition(0, 0);
        boomMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,0,10);
        boomMotor.getSensorCollection().setIntegratedSensorPosition(0, 0);
        boomMotor.config_kF(0, Constants.BOOM_MOTOR_KF); 
        boomMotor.config_kD(0, Constants.BOOM_MOTOR_KD); 
        boomMotor.config_kP(0, Constants.BOOM_MOTOR_KP); 
        boomMotor.config_kI(0, Constants.BOOM_MOTOR_KI); 
        boomMotor.configPeakOutputForward(1, 50); 
        boomMotor.configClosedloopRamp(1);

    }

    //System.out.println("BOOM_EXTENDING");

    //System.out.println("BOOM_RETRACTING");
public void BoomStop(){
    boomMotor.set(0);
    //System.out.println("BOOM_STOPPED");
}

public boolean BoomHome(){
    if (boomMotor.getStatorCurrent() > 70) {
        System.out.print("\nBoomMotor overcurrent! setting Boom Motor position to Zero");
        boomMotor.set(0);
        boomMotor.setSelectedSensorPosition(-750);
        return true;
    } else {
        System.out.print("\nboomMotor current: ");
        System.out.print(boomMotor.getStatorCurrent());
        System.out.print(" - boomMotor position: ");
        System.out.print(boomMotor.getSelectedSensorPosition());
        boomMotor.set(Constants.BOOM_SPEED_IN);
        return false;
    }
}

//THIS IS BOOM PRESETS
public void BoomFullyExtended(){
    if (timer.hasElapsed(1)) {
        if (boomMotor.getSensorCollection().getIntegratedSensorPosition() > 1000 && boomMotor.getSensorCollection().getIntegratedSensorPosition() < (Constants.BOOM_FULL_PRESET - 5000)) {
            boomMotor.set(Constants.BOOM_SPEED_OUT);
        } else if (boomMotor.getSensorCollection().getIntegratedSensorPosition() > (Constants.BOOM_FULL_PRESET + 2000)){ 
            boomMotor.set(Constants.BOOM_SPEED_IN);
        } else {
            boomMotor.set(TalonFXControlMode.Position, Constants.BOOM_FULL_PRESET);
        }
    }
}


public void BoomFullyMiddle(){
    if (timer.hasElapsed(1)) {
        if (boomMotor.getSensorCollection().getIntegratedSensorPosition() > 1000 && boomMotor.getSensorCollection().getIntegratedSensorPosition() < 6000) {
            boomMotor.set(Constants.BOOM_SPEED_OUT);
        } else if (boomMotor.getSensorCollection().getIntegratedSensorPosition() > 12000){ 
            boomMotor.set(Constants.BOOM_SPEED_IN);
        } else {
            boomMotor.set(TalonFXControlMode.Position, Constants.BOOM_MID_PRESET);
        }
    }
}

public void BoomRetract() {
    if (timer.hasElapsed(1)) {
        // System.out.print("\nBoomMotor pos: ");
        // System.out.print(boomMotor.getSensorCollection().getIntegratedSensorPosition());
        // if (boomMotor.getSensorCollection().getIntegratedSensorPosition() < 8000) {
            // boomMotor.set(ControlMode.Position, 1000);
            System.out.print("\nboomMotor current: ");
            System.out.print(boomMotor.getStatorCurrent());
            if (boomMotor.getStatorCurrent() > 72) {
                System.out.print("\nBoomMotor overcurrent!");
                boomMotor.set(0);
                boomMotor.setSelectedSensorPosition(-750);
            } else {
                System.out.print("\nboomMotor position: ");
                System.out.print(boomMotor.getSelectedSensorPosition());
                boomMotor.set(Constants.BOOM_SPEED_IN);
            }
        // } else {
        //     boomMotor.set(0);
        // }
    }
    
}


public void BoomFullyRetracted(){
    if (timer.hasElapsed(1)) {
        if (boomMotor.getSensorCollection().getIntegratedSensorPosition() < (Constants.BOOM_IN_PRESET - 1000)) {
            boomMotor.set(Constants.BOOM_SPEED_OUT);
        } else if (boomMotor.getSensorCollection().getIntegratedSensorPosition() > (Constants.BOOM_IN_PRESET + 5000)){ 
            boomMotor.set(Constants.BOOM_SPEED_IN);
        } else {
            boomMotor.set(TalonFXControlMode.Position, Constants.BOOM_IN_PRESET);
        }
    }
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


