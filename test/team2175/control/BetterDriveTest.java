package team2175.control;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.usfirst.frc.team2175.control.BetterDrive;

public class BetterDriveTest {

	@Test
	public void testBetterDrive_StraightBack() {
		double[] speed = BetterDrive.getMotorValues(-1, 0);
		assertEquals(speed[0], -1);
		assertEquals(speed[1], -1);
	}

	@Test
	public void testBetterDrive_StraightForward() {
		double[] speed = BetterDrive.getMotorValues(1, 0);
		assertEquals(speed[0], 1);
		assertEquals(speed[1], 1);
	}

	@Test
	public void testBetterDrive_CurvingRight() {
		double[] speed = BetterDrive.getMotorValues(1, .5);
		assertEquals(speed[0], 1);
		assertEquals(speed[1], 0.333, 0.01);
	}

	@Test
	public void testBetterDrive_CurvingLeft() {
		double[] speed = BetterDrive.getMotorValues(1, -.5);
		assertEquals(speed[0], 0.333, 0.01);
		assertEquals(speed[1], 1);
	}

	@Test
	public void testBetterDrive_CurvingRightSlowly() {
		double[] speed = BetterDrive.getMotorValues(0.5, .5);
		assertEquals(speed[0], 1);
		assertEquals(speed[1], 0.333, 0.01);
	}

	@Test
	public void testBetterDrive_CurvingLeftSlowly() {
		double[] speed = BetterDrive.getMotorValues(0.5, -.5);
		assertEquals(speed[0], 0.333, 0.01);
		assertEquals(speed[1], 1);
	}

	@Test
	public void testBetterDrive_JustTurning() {
		double[] speed = BetterDrive.getMotorValues(0, -1);
		assertTrue(speed[0] == 0);
		assertTrue(speed[1] == 0);
	}
}
