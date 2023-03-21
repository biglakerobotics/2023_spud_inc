package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

/**
 *
 */
public class AutoTankDrive extends CommandBase {
    private final DriveTrain m_drivetrain;
    private Double m_leftMotorSpeed;
    private Double m_rightMotorSpeed;

    public AutoTankDrive(DriveTrain DriveTrain, Double LeftMotorSpeed, Double RightMotorSpeed) {
        m_leftMotorSpeed = LeftMotorSpeed;
        m_rightMotorSpeed = RightMotorSpeed;
        m_drivetrain = DriveTrain;
        addRequirements(m_drivetrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_drivetrain.mDifferentialDrive.tankDrive(0, 0);

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_drivetrain.mDifferentialDrive.tankDrive(m_leftMotorSpeed, m_rightMotorSpeed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_drivetrain.mDifferentialDrive.tankDrive(0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
