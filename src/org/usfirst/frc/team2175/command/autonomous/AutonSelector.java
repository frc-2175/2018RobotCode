package org.usfirst.frc.team2175.command.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonSelector {
	private SendableChooser<Command> m_chooser = new SendableChooser<>();

	public AutonSelector() {
		m_chooser.addObject("kurveRight", new KurveDriveRightSideOfSwitch());
		m_chooser.addObject("DriveCurve", new DriveCurveAutonomous());
		m_chooser.addObject("Dumb Drive", new CrossBaselineTimeBasedAutonomous());
		m_chooser.addDefault("Do Nothing", new DoNothingCommandGroup());
		SmartDashboard.putData("Auto choices", m_chooser);
	}

	public Command getCommand() {
		return m_chooser.getSelected();
	}
}
