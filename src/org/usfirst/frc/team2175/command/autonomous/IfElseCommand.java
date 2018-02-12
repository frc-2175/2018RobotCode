package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.autonomous.AutoLambdas.LambdaCommandGroup;

import edu.wpi.first.wpilibj.command.Command;

public class IfElseCommand extends Command {

	AutoLambdas.CommandCondition condition;

	Command ifTrue;
	Command ifFalse;

	Command chosenCommand;

	public IfElseCommand(AutoLambdas.CommandCondition condition, Command ifTrue) {
		this(condition, ifTrue, null);
	}

	public IfElseCommand(AutoLambdas.CommandCondition condition, Command ifTrue, Command ifFalse) {
		this.condition = condition;
		this.ifTrue = ifTrue;
		this.ifFalse = ifFalse;
	}

	public IfElseCommand(AutoLambdas.CommandCondition condition, AutoLambdas.CommandAdder ifTrue) {
		this(condition, new LambdaCommandGroup(ifTrue));
	}

	public IfElseCommand(AutoLambdas.CommandCondition condition, AutoLambdas.CommandAdder ifTrue,
		AutoLambdas.CommandAdder ifFalse) {
		this(condition, new LambdaCommandGroup(ifTrue), new LambdaCommandGroup(ifFalse));
	}

	@Override
	protected void initialize() {
		if (condition.check()) {
			chosenCommand = ifTrue;
		} else {
			chosenCommand = ifFalse;
		}

		if (chosenCommand != null) {
			chosenCommand.start();
		}
	}

	@Override
	protected boolean isFinished() {
		return chosenCommand.isCompleted();
	}

}
