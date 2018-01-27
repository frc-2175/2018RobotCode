package team2175.control;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.usfirst.frc.team2175.control.BetterDrive;

public class BetterDriveTest {

	@Test
	public void testBetterDrive_StraightBack() {
		double[] speed = BetterDrive.calculateSpeeds(-1, 0);
		assertEquals(-1, speed[0], 0);
		assertEquals(-1, speed[1], 0);
	}

	@Test
	public void testBetterDrive_StraightForward() {
		double[] speed = BetterDrive.calculateSpeeds(1, 0);
		assertEquals(1, speed[0], 0);
		assertEquals(1, speed[1], 0);
	}

	@Test
	public void testBetterDrive_CurvingRight() {
		double[] speed = BetterDrive.calculateSpeeds(1, .5);
		assertEquals(1, speed[0], 0);
		assertEquals(0.333, speed[1], 0.001);
	}

	@Test
	public void testBetterDrive_CurvingLeft() {
		double[] speed = BetterDrive.calculateSpeeds(1, -.5);
		assertEquals(0.333, speed[0], 0.01);
		assertEquals(1, speed[1], 0);
	}

	@Test
	public void testBetterDrive_CurvingRightSlowly() {
		double[] speed = BetterDrive.calculateSpeeds(0.5, .5);
		assertEquals(.75, speed[0], 0.001);
		assertEquals(.25, speed[1], 0.001);
	}

	@Test
	public void testBetterDrive_CurvingLeftSlowly() {
		double[] speed = BetterDrive.calculateSpeeds(0.5, -.5);
		assertEquals(0.25, speed[0], 0.01);
		assertEquals(0.75, speed[1], 0);
	}

	@Test
	public void testBetterDrive_CurvingRightBackwards() {
		double[] speed = BetterDrive.calculateSpeeds(-1, .5);
		assertEquals(-1, speed[0], 0);
		assertEquals(-0.333, speed[1], 0.001);
	}

	@Test
	public void testBetterDrive_CurvingLeftBackwards() {
		double[] speed = BetterDrive.calculateSpeeds(-1, -.5);
		assertEquals(-0.333, speed[0], 0.001);
		assertEquals(-1, speed[1], 0);
	}

	@Test
	public void testBetterDrive_CurvingRightSlowlyBackwards() {
		double[] speed = BetterDrive.calculateSpeeds(-0.5, .5);
		assertEquals(-.75, speed[0], 0);
		assertEquals(-.25, speed[1], 0);
	}

	@Test
	public void testBetterDrive_CurvingLeftSlowlyBackwards() {
		double[] speed = BetterDrive.calculateSpeeds(-0.5, -.5);
		assertEquals(-0.25, speed[0], 0);
		assertEquals(-0.75, speed[1], 0);
	}

	@Test
	public void testBetterDrive_JustTurning() {
		double[] speed = BetterDrive.calculateSpeeds(0, -1);
		assertEquals(0, speed[0], 0);
		assertEquals(0, speed[1], 0);
	}
}
