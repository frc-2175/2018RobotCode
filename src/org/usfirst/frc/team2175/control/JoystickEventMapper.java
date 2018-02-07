package org.usfirst.frc.team2175.control;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.command.autonomous.SpinIntakeInCommand;
import org.usfirst.frc.team2175.command.autonomous.SpinIntakeOutCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeDownCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeMiddleCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeUpCommand;

public class JoystickEventMapper {

	public JoystickEventMapper() {
		DryverStation driverStation = ServiceLocator.get(DryverStation.class);
		driverStation.getShiftButton().whileHeld(new ShiftCommand());
		driverStation.getIntakeSpinInButton().whileHeld(new SpinIntakeInCommand());
		driverStation.getIntakeSpinOutButton().whileHeld(new SpinIntakeOutCommand());
		driverStation.getIntakeActuateFullButton().whenPressed(new MoveIntakeUpCommand());
		driverStation.getIntakeActuateHalfButton().whenPressed(new MoveIntakeMiddleCommand());
		driverStation.getIntakeActuateNoneButton().whenPressed(new MoveIntakeDownCommand());
	}
}
