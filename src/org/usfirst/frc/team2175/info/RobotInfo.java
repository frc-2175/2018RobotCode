package org.usfirst.frc.team2175.info;

import java.util.HashMap;
import java.util.Properties;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.property.PropertiesLoader;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;

public class RobotInfo {
	public static final String LEFT_MOTOR_MASTER = "drivetrain.motor.left.master";
	public static final String LEFT_MOTOR_SLAVE1 = "drivetrain.motor.left.slave1";
	public static final String LEFT_MOTOR_SLAVE2 = "drivetrain.motor.left.slave2";
	public static final String RIGHT_MOTOR_MASTER = "drivetrain.motor.right.master";
	public static final String RIGHT_MOTOR_SLAVE1 = "drivetrain.motor.right.slave1";
	public static final String RIGHT_MOTOR_SLAVE2 = "drivetrain.motor.right.slave2";
	public static final String ROLLER_BAR_MOTOR = "roller.bar.motor";
	public static final String ELEVATOR_MOTOR = "elevator.motor1";
	public static final String ELEVATOR_MOTOR2 = "elevator.motor2";
	public static final String LEFT_JOYSTICK = "driverstation.joystick.left";
	public static final String RIGHT_JOYSTICK = "driverstation.joystick.right";
	public static final String GAMEPAD = "driverstation.gamepad";
	public static final String CLIMBER_LEFT_MOTOR = "climber.motor.left";
	public static final String CLIMBER_RIGHT_MOTOR = "climber.motor.right";
	public static final String INTAKE_LEFT_MOTOR = "intake.motor.left";
	public static final String INTAKE_RIGHT_MOTOR = "intake.motor.right";
	
	private HashMap<String, Object> info;
	private final boolean isComp;
	private Properties botProperties;
	
	public RobotInfo() {
		ServiceLocator.register(this);
		info = new HashMap<>();
		botProperties = PropertiesLoader.loadProperties("/home/lvuser/bot.properties");
		isComp = Boolean.parseBoolean((String) botProperties.get("isComp"));
		populate();
	}
	
	private void populate() {
		put(LEFT_MOTOR_MASTER, new WPI_TalonSRX(1), new WPI_TalonSRX(1));
		put(LEFT_MOTOR_SLAVE1, new WPI_TalonSRX(2), new WPI_TalonSRX(2));
		put(LEFT_MOTOR_SLAVE2, new WPI_TalonSRX(3), new WPI_TalonSRX(3));
		put(RIGHT_MOTOR_MASTER, new WPI_TalonSRX(4), new WPI_TalonSRX(4));
		put(RIGHT_MOTOR_SLAVE1, new WPI_TalonSRX(5), new WPI_TalonSRX(5));
		put(RIGHT_MOTOR_SLAVE2, new WPI_TalonSRX(6), new WPI_TalonSRX(6));
		put(ROLLER_BAR_MOTOR, new WPI_TalonSRX(7), new WPI_TalonSRX(7));
		put(CLIMBER_LEFT_MOTOR, new WPI_TalonSRX(8), new WPI_TalonSRX(8));
		put(CLIMBER_RIGHT_MOTOR, new WPI_TalonSRX(9), new WPI_TalonSRX(9));
		put(INTAKE_LEFT_MOTOR, new WPI_TalonSRX(10), new WPI_TalonSRX(10));
		put(INTAKE_RIGHT_MOTOR, new WPI_TalonSRX(11), new WPI_TalonSRX(11));
		put(LEFT_JOYSTICK, new Joystick(0), new Joystick(0));
		put(RIGHT_JOYSTICK, new Joystick(1), new Joystick(1));
		put(GAMEPAD, new Joystick(2), new Joystick(2));
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
