package org.usfirst.frc.team2175.command.autonomous;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.command.LambdaConditionalCommand;
import org.usfirst.frc.team2175.control.DryverStation;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonSelector {
	private DryverStation driverStation;

	private SendableChooser<Side> side = new SendableChooser<>();
	private SendableChooser<Command> switchAndScale = new SendableChooser<>();
	private SendableChooser<Command> justSwitch = new SendableChooser<>();
	private SendableChooser<Command> justScale = new SendableChooser<>();
	private SendableChooser<Command> neither = new SendableChooser<>();
	private SendableChooser<Command> test = new SendableChooser<>();
	private SendableChooser<Command> center = new SendableChooser<>();

	public AutonSelector() {
		driverStation = ServiceLocator.get(DryverStation.class);
		addSides();
		addSwitchAndScale();
		addJustSwitch();
		addJustScale();
		addNeither();
		addCenter();
		// addTemp();
	}

	// Commented out for comp
	// private void addTemp() {
	// test.addObject("LeftUltrasonicStraightFiveFeet", new
	// TestUltrasonicDriveStraightAutonomous(true));
	//
	// SmartDashboard.putData("Test", test);
	// }

	private void addSwitchAndScale() {
		switchAndScale.addDefault("Do Nothing", new DoNothingCommandGroup());
		switchAndScale.addObject("Cross Baseline", new CrossBaselineTimeBasedAutonomous());

		// switchAndScale.addObject("SwitchScale", new LambdaConditionalCommand(() ->
		// side.getSelected() == Side.Left,
		// new UntestedFullyScaleSwitchAutonomous(true), new
		// UntestedFullyScaleSwitchAutonomous(false)));

		switchAndScale.addObject("SideSwitch", new LambdaConditionalCommand(() -> side.getSelected() == Side.Left,
			new SideSwitchAutonomous(true), new SideSwitchAutonomous(false)));

		switchAndScale.addObject("Scale", new LambdaConditionalCommand(() -> side.getSelected() == Side.Left,
			new ScaleAutonomous(true), new ScaleAutonomous(false)));

		SmartDashboard.putData("SwitchAndScale", switchAndScale);
	}

	private void addJustSwitch() {
		justSwitch.addDefault("Do Nothing", new DoNothingCommandGroup());
		justSwitch.addObject("Cross Baseline", new CrossBaselineTimeBasedAutonomous());

		justSwitch.addObject("SideSwitch", new LambdaConditionalCommand(() -> side.getSelected() == Side.Left,
			new SideSwitchAutonomous(true), new SideSwitchAutonomous(false)));

		justSwitch.addObject("Other Side Scale", new LambdaConditionalCommand(() -> side.getSelected() == Side.Left,
			new ExperimentalOtherScaleAutonomous(true), new ExperimentalOtherScaleAutonomous(false)));

		SmartDashboard.putData("JustSwitch", justSwitch);

	}

	private void addJustScale() {
		justScale.addDefault("Do Nothing", new DoNothingCommandGroup());
		justScale.addObject("Cross Baseline", new CrossBaselineTimeBasedAutonomous());

		justScale.addObject("Scale", new LambdaConditionalCommand(() -> side.getSelected() == Side.Left,
			new ScaleAutonomous(true), new ScaleAutonomous(false)));

		SmartDashboard.putData("JustScale", justScale);
	}

	private void addNeither() {
		neither.addDefault("Do Nothing", new DoNothingCommandGroup());
		neither.addObject("Cross Baseline", new CrossBaselineTimeBasedAutonomous());

		Command botLeftScaleRight = new ExperimentalOtherScaleAutonomous(true);
		Command botRightScaleLeft = new ExperimentalOtherScaleAutonomous(false);
		neither.addObject("OtherSideScale",
			new LambdaConditionalCommand(() -> side.getSelected() == Side.Left, botLeftScaleRight, botRightScaleLeft));

		SmartDashboard.putData("Neither", neither);
	}

	private void addCenter() {
		center.addDefault("Do Nothing", new DoNothingCommandGroup());

		Command switchLeft = new CenterSwitchAutonomous(true);
		Command switchRight = new CenterSwitchAutonomous(false);
		center.addObject("Switch-GameData",
			new LambdaConditionalCommand(() -> driverStation.isSwitchLeft(), switchLeft, switchRight));
		center.addObject("Switch-Left", switchLeft);
		center.addObject("Switch-Left", switchRight);

		SmartDashboard.putData("Center", center);
	}

	private void addSides() {
		for (Side s : Side.values()) {
			side.addObject(s.toString(), s);
		}
		SmartDashboard.putData("SideChoice", side);
	}

	public Command getCommand() {
		Command selectedCommand;
		switch (side.getSelected()) {
		// In the event that Side == Left or Test, selectedCommand will be determined by
		// Scale
		// and Switch GameData
		case Left:
			selectedCommand = new LambdaConditionalCommand(() -> driverStation.isSwitchLeft(),
				new LambdaConditionalCommand(() -> driverStation.isScaleLeft(), switchAndScale.getSelected(),
					justSwitch.getSelected()),
				new LambdaConditionalCommand(() -> driverStation.isScaleLeft(), justScale.getSelected(),
					neither.getSelected()));
			break;
		case Right:
			selectedCommand = new LambdaConditionalCommand(() -> !driverStation.isSwitchLeft(),
				new LambdaConditionalCommand(() -> !driverStation.isScaleLeft(), switchAndScale.getSelected(),
					justSwitch.getSelected()),
				new LambdaConditionalCommand(() -> !driverStation.isScaleLeft(), justScale.getSelected(),
					neither.getSelected()));
			break;
		// In the event that Side == Center or Test, selectedCommand will equal
		// getSelected from respective SendableChooser
		case Center:
			selectedCommand = center.getSelected();
			break;
		case Test:
			selectedCommand = test.getSelected();
			break;
		default:
			selectedCommand = new DoNothingCommandGroup();
			break;
		}
		return selectedCommand;
	}

	enum Side {
		Left, Right, Center, Test;
	}

	public Side getChosenSide() {
		return side.getSelected();
	}
}
