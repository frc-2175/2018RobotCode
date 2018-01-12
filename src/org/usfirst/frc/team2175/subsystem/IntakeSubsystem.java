package org.usfirst.frc.team2175.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class IntakeSubsystem extends Base_S {
	private WPI_TalonSRX rollerBar;
	public IntakeSubsystem () {
		rollerBar = new WPI_TalonSRX(6);
	}
	public void runRollerBarIn () {
		rollerBar.set(0.7);
	}
	public void runRollerBarOut () {
		rollerBar.set(-0.5);
	} 

}
