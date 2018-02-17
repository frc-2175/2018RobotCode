package org.usfirst.frc.team2175.command;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class LambdaConditionalCommand extends ConditionalCommand {

	public static interface Condition {
		public boolean check();
	}

	protected Condition condition;

	public LambdaConditionalCommand(Condition condition, Command onTrue) {
		super(onTrue);
		this.condition = condition;
	}

	public LambdaConditionalCommand(Condition condition, Command onTrue, Command onFalse) {
		super(onTrue, onFalse);
		this.condition = condition;
	}

	@Override
	protected boolean condition() {
		return condition.check();
	}

}
