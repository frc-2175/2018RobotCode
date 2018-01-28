package team2175.control;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;

public class DrivetrainSubsystemTest {
	@Test
	public void testGetBlendedMotorValues_Left_Full_Right_Half() {
		final double moveValue = 1;
		final double turnValue = 0.5;
		final double[] expectedBlends = {1.5, 0.5};
		final double[] blends = DrivetrainSubsystem.getBlendedMotorValues(moveValue, turnValue);
		assertEquals(expectedBlends, blends);
	}
	
//	@Test
//	public void testGetBlendedMotorValues_Left_Zero_Right_Full() {
//		final double moveValue = 0;
//		final double turnValue = 
//	}
}
