package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.autonomous.AutoLambdas.LambdaCommandGroup;

import edu.wpi.first.wpilibj.command.Command;

public class WhileCommand extends Command {

	AutoLambdas.CommandCondition condition;
	Command cmd;

	boolean active;

	public WhileCommand(AutoLambdas.CommandCondition condition, Command command) {
		this.condition = condition;
		this.cmd = command;
		this.active = true;
	}

	public WhileCommand(AutoLambdas.CommandCondition condition, AutoLambdas.CommandAdder adder) {
		this(condition, new LambdaCommandGroup(adder));
	}

	@Override
	protected void execute() {
		if (active && !cmd.isRunning()) {
			if (condition.check()) {
				cmd.start();
			} else {
				active = false;
			}
		}
	}

	@Override
	protected boolean isFinished() {
		return !active;
	}

}
