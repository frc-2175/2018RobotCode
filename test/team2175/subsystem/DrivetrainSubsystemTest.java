package team2175.subsystem;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

import team2175.BaseTest;

public class DrivetrainSubsystemTest extends BaseTest {
	@Test
	public void testGetBlendedMotorValues_Left_Full_Right_Half() {
		final double moveValue = 1;
		final double turnValue = 0.5;
		final double[] expectedBlends = {1.5, 0.5};
		final double[] blends = DrivetrainSubsystem.getBlendedMotorValues(moveValue, turnValue);
		assertEquals(expectedBlends, blends);
	}
	
	public static double undeadband(double value, double deadband) {
		if (value < 0) {
			double t = -value;
			return DrivetrainSubsystem.lerp(-deadband, -1, t);
		} else if (value > 1) {
			double t = value;
			return DrivetrainSubsystem.lerp(deadband, 1, t);
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
