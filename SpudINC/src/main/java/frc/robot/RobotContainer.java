// RobotBuilder Version: 5.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: RobotContainer.


package frc.robot;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command.InterruptionBehavior;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

    private static RobotContainer m_robotContainer = new RobotContainer();

    // The robot's subsystems
    public final Boom m_boom = new Boom();
    public final Claw m_claw = new Claw();
    public final Elevator m_elevator = new Elevator();
    public final DriveTrain m_driveTrain = new DriveTrain();

    // Joysticks
    private final XboxController xboxController1 = new XboxController(1);
    private final Joystick joystick1 = new Joystick(0);

    // A chooser for autonomous commands
    SendableChooser<Command> m_chooser = new SendableChooser<>();

    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
    */
    private RobotContainer() {
        // Smartdashboard Subsystems


        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand(m_boom));

        // Configure the button bindings
        configureButtonBindings(); 

        // Configure default commands
        m_driveTrain.setDefaultCommand(new TankDrive(joystick1, m_driveTrain));

        // Configure autonomous sendable chooser

        m_chooser.setDefaultOption("Autonomous Command", new AutonomousCommand(m_boom));

        SmartDashboard.putData("Auto Mode", m_chooser);
    }

    public static RobotContainer getInstance() {
        return m_robotContainer;
    }

    /**
     * Use this method to define your button->command mappings.  Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        
        // Boom 
        final JoystickButton boomExtendButton = new JoystickButton(xboxController1, XboxController.Button.kX.value);    
        final JoystickButton boomRetractButton = new JoystickButton(xboxController1, XboxController.Button.kB.value);     
        // final JoystickButton boomFullyExtendedButton = new JoystickButton(xboxController1, XboxController.Button.kLeftBumper.value); 
        final JoystickButton boomFullyRetractedButton = new JoystickButton(xboxController1, XboxController.Button.kLeftBumper.value); 

        boomExtendButton.onTrue(new BoomFullyExtended( m_boom ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
        boomRetractButton.onTrue(new BoomFullyRetracted( m_boom ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));       
        // boomFullyExtendedButton.whileTrue(new BoomFullyExtended( m_boom ).withInterruptBehavior(InterruptionBehavior.kCancelSelf)); 
        boomFullyRetractedButton.whileTrue(new BoomRetract( m_boom ).withInterruptBehavior(InterruptionBehavior.kCancelSelf)); 

        // Elevator (ElevatorFrontOnlyDown = button7, ElevatorFrontOnlyUp = button8, ElevatorBackOnlyDown = button9, ElevatorBackOnlyUp = button10)
        final JoystickButton ybutton = new JoystickButton(xboxController1, XboxController.Button.kY.value);
        final JoystickButton abutton = new JoystickButton(xboxController1, XboxController.Button.kA.value);
        final JoystickButton ninebutton = new JoystickButton(joystick1, 9);
        final JoystickButton tenbutton = new JoystickButton(joystick1, 10);
        final JoystickButton sevenbutton = new JoystickButton(joystick1, 7);
        final JoystickButton eightbutton = new JoystickButton(joystick1, 8);
        final JoystickButton elevenbutton = new JoystickButton(joystick1, 11);
        ybutton.whileTrue(new ElevatorUp(m_elevator).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
        abutton.whileTrue(new ElevatorDown(m_elevator).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
        ninebutton.whileTrue(new ElevatorBackOnlyDown(m_elevator).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
        tenbutton.whileTrue(new ElevatorBackOnlyUp(m_elevator).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
        sevenbutton.whileTrue(new ElevatorFrontOnlyDown(m_elevator).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
        eightbutton.whileTrue(new ElevatorFrontOnlyUp(m_elevator).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
        elevenbutton.whileTrue(new ElevatorStart(m_elevator).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

        // Claw (ClawToggle = r1 or rb)
        final JoystickButton clawToggleButton = new JoystickButton(xboxController1, XboxController.Button.kRightBumper.value);        
        clawToggleButton.toggleOnTrue(new ClawToggle(false, m_claw).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

        //Presets (PickUp = 6, Mid = 3, Low = 4, High = 5)
        final JoystickButton PickUpPresetButton = new JoystickButton(joystick1, 6); 
        final JoystickButton MidPresetButton = new JoystickButton(joystick1,3);
        final JoystickButton LowPresetButton = new JoystickButton(joystick1,4);
        final JoystickButton HighPresetButton = new JoystickButton(joystick1,5);

        MidPresetButton.onTrue(new ElevatorMidPreset(m_boom,m_elevator).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
        PickUpPresetButton.onTrue(new ElevatorPickUpPreset(m_boom,m_elevator).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
        LowPresetButton.onTrue(new ElevatorLowPreset(m_boom, m_elevator).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
        HighPresetButton.onTrue(new ElevatorHighPreset(m_boom,m_elevator).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
    }

    public Joystick getJoystick1() {
        return joystick1;
    }

    public XboxController getxboxController1() {
        return xboxController1;
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // The selected command will be run in autonomous
        return m_chooser.getSelected();
    }
}
