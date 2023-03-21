package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

/**
 *
 */
public class AutoTankDrivePosition extends CommandBase {
    private final DriveTrain m_drivetrain;
    private int m_leftMotorPos;
    private int m_rightMotorPos;

    public AutoTankDrivePosition(DriveTrain DriveTrain, int LeftMotorPosition, int RightMotorPosition) {
        m_rightMotorPos = LeftMotorPosition;
        m_leftMotorPos = RightMotorPosition;
        m_drivetrain = DriveTrain;
        addRequirements(m_drivetrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_drivetrain.mDifferentialDrive.tankDrive(0, 0);
        m_drivetrain.SetSensorPositions(0, 0);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_drivetrain.PositionDrive(m_rightMotorPos, m_leftMotorPos);
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
