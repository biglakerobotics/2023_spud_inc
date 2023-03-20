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

import java.util.ResourceBundle.Control;

import javax.sound.sampled.SourceDataLine;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;

// import java.util.ResourceBundle.Control;
// import javax.lang.model.util.ElementScanner14;

// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.DemandType;
// import com.ctre.phoenix.motorcontrol.FeedbackDevice;
// import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.Constants;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.AnalogInput;
//- BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.AnalogPotentiometer;
// import edu.wpi.first.wpilibj.motorcontrol.MotorController; 
// import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Boom extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS
    private WPI_TalonFX boomMotor = new WPI_TalonFX(11);
    private final Timer timer = new Timer();

    AnalogPotentiometer boomPot = new AnalogPotentiometer(3,10,0);
    @Override
    public void addChild(String name, Sendable child) {
        super.addChild("BoomPot", boomPot);
    }
    //addChild("BoomPot",boomPot);
    //- BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    double boomSpeedIn = -.3;
    double boomSpeedOut = .17;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    /**
    *
    */
    public Boom() {
        //- BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        boomMotor.setInverted(false);
        boomMotor.configFactoryDefault();
        //boomMotor.setNeutralMode(NeutralMode.Brake);
        boomMotor.configPeakOutputForward(1);
        boomMotor.configPeakOutputReverse(-1);
        boomMotor.setSensorPhase(true);

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

//THIS IS BOOM PRESETS
public void BoomFullyExtended(){
    if (timer.hasElapsed(1)) {
        if (boomMotor.getSensorCollection().getIntegratedSensorPosition() > 1000 && boomMotor.getSensorCollection().getIntegratedSensorPosition() < 19000) {
            boomMotor.set(boomSpeedOut);
        } else if (boomMotor.getSensorCollection().getIntegratedSensorPosition() > 23000){ 
            boomMotor.set(boomSpeedIn);
        } else {
            boomMotor.set(TalonFXControlMode.Position, 21000);
        }
    }
}


public void BoomFullyMiddle(){
    if (timer.hasElapsed(1)) {
        if (boomMotor.getSensorCollection().getIntegratedSensorPosition() > 1000 && boomMotor.getSensorCollection().getIntegratedSensorPosition() < 6000) {
            boomMotor.set(boomSpeedOut);
        } else if (boomMotor.getSensorCollection().getIntegratedSensorPosition() > 12000){ 
            boomMotor.set(boomSpeedIn);
        } else {
            boomMotor.set(TalonFXControlMode.Position, 9000);
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
            }
            System.out.print("\nboomMotor position: ");
            System.out.print(boomMotor.getSelectedSensorPosition());
            boomMotor.set(boomSpeedIn);
        // } else {
        //     boomMotor.set(0);
        // }
    }
    
}


public void BoomFullyRetracted(){
    if (timer.hasElapsed(1)) {
        boomMotor.set(TalonFXControlMode.Position, 0);
        // } else {
        //     boomMotor.set(0);
        // }
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


