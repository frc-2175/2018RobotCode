package org.usfirst.frc.team2175.control;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.command.single.MoveElevatorPresetLowCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeDownCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeMiddleCommand;
import org.usfirst.frc.team2175.command.single.MoveIntakeUpCommand;
import org.usfirst.frc.team2175.command.single.ShiftCommand;
import org.usfirst.frc.team2175.command.single.SpinClimberViaButtonCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeInCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeInDriverCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutCommand;
import org.usfirst.frc.team2175.command.single.SpinIntakeOutSlowCommand;
import org.usfirst.frc.team2175.command.single.ToggleIntakeDownAndMiddleCommand;

public class JoystickEventMapper {

	public JoystickEventMapper() {
		DryverStation driverStation = ServiceLocator.get(DryverStation.class);
		driverStation.getShiftButton().whileHeld(new ShiftCommand());
		driverStation.getIntakeSpinInButton().whileHeld(new SpinIntakeInCommand());
		driverStation.getIntakeSpinOutButton().whileHeld(new SpinIntakeOutCommand());
		driverStation.getIntakeActuateFullButton().whenPressed(new MoveIntakeDownCommand());
		driverStation.getIntakeActuateHalfButton().whenPressed(new MoveIntakeMiddleCommand());
		driverStation.getElevatorPresetLowButton().whenPressed(new MoveElevatorPresetLowCommand());
		driverStation.getIntakeActuateNoneButton().whenPressed(new MoveIntakeUpCommand());
		driverStation.getIntakeSpinOutSlowButton().whileHeld(new SpinIntakeOutSlowCommand());
		driverStation.getClimberRunButton().whileHeld(new SpinClimberViaButtonCommand(true));
		driverStation.getClimberRunOutButton().whileHeld(new SpinClimberViaButtonCommand(false));
		driverStation.getDriverSpinOutButton().whileHeld(new SpinIntakeOutCommand());
		driverStation.getDriverSpinOutSlowButton().whileHeld(new SpinIntakeOutSlowCommand());
		driverStation.getDriverSpinInButton().whileHeld(new SpinIntakeInDriverCommand());
		driverStation.getDriverActuateDownButton().whileHeld(new ToggleIntakeDownAndMiddleCommand());
		driverStation.getActuateDownAndSpinInButton().whileHeld(new SpinIntakeInDriverCommand());
	}
}
