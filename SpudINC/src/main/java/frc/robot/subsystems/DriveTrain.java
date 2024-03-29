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
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class DriveTrain extends SubsystemBase {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS
public static final String Left = "";
public static final String Right = "";

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS
private final WPI_TalonFX Llead = new WPI_TalonFX(14);
private final WPI_TalonFX Lfollow = new WPI_TalonFX(13);
private final WPI_TalonFX Rlead = new WPI_TalonFX(16);
private final WPI_TalonFX Rfollow = new WPI_TalonFX(15);
public final DifferentialDrive mDifferentialDrive = new DifferentialDrive(Llead, Rlead);


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    /**
    *
    */
    public DriveTrain() {
        Rlead.configFactoryDefault();
        Rfollow.configFactoryDefault();
        Lfollow.configFactoryDefault();
        Llead.configFactoryDefault();
        Llead.setInverted(true);
        Lfollow.setInverted(true);
        Rfollow.follow(Rlead);
        Lfollow.follow(Llead);
        Rlead.setSafetyEnabled(false);
        Rfollow.setSafetyEnabled(false);
        Llead.setSafetyEnabled(false);
        Lfollow.setSafetyEnabled(false);
        mDifferentialDrive.setSafetyEnabled(false);

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

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

