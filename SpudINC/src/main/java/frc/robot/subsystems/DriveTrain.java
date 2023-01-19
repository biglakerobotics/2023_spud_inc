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


import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class DriveTrain extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
private PWMTalonSRX mC_Left_Bank_Front;
private PWMTalonSRX mC_Left_Bank_Rear;
private MotorControllerGroup motor_Controller_Group_Left_Bank;
private PWMTalonSRX mC_Right_Bank_Front;
private PWMVictorSPX mC_Right_Bank_Rear;
private MotorControllerGroup motor_Controller_Group_Right_Bank;
private DifferentialDrive driveTrainController;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    /**
    *
    */
    public DriveTrain() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
mC_Left_Bank_Front = new PWMTalonSRX(4);
 addChild("MC_Left_Bank_Front",mC_Left_Bank_Front);
 mC_Left_Bank_Front.setInverted(true);

mC_Left_Bank_Rear = new PWMTalonSRX(6);
 addChild("MC_Left_Bank_Rear",mC_Left_Bank_Rear);
 mC_Left_Bank_Rear.setInverted(true);

motor_Controller_Group_Left_Bank = new MotorControllerGroup(mC_Left_Bank_Front, mC_Left_Bank_Rear  );
 addChild("Motor_Controller_Group_Left_Bank",motor_Controller_Group_Left_Bank);
 

mC_Right_Bank_Front = new PWMTalonSRX(5);
 addChild("MC_Right_Bank_Front",mC_Right_Bank_Front);
 mC_Right_Bank_Front.setInverted(false);

mC_Right_Bank_Rear = new PWMVictorSPX(7);
 addChild("MC_Right_Bank_Rear",mC_Right_Bank_Rear);
 mC_Right_Bank_Rear.setInverted(false);

motor_Controller_Group_Right_Bank = new MotorControllerGroup(mC_Right_Bank_Front, mC_Right_Bank_Rear  );
 addChild("Motor_Controller_Group_Right_Bank",motor_Controller_Group_Right_Bank);
 

driveTrainController = new DifferentialDrive(motor_Controller_Group_Left_Bank, motor_Controller_Group_Right_Bank);
 addChild("DriveTrainController",driveTrainController);
 driveTrainController.setSafetyEnabled(true);
driveTrainController.setExpiration(0.1);
driveTrainController.setMaxOutput(1.0);



    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
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

