package org.usfirst.frc.team2175.info;

import java.util.Properties;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.property.PropertiesLoader;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardInfo {
	private static final String PREFIX = "AutoPopulate/";
	private final boolean isComp;
	public static final String ROLLER_IN_SPEED = "roller.in";
	public static final String ROLLER_OUT_SPEED = "roller.out";
	public static final String INTAKE_IN_SPEED = "intake.in";
	public static final String INTAKE_OUT_SPEED = "intake.out";
	public static final String RUN_ELEVATOR_UP_SPEED = "elevator.up";
	public static final String RUN_ELEVATOR_DOWN_SPEED = "elevator.down";
	public static final String ELEVATOR_MAX_UP_SPEED = "elevator.max.up";
	public static final String ELEVATOR_MAX_DOWN_SPEED = "elevator.max.down";
	public static final String POSITIVE_DEADBAND = "deadband.positive";
	public static final String NEGATIVE_DEADBAND = "deadband.negative";
	public static final String INTAKE_TURN_CUBE_IN_SPEED = "turncubein";
	public static final String INTAKE_TURN_CUBE_OUT_SPEED = "turncubeout";
	public static final String ELEVATOR_PRECISION_MODE = "elevator.precisionmode";
	public static final String TURN_CORRECTION = "turn.correction";
	public static final String DRIVE_CURVE_MAX_SPEED = "drivecurve.maxspeed";
	public static final String DRIVE_STRAIGHT_ACCELERATION_RATE = "drivestraight.acceleration";
	public static final String INTAKE_SLOW_SPEED = "intake.speed.slow";
	public static final String CLIMBER_UP_SPEED = "climber.speed.up";
	public static final String CLIMBER_DOWN_SPEED = "climber.speed.down";

	public SmartDashboardInfo() {
		ServiceLocator.register(this);
		Properties botProperties = PropertiesLoader.loadProperties("/home/lvuser/bot.properties");
		isComp = Boolean.parseBoolean((String) botProperties.get("isComp"));
		populate();
	}

	private void populate() {
		putNumber(ROLLER_IN_SPEED, -0.5, -0.5);
		putNumber(ROLLER_OUT_SPEED, 0.4, 0.4);
		putNumber(INTAKE_IN_SPEED, 0.7, 0.7);
		putNumber(INTAKE_OUT_SPEED, -0.7, -0.7);
		putNumber(ELEVATOR_MAX_UP_SPEED, 1.0, 1.0);
		putNumber(ELEVATOR_MAX_DOWN_SPEED, 0.8, 0.8);
		putNumber(POSITIVE_DEADBAND, 0.1, 0.1);
		putNumber(NEGATIVE_DEADBAND, -0.05, -0.01);
		putNumber(INTAKE_TURN_CUBE_IN_SPEED, 0.5, 0.5);
		putNumber(INTAKE_TURN_CUBE_OUT_SPEED, 0.25, 0.25);
		putNumber(ELEVATOR_PRECISION_MODE, 0.5, 0.5);
		putNumber(TURN_CORRECTION, 45, 45);
		putNumber(DRIVE_CURVE_MAX_SPEED, 0.7, 0.7);
		putNumber(DRIVE_STRAIGHT_ACCELERATION_RATE, 1, 1);
		putNumber(INTAKE_SLOW_SPEED, -0.4, -0.4);
		putNumber(CLIMBER_UP_SPEED, 0.7, 0.7);
		putNumber(CLIMBER_DOWN_SPEED, -0.5, -0.5);
	}

	public void putBoolean(String key, boolean comp, boolean practice) {
		SmartDashboard.putBoolean(PREFIX + key, isComp ? comp : practice);
	}

	public void putNumber(String key, double comp, double practice) {
		SmartDashboard.putNumber(PREFIX + key, isComp ? comp : practice);
	}

	public void putString(String key, String comp, String practice) {
		SmartDashboard.putString(PREFIX + key, isComp ? comp : practice);
	}

	public void putBooleanArray(String key, boolean[] comp, boolean[] practice) {
		SmartDashboard.putBooleanArray(PREFIX + key, isComp ? comp : practice);
	}

	public void putNumberArray(String key, double[] comp, double[] practice) {
		SmartDashboard.putNumberArray(PREFIX + key, isComp ? comp : practice);
	}

	public void putStringArray(String key, String[] comp, String[] practice) {
		SmartDashboard.putStringArray(PREFIX + key, isComp ? comp : practice);
	}

	public void putRaw(String key, byte[] comp, byte[] practice) {
		SmartDashboard.putRaw(PREFIX + key, isComp ? comp : practice);
	}

	public boolean getBoolean(String key) {
		return SmartDashboard.getBoolean(PREFIX + key, false);
	}

	public double getNumber(String key) {
		return SmartDashboard.getNumber(PREFIX + key, 0);
	}

	public String getString(String key) {
		return SmartDashboard.getString(PREFIX + key, "");
	}

	public boolean[] getBooleanArray(String key) {
		boolean[] defaultArray = { false };
		return SmartDashboard.getBooleanArray(PREFIX + key, defaultArray);
	}

	public double[] getNumberArray(String key) {
		double[] defaultArray = { 0 };
		return SmartDashboard.getNumberArray(PREFIX + key, defaultArray);
	}

	public String[] getStringArray(String key) {
		String[] defaultArray = { "" };
		return SmartDashboard.getStringArray(PREFIX + key, defaultArray);
	}

	public byte[] getRaw(String key) {
		byte[] defaultArray = { 0 };
		return SmartDashboard.getRaw(PREFIX + key, defaultArray);
	}
}
