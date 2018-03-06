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
	private SendableChooser<Command> temp = new SendableChooser<>();
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
		temp.addDefault("Do Nothing", new DoNothingCommandGroup());
		temp.addObject("CenterLeft", new CenterSwitchAutonomous(true));
		temp.addObject("CenterRight", new CenterSwitchAutonomous(false));
		temp.addObject("SwitchScaleLeft", new ScaleSwitchAutonomous(true));
		temp.addObject("SideSwitchLeft", new SideSwitchAutonomous(true));
		temp.addObject("SideSwitchRight", new SideSwitchAutonomous(false));
		temp.addObject("LeftUltrasonicStraightFiveFeet", new TestUltrasonicDriveStraightAutonomous(true));
		temp.addObject("LeftScale", new ScaleAutonomous(true));
		temp.addObject("RightScale", new ScaleAutonomous(false));
		temp.addObject("ExperimentalScale", new ExperimentalScaleAutonomous());
		temp.addObject("LeftSideOtherScale", new ExperimentalOtherScaleAutonomous());

		// TODO (kevin): Since the side choice is called "Test", maybe we should rename
		// this selector to Test as well.
		SmartDashboard.putData("Temporary", temp);
	}

	private void addSwitchAndScale() {
		switchAndScale.addDefault("Do Nothing", new DoNothingCommandGroup());
		switchAndScale.addObject("SwitchScaleLeft", new ScaleSwitchAutonomous(true));
		switchAndScale.addObject("SideSwitchLeft", new SideSwitchAutonomous(true));

		SmartDashboard.putData("SwitchAndScale", switchAndScale);
	}

	private void addJustSwitch() {
		justSwitch.addDefault("Do Nothing", new DoNothingCommandGroup());
		SmartDashboard.putData("JustSwitch", justSwitch);

	}

	private void addJustScale() {
		justScale.addDefault("Do Nothing", new DoNothingCommandGroup());
		SmartDashboard.putData("JustScale", justScale);
	}

	private void addNeither() {
		neither.addDefault("Do Nothing", new DoNothingCommandGroup());
		neither.addObject("Dumb Drive", new CrossBaselineTimeBasedAutonomous());
		SmartDashboard.putData("Neither", neither);
	}

	private void addCenter() {
		center.addDefault("Do Nothing", new DoNothingCommandGroup());
		Command switchLeft = new CenterSwitchAutonomous(true);
		Command switchRight = new CenterSwitchAutonomous(false);
		center.addObject("Switch-GameData",
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
		case Center:
			selectedCommand = center.getSelected();
			break;
		case Test:
			selectedCommand = temp.getSelected();
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
}
