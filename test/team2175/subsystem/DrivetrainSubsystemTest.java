package team2175.subsystem;

import org.junit.Test;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.drive.RobotDriveBase;

import static org.junit.Assert.assertArrayEquals;
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
			return -DrivetrainSubsystem.lerp(deadband, 1, t);
		} else {
			return 0;
		}
	}
	
//	@Test
//	public void testGetBlendedMotorValues_Left_Zero_Right_Full() {
//		final double moveValue = 0;
//		final double turnValue = 
//	}
}
