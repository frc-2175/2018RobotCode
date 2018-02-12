package org.usfirst.frc.team2175.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLambdas {

	public static interface CommandCondition {
		public boolean check();
	}

	public static interface CommandAdder {
		public void addCommands(CommandGroup cg);
	}

	public static class LambdaCommandGroup extends CommandGroup {
		public LambdaCommandGroup(CommandAdder adder) {
			adder.addCommands(this);
		}
	}

}
