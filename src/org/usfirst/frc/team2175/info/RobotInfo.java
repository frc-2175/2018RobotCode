package org.usfirst.frc.team2175.info;

import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Logger;

import org.usfirst.frc.team2175.MotorWrapper;
import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.SolenoidWrapper;
import org.usfirst.frc.team2175.UltrasonicWrapper;
import org.usfirst.frc.team2175.log.LoggableAnalogInput;
import org.usfirst.frc.team2175.log.LoggableJoystick;
import org.usfirst.frc.team2175.log.LoggableJoystickButton;
import org.usfirst.frc.team2175.log.LoggableNavX;
import org.usfirst.frc.team2175.log.LoggableSolenoid;
import org.usfirst.frc.team2175.log.LoggableTalonSRX;
import org.usfirst.frc.team2175.log.LoggableUltrasonic;
import org.usfirst.frc.team2175.log.LoggableVictorSPX;
import org.usfirst.frc.team2175.log.RobotLogger;
import org.usfirst.frc.team2175.property.PropertiesLoader;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class RobotInfo {
	private Logger log = RobotLogger.getLogger(this);

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
	public static final String ELEVATOR_MASTER_MOTOR = "elevator.motor.master";
	public static final String ELEVATOR_SLAVE_MOTOR = "elevator.motor.slave";
	public static final String LEFT_JOYSTICK = "driverstation.joystick.left";
	public static final String RIGHT_JOYSTICK = "driverstation.joystick.right";
	public static final String GAMEPAD = "driverstation.gamepad";
	public static final String CLIMBER_MASTER = "climber.motor.master";
	public static final String INTAKE_LEFT_MOTOR = "intake.motor.left";
	public static final String INTAKE_RIGHT_MOTOR = "intake.motor.right";
	public static final String INTAKE_PISTON1 = "intake.piston1";
	public static final String INTAKE_PISTON2 = "intake.piston2";
	public static final String DRIVE_SHIFTERS = "drive.shifters";
	public static final String PSI_SENSOR = "psi.sensor";
	public static final String LEFT_ULTRA = "left.sensor.ultra";
	public static final String RIGHT_ULTRA = "right.sensor.ultra";

	private HashMap<String, Object> info;
	private final boolean isComp;

	private final RobotLogger robotLogger;

	public RobotInfo() {
		log.info(getClass().getName() + "was constructed");
		ServiceLocator.register(this);
		info = new HashMap<>();
		Properties botProperties = PropertiesLoader.loadProperties("/home/lvuser/bot.properties");
		isComp = Boolean.parseBoolean((String) botProperties.get("isComp"));
		robotLogger = ServiceLocator.get(RobotLogger.class);
		populate();
	}

	private void populate() {
		put(LEFT_MOTOR_MASTER, talon(new WPI_TalonSRX(1)));
		put(LEFT_MOTOR_SLAVE1, victor(new WPI_VictorSPX(11)), talon(new WPI_TalonSRX(11)));
		put(LEFT_MOTOR_SLAVE2, victor(new WPI_VictorSPX(12)), talon(new WPI_TalonSRX(12)));
		put(RIGHT_MOTOR_MASTER, talon(new WPI_TalonSRX(4)));
		put(RIGHT_MOTOR_SLAVE1, victor(new WPI_VictorSPX(2)), talon(new WPI_TalonSRX(20)));
		put(RIGHT_MOTOR_SLAVE2, victor(new WPI_VictorSPX(3)), talon(new WPI_TalonSRX(3)));
		put(ROLLER_BAR_MOTOR, victor(new WPI_VictorSPX(6)), talon(new WPI_TalonSRX(6)));
		put(CLIMBER_MASTER, victor(new WPI_VictorSPX(10)), talon(new WPI_TalonSRX(4)));
		put(INTAKE_LEFT_MOTOR, talon(new WPI_TalonSRX(8)));
		put(INTAKE_RIGHT_MOTOR, talon(new WPI_TalonSRX(7)));
		put(ELEVATOR_MASTER_MOTOR, talon(new WPI_TalonSRX(5)));
		put(ELEVATOR_SLAVE_MOTOR, victor(new WPI_VictorSPX(9)), talon(new WPI_TalonSRX(9)));
		put(INTAKE_PISTON1, () -> new SolenoidWrapper(0, 1), () -> new SolenoidWrapper(0, 1));
		put(INTAKE_PISTON2, () -> new SolenoidWrapper(2, 3), () -> new SolenoidWrapper(2, 3));
		put(DRIVE_SHIFTERS, () -> new SolenoidWrapper(4), () -> new SolenoidWrapper(4));
		put(PSI_SENSOR, () -> new AnalogInput(0), () -> new AnalogInput(0));
		put(LEFT_JOYSTICK, new Joystick(0));
		put(RIGHT_JOYSTICK, new Joystick(1));
		put(GAMEPAD, new Joystick(2));
		put(LEFT_ULTRA, () -> new UltrasonicWrapper(1, false), () -> new UltrasonicWrapper(1, false));
		put(RIGHT_ULTRA, () -> new UltrasonicWrapper(2, true), () -> new UltrasonicWrapper(2, true));
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
		info.put(key, value);
	}

	private void roboLog(String key, Object obj) {
		if (obj.getClass() == SolenoidWrapper.class) {
			robotLogger.addLoggable(new LoggableSolenoid(key, (SolenoidWrapper) obj));
		} else if (obj.getClass() == WPI_TalonSRX.class) {
			robotLogger.addLoggable(new LoggableTalonSRX(key, (WPI_TalonSRX) obj));
		} else if (obj.getClass() == WPI_VictorSPX.class) {
			robotLogger.addLoggable(new LoggableVictorSPX(key, (WPI_VictorSPX) obj));
		} else if (obj.getClass() == Joystick.class) {
			robotLogger.addLoggable(new LoggableJoystick(key, (Joystick) obj));
		} else if (obj.getClass() == JoystickButton.class) {
			robotLogger.addLoggable(new LoggableJoystickButton(key, (JoystickButton) obj));
		} else if (obj.getClass() == AnalogInput.class) {
			robotLogger.addLoggable(new LoggableAnalogInput(key, (AnalogInput) obj));
		} else if (obj.getClass() == UltrasonicWrapper.class) {
			robotLogger.addLoggable(new LoggableUltrasonic(key, (UltrasonicWrapper) obj));
		} else if (obj.getClass() == MotorWrapper.class) {
			MotorWrapper mw = (MotorWrapper) obj;
			roboLog(key, mw.getMotor());
		} else if (obj.getClass() == AHRS.class) {
			robotLogger.addLoggable(new LoggableNavX((AHRS) obj, key));
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		return (T) info.get(key);
	}
}
