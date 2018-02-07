package org.usfirst.frc.team2175.info;

import java.util.HashMap;
import java.util.Properties;

import org.usfirst.frc.team2175.MotorWrapper;
import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.SolenoidWrapper;
import org.usfirst.frc.team2175.log.LoggableJoystick;
import org.usfirst.frc.team2175.log.LoggableJoystickButton;
import org.usfirst.frc.team2175.log.LoggableSolenoid;
import org.usfirst.frc.team2175.log.LoggableTalonSRX;
import org.usfirst.frc.team2175.log.RobotLogger;
import org.usfirst.frc.team2175.property.PropertiesLoader;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class RobotInfo {
	public static interface ValueContainer {
		public Object get();
	}

	public static final String LEFT_MOTOR_MASTER = "drivetrain.motor.left.master";
	public static final String LEFT_MOTOR_SLAVE1 = "drivetrain.motor.left.slave1";
	public static final String LEFT_MOTOR_SLAVE2 = "drivetrain.motor.left.slave2";
	public static final String RIGHT_MOTOR_MASTER = "drivetrain.motor.right.master";
	public static final String RIGHT_MOTOR_SLAVE1 = "drivetrain.motor.right.slave1";
	public static final String RIGHT_MOTOR_SLAVE2 = "drivetrain.motor.right.slave2";
	public static final String ROLLER_BAR_MOTOR = "roller.bar.motor";
	public static final String ELEVATOR_MOTOR = "elevator.motor1";
	public static final String LEFT_JOYSTICK = "driverstation.joystick.left";
	public static final String RIGHT_JOYSTICK = "driverstation.joystick.right";
	public static final String GAMEPAD = "driverstation.gamepad";
	public static final String CLIMBER_MASTER = "climber.motor.master";
	public static final String CLIMBER_SLAVE = "climber.motor.slave";
	public static final String INTAKE_LEFT_MOTOR = "intake.motor.left";
	public static final String INTAKE_RIGHT_MOTOR = "intake.motor.right";
	public static final String INTAKE_PISTON1 = "intake.piston1";
	public static final String INTAKE_PISTON2 = "intake.piston2";
	private HashMap<String, Object> info;
	private final boolean isComp;
	private Properties botProperties;

	private RobotLogger robotLogger;

	public RobotInfo() {
		ServiceLocator.register(this);
		info = new HashMap<>();
		botProperties = PropertiesLoader.loadProperties("/home/lvuser/bot.properties");
		isComp = Boolean.parseBoolean((String) botProperties.get("isComp"));
		robotLogger = ServiceLocator.get(RobotLogger.class);
		populate();
	}

	private void populate() {
		put(LEFT_MOTOR_MASTER, talon(new WPI_TalonSRX(1)), talon(new WPI_TalonSRX(1)));
		put(LEFT_MOTOR_SLAVE1, victor(new WPI_VictorSPX(11)), talon(new WPI_TalonSRX(11)));
		put(LEFT_MOTOR_SLAVE2, victor(new WPI_VictorSPX(12)), talon(new WPI_TalonSRX(12)));
		put(RIGHT_MOTOR_MASTER, talon(new WPI_TalonSRX(4)), talon(new WPI_TalonSRX(4)));
		put(RIGHT_MOTOR_SLAVE1, talon(new WPI_TalonSRX(2)), talon(new WPI_TalonSRX(2)));
		put(RIGHT_MOTOR_SLAVE2, victor(new WPI_VictorSPX(3)), talon(new WPI_TalonSRX(3)));
		put(ROLLER_BAR_MOTOR, talon(new WPI_TalonSRX(6)), talon(new WPI_TalonSRX(6)));
		put(CLIMBER_MASTER, talon(new WPI_TalonSRX(9)), talon(new WPI_TalonSRX(9)));
		put(CLIMBER_SLAVE, victor(new WPI_VictorSPX(10)), talon(new WPI_TalonSRX(10)));
		put(INTAKE_LEFT_MOTOR, victor(new WPI_VictorSPX(8)), talon(new WPI_TalonSRX(8)));
		put(INTAKE_RIGHT_MOTOR, victor(new WPI_VictorSPX(7)), talon(new WPI_TalonSRX(7)));
		put(ELEVATOR_MOTOR, victor(new WPI_VictorSPX(5)), talon(new WPI_TalonSRX(5)));
		put(INTAKE_PISTON1, () -> new SolenoidWrapper(1), () -> new SolenoidWrapper(1));
		put(INTAKE_PISTON2, () -> new SolenoidWrapper(2), () -> new SolenoidWrapper(2));
		put(LEFT_JOYSTICK, new Joystick(0), new Joystick(0));
		put(RIGHT_JOYSTICK, new Joystick(1), new Joystick(1));
		put(GAMEPAD, new Joystick(2), new Joystick(2));
	}

	private MotorWrapper talon(WPI_TalonSRX talon) {
		return new MotorWrapper(talon);
	}

	private MotorWrapper victor(WPI_VictorSPX victor) {
		return new MotorWrapper(victor);
	}

	private void put(String key, Object comp, Object practice) {
		Object choice = isComp ? comp : practice;
		roboLog(key, choice);
		info.put(key, choice);
	}

	private void put(String key, ValueContainer comp, ValueContainer practice) {
		Object choice = isComp ? comp.get() : practice.get();
		roboLog(key, choice);
		info.put(key, choice);
	}

	private void put(String key, Object value) {
		roboLog(key, value);
	}

	private void roboLog(String key, Object obj) {
		if (obj.getClass() == SolenoidWrapper.class) {
			robotLogger.addLoggable(new LoggableSolenoid(key, (SolenoidWrapper) obj));
		} else if (obj.getClass() == WPI_TalonSRX.class) {
			robotLogger.addLoggable(new LoggableTalonSRX(key, (WPI_TalonSRX) obj));
		} else if (obj.getClass() == Joystick.class) {
			robotLogger.addLoggable(new LoggableJoystick(key, (Joystick) obj));
		} else if (obj.getClass() == JoystickButton.class) {
			robotLogger.addLoggable(new LoggableJoystickButton(key, (JoystickButton) obj));
		}
	}

	public <T> T get(String key) {
		return (T) info.get(key);
	}
}
