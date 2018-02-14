package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.command.single.ArcadeDriveForAutonCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestConditionalAuton extends CommandGroup {

	public static final boolean FORWARDS = true;

	// public TestConditionalAuton() {
	// addSequential(new IfElseCommand(() -> {
	// return FORWARDS;
	// }, (cg) -> {
	// cg.addSequential(new ArcadeDriveForAutonCommand(0.5), 1);
	// }, (cg) -> {
	// cg.addSequential(new ArcadeDriveForAutonCommand(-0.5), 1);
	// }));
	// }

	public TestConditionalAuton() {
		addSequential(new LambdaConditionalCommand(() -> {
			return FORWARDS;
		}, new ArcadeDriveForAutonCommand(0.5), new ArcadeDriveForAutonCommand(-0.5)));
	}

}
