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
		addTemp();
	}

	private void addTemp() {
		test.addObject("Right Side Switch", new SideSwitchAutonomous(false));
		test.addObject("Left Side Switch", new SideSwitchAutonomous(true));
		test.addObject("Right Side Scale", new ScaleAutonomous(false));
		test.addObject("Left Side Scale", new ScaleAutonomous(true));
		test.addObject("Right Side Opposite Scale", new OtherSideScaleAutonomous(false));
		test.addObject("Left Side Opposite Scale", new OtherSideScaleAutonomous(true));
		test.addObject("Right Side Two Cube Scale", new TwoCubeScaleAutonomous(false));
		test.addObject("Left Side Two Cube Scale", new TwoCubeScaleAutonomous(true));
		test.addObject("Right Side One Cube Scale One Cube Switch", new TwoCubeScaleAndSwitchAutonomous(false));
		test.addObject("Left Side One Cube Scale One Cube Switch", new TwoCubeScaleAndSwitchAutonomous(true));
		test.addObject("Center Two Cube Right", new TwoCubeCenterSwitch(false));
		test.addObject("Center Two Cube Left", new TwoCubeCenterSwitch(true));
		SmartDashboard.putData("Test", test);
	}

	private void addSwitchAndScale() {
		switchAndScale.addDefault("Do Nothing", new DoNothingCommandGroup());
		switchAndScale.addObject("Cross Baseline", new CrossBaselineTimeBasedAutonomous());

		switchAndScale.addObject("SideSwitch", new LambdaConditionalCommand(() -> side.getSelected() == Side.Left,
			new SideSwitchAutonomous(true), new SideSwitchAutonomous(false)));

		switchAndScale.addObject("Two Cube Scale", new LambdaConditionalCommand(() -> side.getSelected() == Side.Left,
			new TwoCubeScaleAutonomous(true), new TwoCubeScaleAutonomous(false)));

		switchAndScale.addObject("Scale Then Switch",
			new LambdaConditionalCommand(() -> side.getSelected() == Side.Left,
				new TwoCubeScaleAndSwitchAutonomous(true), new TwoCubeScaleAndSwitchAutonomous(false)));

		switchAndScale.addObject("One Cube Scale", new LambdaConditionalCommand(() -> side.getSelected() == Side.Left,
			new ScaleAutonomous(true), new ScaleAutonomous(false)));

		SmartDashboard.putData("SwitchAndScale", switchAndScale);
	}

	private void addJustSwitch() {
		justSwitch.addDefault("Do Nothing", new DoNothingCommandGroup());
		justSwitch.addObject("Cross Baseline", new CrossBaselineTimeBasedAutonomous());

		justSwitch.addObject("SideSwitch", new LambdaConditionalCommand(() -> side.getSelected() == Side.Left,
			new SideSwitchAutonomous(true), new SideSwitchAutonomous(false)));

		justSwitch.addObject("Other Side Scale", new LambdaConditionalCommand(() -> side.getSelected() == Side.Left,
			new OtherSideScaleAutonomous(true), new OtherSideScaleAutonomous(false)));

		SmartDashboard.putData("JustSwitch", justSwitch);

	}

	private void addJustScale() {
		justScale.addDefault("Do Nothing", new DoNothingCommandGroup());
		justScale.addObject("Cross Baseline", new CrossBaselineTimeBasedAutonomous());

		justScale.addObject("Two Cube Scale", new LambdaConditionalCommand(() -> side.getSelected() == Side.Left,
			new TwoCubeScaleAutonomous(true), new TwoCubeScaleAutonomous(false)));

		justScale.addObject("One Cube Scale", new LambdaConditionalCommand(() -> side.getSelected() == Side.Left,
			new ScaleAutonomous(true), new ScaleAutonomous(false)));

		SmartDashboard.putData("JustScale", justScale);
	}

	private void addNeither() {
		neither.addDefault("Do Nothing", new DoNothingCommandGroup());
		neither.addObject("Cross Baseline", new CrossBaselineTimeBasedAutonomous());

		Command botLeftScaleRight = new OtherSideScaleAutonomous(true);
		Command botRightScaleLeft = new OtherSideScaleAutonomous(false);
		neither.addObject("OtherSideScale",
			new LambdaConditionalCommand(() -> side.getSelected() == Side.Left, botLeftScaleRight, botRightScaleLeft));

		SmartDashboard.putData("Neither", neither);
	}

	private void addCenter() {
		center.addDefault("Do Nothing", new DoNothingCommandGroup());

		Command switchLeft = new TwoCubeCenterSwitch(true);
		Command switchRight = new TwoCubeCenterSwitch(false);
		center.addObject("Two Cube Switch",
			new LambdaConditionalCommand(() -> driverStation.isSwitchLeft(), switchLeft, switchRight));
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
		// In the event that Side == Left or Right, selectedCommand will be determined
		// by
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
