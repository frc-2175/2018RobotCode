package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.autonomous.AutoLambdas.CommandCondition;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class LambdaConditionalCommand extends ConditionalCommand {

	protected CommandCondition condition;

	public LambdaConditionalCommand(CommandCondition condition, Command onTrue) {
		this(condition, onTrue, null);
	}

	public LambdaConditionalCommand(CommandCondition condition, Command onTrue, Command onFalse) {
		super(onTrue, onFalse);
		this.condition = condition;
	}

	@Override
	protected boolean condition() {
		return condition.check();
	}

}
