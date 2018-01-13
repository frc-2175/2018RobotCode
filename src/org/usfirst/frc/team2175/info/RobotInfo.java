package org.usfirst.frc.team2175.info;

import java.util.HashMap;
import java.util.Properties;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.property.PropertiesLoader;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class RobotInfo {
	public static final String LEFT_MOTOR_MASTER = "drivetrain.motor.left.master";
	public static final String LEFT_MOTOR_SLAVE1 = "drivetrain.motor.left.slave1";
	public static final String LEFT_MOTOR_SLAVE2 = "drivetrain.motor.left.slave2";
	public static final String RIGHT_MOTOR_MASTER = "drivetrain.motor.right.master";
	public static final String RIGHT_MOTOR_SLAVE1 = "drivetrain.motor.right.slave1";
	public static final String RIGHT_MOTOR_SLAVE2 = "drivetrain.motor.right.slave2";
	public static final String INTAKE_MOTOR = "intake.motor";
	private HashMap<String, Object> info;
	private final boolean isComp;
	private Properties botProperties;
	
	public RobotInfo() {
		ServiceLocator.register(this);
		info = new HashMap<>();
		botProperties = PropertiesLoader.loadProperties("/home/lvuser/bot.properties");
		isComp = (boolean) botProperties.get("isComp");
		populate();
	}
	
	private void populate() {
		put(LEFT_MOTOR_MASTER, new WPI_TalonSRX(0), new WPI_TalonSRX(1));
		put(LEFT_MOTOR_SLAVE1, new WPI_TalonSRX(5), new WPI_TalonSRX(3));
		put(LEFT_MOTOR_SLAVE2, new WPI_TalonSRX(2), new WPI_TalonSRX(4));
		put(RIGHT_MOTOR_MASTER, new WPI_TalonSRX(4), new WPI_TalonSRX(6));
		put(RIGHT_MOTOR_SLAVE1, new WPI_TalonSRX(7), new WPI_TalonSRX(8));
		put(RIGHT_MOTOR_SLAVE2, new WPI_TalonSRX(9), new WPI_TalonSRX(3));
		put(INTAKE_MOTOR, new WPI_TalonSRX(1), new WPI_TalonSRX(3));
	}
	
	private void put(String key, Object comp, Object practice) {
		if(isComp) {
			info.put(key, comp);
		} else {
			info.put(key, practice);
		}
	}
	
	public <T> T get(String key) {
		return (T) info.get(key);
	}
}
