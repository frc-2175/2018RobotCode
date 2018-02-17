package org.usfirst.frc.team2175.control;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.command.single.MoveIntakeDownCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeMiddleCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeUpCommand;
import org.usfirst.frc.team2175.command.single.ShiftCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeInCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutSlowCommand;

public class JoystickEventMapper {

	public JoystickEventMapper() {
		DryverStation driverStation = ServiceLocator.get(DryverStation.class);
		driverStation.getShiftButton().whileHeld(new ShiftCommand());
		driverStation.getIntakeSpinInButton().whileHeld(new SpinIntakeInCommand());
		driverStation.getIntakeSpinOutButton().whileHeld(new SpinIntakeOutCommand());
		driverStation.getIntakeActuateFullButton().whenPressed(new MoveIntakeDownCommand());
		driverStation.getIntakeActuateHalfButton().whenPressed(new MoveIntakeMiddleCommand());
		driverStation.getIntakeActuateNoneButton().whenPressed(new MoveIntakeUpCommand());
		driverStation.getIntakeSpinOutSlowButton().whenPressed(new SpinIntakeOutSlowCommand());
	}
}
