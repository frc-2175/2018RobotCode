package org.usfirst.frc.team2175.subsystem;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.info.RobotInfo;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class IntakeSubsystem extends BaseSubsystem {
	private WPI_TalonSRX rollerBar;
	private RobotInfo robotInfo;
	public IntakeSubsystem () {
		robotInfo = ServiceLocator.get(RobotInfo.class);
		rollerBar = robotInfo.get(RobotInfo.INTAKE_MOTOR);
	}
	public void runRollerBarIn() {
		rollerBar.set(0.7);
	}
	public void runRollerBarOut() {
		rollerBar.set(-0.5);
	} 
	public void stopRollerBar() {
		rollerBar.set(0);
	}

}
