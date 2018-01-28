package team2175.subsystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.drive.RobotDriveBase;
import team2175.BaseTest;

public class DrivetrainSubsystemTest extends BaseTest {
	@Test
	public void testGetBlendedMotorValues_Left_Full_Right_Half() {
		final double moveValue = undeadband(1, RobotDriveBase.kDefaultDeadband);
		final double turnValue = undeadband(0.5, RobotDriveBase.kDefaultDeadband);
		final double[] expectedBlends = {1.5, 0.5};
		final double[] blends = DrivetrainSubsystem.getBlendedMotorValues(moveValue, turnValue);
		assertArrayEquals(expectedBlends, blends, 0.001);
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

	@Test
	public void testUndeadband() {
		assertEquals(-1, undeadband(applyDeadband(-1, RobotDriveBase.kDefaultDeadband), RobotDriveBase.kDefaultDeadband), 0.00001);
		assertEquals(-0.5, undeadband(applyDeadband(-0.5, RobotDriveBase.kDefaultDeadband), RobotDriveBase.kDefaultDeadband), 0.00001);
		assertEquals(0, undeadband(applyDeadband(0, RobotDriveBase.kDefaultDeadband), RobotDriveBase.kDefaultDeadband), 0.00001);
		assertEquals(0.5, undeadband(applyDeadband(0.5, RobotDriveBase.kDefaultDeadband), RobotDriveBase.kDefaultDeadband), 0.00001);
		assertEquals(1, undeadband(applyDeadband(1, RobotDriveBase.kDefaultDeadband), RobotDriveBase.kDefaultDeadband), 0.00001);
	}

	/** Straight copy-paste from RobotDriveBase. */
	protected double applyDeadband(double value, double deadband) {
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

	//	@Test
	//	public void testGetBlendedMotorValues_Left_Zero_Right_Full() {
	//		final double moveValue = 0;
	//		final double turnValue = 
	//	}
}
