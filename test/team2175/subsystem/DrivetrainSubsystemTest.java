package team2175.subsystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.drive.RobotDriveBase;
import team2175.BaseTest;

public class DrivetrainSubsystemTest extends BaseTest {
	@Test
	public void testGetBlendedMotorValues_LeftFullRightHalf() {
		final double moveValue = DrivetrainSubsystem.undeadband(1, RobotDriveBase.kDefaultDeadband);
		final double turnValue = DrivetrainSubsystem.undeadband(0.5, RobotDriveBase.kDefaultDeadband);
		final double[] expectedBlends = {1.5, 0.5};
		final double[] blends = DrivetrainSubsystem.getBlendedMotorValues(moveValue, turnValue);
		assertArrayEquals(expectedBlends, blends, 0.001);
	}
	
	@Test
	public void testGetBlendedMotorValues_LeftZeroRightFull() {
		final double moveValue = 0;
		final double turnValue = 1;
		final double[] expectedBlends = { 1, -1 };
		final double[] blends = DrivetrainSubsystem.getBlendedMotorValues(moveValue, turnValue);
		assertArrayEquals(expectedBlends, blends, 0.001);
	}
	
	@Test
	public void testGetBlendedMotorValues_LeftSmidgeonRightFull() {
		final double moveValue = DrivetrainSubsystem.undeadband(0.05, 0.02);
		final double turnValue = 1;
		final double[] expectedBlends = {0.55, -0.475};
		final double[] blends = DrivetrainSubsystem.getBlendedMotorValues(moveValue, turnValue);
		assertArrayEquals(expectedBlends, blends, 0.001);
	}

	@Test
	public void testUndeadband() {
		assertEquals(-1, DrivetrainSubsystem.undeadband(DrivetrainSubsystem.deadband(-1, RobotDriveBase.kDefaultDeadband), RobotDriveBase.kDefaultDeadband), 0.00001);
		assertEquals(-0.5, DrivetrainSubsystem.undeadband(DrivetrainSubsystem.deadband(-0.5, RobotDriveBase.kDefaultDeadband), RobotDriveBase.kDefaultDeadband), 0.00001);
		assertEquals(0, DrivetrainSubsystem.undeadband(DrivetrainSubsystem.deadband(0, RobotDriveBase.kDefaultDeadband), RobotDriveBase.kDefaultDeadband), 0.00001);
		assertEquals(0.5, DrivetrainSubsystem.undeadband(DrivetrainSubsystem.deadband(0.5, RobotDriveBase.kDefaultDeadband), RobotDriveBase.kDefaultDeadband), 0.00001);
		assertEquals(1, DrivetrainSubsystem.undeadband(DrivetrainSubsystem.deadband(1, RobotDriveBase.kDefaultDeadband), RobotDriveBase.kDefaultDeadband), 0.00001);
	}
}
