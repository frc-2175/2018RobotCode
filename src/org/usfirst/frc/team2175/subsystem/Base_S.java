package org.usfirst.frc.team2175.subsystem;

import org.usfirst.frc.team2175.ServiceLocator;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Base_S extends Subsystem {
	public Base_S() {
		ServiceLocator.register(this);
	}

//	protected CANTalon makeMotor(String id) {
//		String data[] = infoLocator.getWiringInfo(id).split(",");
//		CANTalon motor = new CANTalon(parseInt(data[0]));
//		motor.reverseOutput(data[1].contains("true"));
//		return motor;
//	}
//
//	private int parseInt(String port) {
//		return Integer.parseInt(port.trim());
//	}
//
//	protected Encoder makeEncoder(String id) {
//		String data[] = infoLocator.getWiringInfo(id).split(",");
//		Encoder enc = new Encoder(parseInt(data[0]), parseInt(data[1]));
//		enc.setDistancePerPulse(parseInt(data[2]));
//		enc.setReverseDirection(data[3].contains("true"));
//		return enc;
//	}
//
//	protected DigitalInput makeDIO(String id) {
//		return new DigitalInput(parseInt(infoLocator.getWiringInfo(id)));
//	}
//
//	protected DoubleSolenoid makeSolenoid(String id) {
//		String data[] = infoLocator.getWiringInfo(id).split(",");
//		return new DoubleSolenoid(parseInt(data[0]), parseInt(data[1]));
//	}
//
//	protected double getSpeed(String id) {
//		return infoLocator.getBehaviorInfo(id);
//	}
//
//	protected void setSlave(final CANTalon slave, final CANTalon master) {
//		slave.changeControlMode(CANTalon.TalonControlMode.Follower);
//		slave.set(master.getDeviceID());
//	}
//
	@Override
	public void setDefaultCommand(final Command command) {
		super.setDefaultCommand(command);
	}

	@Override
	protected void initDefaultCommand() {
	}
}
