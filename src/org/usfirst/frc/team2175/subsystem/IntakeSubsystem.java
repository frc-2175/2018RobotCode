package org.usfirst.frc.team2175.subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class IntakeSubsystem {
	private WPI_TalonSRX rightIntakeMotor;
	private WPI_TalonSRX leftIntakeMotor;

	public IntakeSubsystem() {
		rightIntakeMotor = new WPI_TalonSRX(5);
		leftIntakeMotor = new WPI_TalonSRX(6);
	}

}
