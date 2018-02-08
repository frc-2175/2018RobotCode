package org.usfirst.frc.team2175.subsystem;

import org.usfirst.frc.team2175.MotorWrapper;
import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.SolenoidWrapper;
import org.usfirst.frc.team2175.VirtualSpeedController;
import org.usfirst.frc.team2175.info.RobotInfo;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;

public class DrivetrainSubsystem extends BaseSubsystem {
    private RobotInfo robotInfo;
    private MotorWrapper leftMaster;
    private MotorWrapper leftSlaveOne;
    private MotorWrapper leftSlaveTwo;
    private MotorWrapper rightMaster;
    private MotorWrapper rightSlaveOne;
    private MotorWrapper rightSlaveTwo;
    private SolenoidWrapper driveShifters;
    private DifferentialDrive robotDrive;
    private static VirtualSpeedController leftVirtualSpeedController =
            new VirtualSpeedController();
    private static VirtualSpeedController rightVirtualSpeedController =
            new VirtualSpeedController();
    private static DifferentialDrive virtualRobotDrive = new DifferentialDrive(
            leftVirtualSpeedController, rightVirtualSpeedController);
    private AHRS navx;

    public DrivetrainSubsystem() {
        robotInfo = ServiceLocator.get(RobotInfo.class);
        leftMaster = robotInfo.get(RobotInfo.LEFT_MOTOR_MASTER);
        leftSlaveOne = robotInfo.get(RobotInfo.LEFT_MOTOR_SLAVE1);
        leftSlaveTwo = robotInfo.get(RobotInfo.LEFT_MOTOR_SLAVE2);
        rightMaster = robotInfo.get(RobotInfo.RIGHT_MOTOR_MASTER);
        rightSlaveOne = robotInfo.get(RobotInfo.RIGHT_MOTOR_SLAVE1);
        rightSlaveTwo = robotInfo.get(RobotInfo.RIGHT_MOTOR_SLAVE2);
        driveShifters = new SolenoidWrapper(4);
        leftSlaveOne.follow(leftMaster);
        leftSlaveTwo.follow(leftMaster);
        rightSlaveOne.follow(rightMaster);
        rightSlaveTwo.follow(rightMaster);
        robotDrive = new DifferentialDrive(leftMaster.getMotor(),
                rightMaster.getMotor());
        leftVirtualSpeedController = new VirtualSpeedController();
        rightVirtualSpeedController = new VirtualSpeedController();
        virtualRobotDrive = new DifferentialDrive(leftVirtualSpeedController,
                rightVirtualSpeedController);
        leftMaster.configSelectedFeedbackSensor(
                FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        rightMaster.configSelectedFeedbackSensor(
                FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        leftMaster.setSelectedSensorPosition(0, 0, 0);
        rightMaster.setSelectedSensorPosition(0, 0, 0);
        navx = new AHRS(SPI.Port.kMXP);
        navx.reset();
    }

    public void stopAllMotors() {
        robotDrive(0, 0);
    }

    /**
     * Returns the lerp of the arcade and curvature values for the left and
     * right virtual speed controllers
     *
     * @return {left, right}
     */
    public static double[] getBlendedMotorValues(double moveValue,
            double turnValue) {
        final double INPUT_THRESHOLD = 0.1;
        virtualRobotDrive.arcadeDrive(moveValue, turnValue, false);
        double leftArcadeValue = leftVirtualSpeedController.get();
        double rightArcadeValue = rightVirtualSpeedController.get();

        virtualRobotDrive.curvatureDrive(moveValue, turnValue, false);
        double leftCurvatureValue = leftVirtualSpeedController.get();
        double rightCurvatureValue = rightVirtualSpeedController.get();

        double lerpT =
                Math.abs(deadband(moveValue, RobotDriveBase.kDefaultDeadband))
                        / INPUT_THRESHOLD;
        lerpT = clamp(lerpT, 0, 1);
        double leftBlend = lerp(leftArcadeValue, leftCurvatureValue, lerpT);
        double rightBlend = lerp(rightArcadeValue, rightCurvatureValue, lerpT);

        double[] blends = { leftBlend, -rightBlend };
        return blends;
    }

    /**
     * Clamps a double value based on a minimum and a maximum
     *
     * @param val
     *            the value to clamp
     * @param min
     *            the minimum to clamp on
     * @param max
     *            the maximum to clamp on
     * @return min if val is less than min or max if val is greater than max
     */
    public static double clamp(double val, double min, double max) {
        return val >= min && val <= max ? val : (val < min ? min : max);
    }

    /**
     * Linearly interpolates between two points based on a t value
     *
     * @param a
     *            the point to interpolate from
     * @param b
     *            the point to interpolate to
     * @param t
     *            the value to interpolate on
     * @return an output based on the formula lerp(a, b, t) = (1-t)a + tb
     */
    public static double lerp(double a, double b, double t) {
        return (1 - t) * a + t * b;
    }

    // Copied from RobotDriveBase
    public static double deadband(double value, double deadband) {
        if (Math.abs(value) > deadband) {
            if (value > 0.0) {
                return (value - deadband) / (1.0 - deadband);
            } else {
                return (value + deadband) / (1.0 - deadband);
            }
        } else {
            return 0.0;
        }
    }

    public static double undeadband(double value, double deadband) {
        if (value < 0) {
            double t = -value;
            return DrivetrainSubsystem.lerp(-deadband, -1, t);
        } else if (value > 0) {
            double t = value;
            return DrivetrainSubsystem.lerp(deadband, 1, t);
        } else {
            return 0;
        }
    }

    public double getLeftEncoderValues() {
        return -leftMaster.getSelectedSensorPosition(0);
    }

    public double getRightEncoderValues() {
        return rightMaster.getSelectedSensorPosition(0);
    }

    public double getGryroValue() {
        return navx.getAngle();
    }

    public void robotDrive(double xSpeed, double zRotation) {
        double[] blendedValues = getBlendedMotorValues(xSpeed, -zRotation);
        robotDrive.tankDrive(blendedValues[0], blendedValues[1]);
    }

    public void autonDrive(double leftSpeed, double rightSpeed) {
        robotDrive.tankDrive(leftSpeed, rightSpeed);
    }

    public void resetAllSensors() {
        leftMaster.setSelectedSensorPosition(0, 0, 0);
        rightMaster.setSelectedSensorPosition(0, 0, 0);
        navx.reset();
    }

    double thetaNeeded;

    public void setThetaNeeded(double theta) {
        thetaNeeded = theta;
    }

    public double getThetaNeeded() {
        return thetaNeeded;
    }

    public double toTicks(double distance) {
        return distance;
    }

    double nDy;

    public void setNDY(double nDy) {
        this.nDy = nDy;
    }

    public double getNDY() {
        return nDy;
    }

    double nDx;

    public void setNDX(double nDx) {
        this.nDx = nDx;
    }

    public double getNDX() {
        return nDx;
    }

    public void arcadeDrive(double moveValue, double turnValue) {
        robotDrive.arcadeDrive(moveValue, turnValue);
    }

    public void shift(boolean high) {
        driveShifters.set(high);
    }
}
