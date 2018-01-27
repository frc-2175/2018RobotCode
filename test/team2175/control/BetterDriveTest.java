package team2175.control;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.usfirst.frc.team2175.control.BetterDrive;

public class BetterDriveTest {

	@Test
	public void testBetterDrive_StraightBack() {
		double[] speed = BetterDrive.getMotorValues(-1, 0);
		assertEquals(-1, speed[0], 0);
		assertEquals(-1, speed[1], 0);
	}

	@Test
	public void testBetterDrive_StraightForward() {
		double[] speed = BetterDrive.getMotorValues(1, 0);
		assertEquals(1, speed[0], 0);
		assertEquals(1, speed[1], 0);
	}

	@Test
	public void testBetterDrive_CurvingRight() {
		double[] speed = BetterDrive.getMotorValues(1, .5);
		assertEquals(1, speed[0], 0);
		assertEquals(0.333, speed[1], 0.01);
	}

	@Test
	public void testBetterDrive_CurvingLeft() {
		double[] speed = BetterDrive.getMotorValues(1, -.5);
		assertEquals(0.333, speed[0], 0.01);
		assertEquals(1, speed[1], 0);
	}

	@Test
	public void testBetterDrive_CurvingRightSlowly() {
		double[] speed = BetterDrive.getMotorValues(0.5, .5);
		assertEquals(1, speed[0], 0);
		assertEquals(0.333, speed[1], 0.01);
	}

	@Test
	public void testBetterDrive_CurvingLeftSlowly() {
		double[] speed = BetterDrive.getMotorValues(0.5, -.5);
		assertEquals(0.333, speed[0], 0.01);
		assertEquals(1, speed[1], 0);
	}

	@Test
	public void testBetterDrive_JustTurning() {
		double[] speed = BetterDrive.getMotorValues(0, -1);
		assertEquals(0, speed[0], 0);
		assertEquals(0, speed[1], 0);
	}
}
