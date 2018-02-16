package org.usfirst.frc.team2175;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedController;

public class MotorWrapper {
	private WPI_TalonSRX talon;
	private WPI_VictorSPX victor;
	private boolean isTalon;

	public MotorWrapper(WPI_TalonSRX talon) {
		this.talon = talon;
		isTalon = true;
	}

	public MotorWrapper(WPI_VictorSPX victor) {
		this.victor = victor;
		isTalon = false;
	}

	public void set(double number) {
		if (isTalon) {
			talon.set(number);
		} else {
			victor.set(number);
		}
	}

	public void follow(MotorWrapper masterMotor) {
		(isTalon ? talon : victor).follow((IMotorController) masterMotor.getMotor());
	}

	public void configSelectedFeedbackSensor(FeedbackDevice device, int pidIdx, int timeoutMs) {
		if (isTalon) {
			talon.configSelectedFeedbackSensor(device, pidIdx, timeoutMs);
		}
	}

	public void setSelectedSensorPosition(int sensorPos, int pidIdx, int timeoutMs) {
		if (isTalon) {
			talon.setSelectedSensorPosition(sensorPos, pidIdx, timeoutMs);
		}
	}

	public int getSelectedSensorPosition(int pidIdx) {
		if (isTalon) {
			return talon.getSelectedSensorPosition(pidIdx);
		} else {
			return victor.getSelectedSensorPosition(pidIdx);
		}
	}

	public void setInverted(boolean isInverted) {
		if (isTalon) {
			talon.setInverted(isInverted);
		} else {
			victor.setInverted(isInverted);
		}
	}

	public SpeedController getMotor() {
		return isTalon ? talon : victor;
	}

}
