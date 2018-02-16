/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2175.robot;

import java.util.logging.Logger;

import org.usfirst.frc.team2175.ServiceLocator;
import org.usfirst.frc.team2175.command.DefaultCommandFactory;
import org.usfirst.frc.team2175.command.autonomous.DriveCurveAutonomous;
import org.usfirst.frc.team2175.command.autonomous.KurveDriveRightSideOfSwitch;
import org.usfirst.frc.team2175.control.DryverStation;
import org.usfirst.frc.team2175.control.JoystickEventMapper;
import org.usfirst.frc.team2175.info.InfoFactory;
import org.usfirst.frc.team2175.log.LogServer;
import org.usfirst.frc.team2175.log.LoggingConfig;
import org.usfirst.frc.team2175.log.RobotLogger;
import org.usfirst.frc.team2175.subsystem.DrivetrainSubsystem;
import org.usfirst.frc.team2175.subsystem.ElevatorSubsystem;
import org.usfirst.frc.team2175.subsystem.SubsystemsFactory;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	private final Logger log = RobotLogger.getLogger(this);
	private Command m_autoSelected;
	private SendableChooser<Command> m_chooser = new SendableChooser<>();
	private DrivetrainSubsystem drivetrainSubsystem;
	private ElevatorSubsystem elevatorSubsystem;
	private RobotLogger robotLogger;
	private LogServer logServer;

	@Override
	public void robotInit() {
		LoggingConfig.initialize();
		ServiceLocator.register(this);
		robotLogger = new RobotLogger();
		InfoFactory.makeAllInfos();
		new DryverStation();
		SubsystemsFactory.makeAllSubsystems();
		m_chooser.addObject("kurveRight", new KurveDriveRightSideOfSwitch());
		m_chooser.addDefault("DriveCurve", new DriveCurveAutonomous());
		SmartDashboard.putData("Auto choices", m_chooser);
		robotLogger.log();
		logServer = new LogServer();
		drivetrainSubsystem = ServiceLocator.get(DrivetrainSubsystem.class);
		elevatorSubsystem = ServiceLocator.get(ElevatorSubsystem.class);
		new JoystickEventMapper();
		new DefaultCommandFactory();
	}

	@Override
	public void robotPeriodic() {
		SmartDashboard.putNumber("AutoPopulate/PSI Value", drivetrainSubsystem.getPSIValue());
		SmartDashboard.putNumber("LeftEncoder", drivetrainSubsystem.getLeftEncoderValues());
		SmartDashboard.putNumber("RightEncoder", drivetrainSubsystem.getRightEncoderValues());
		SmartDashboard.putNumber("Elevator Encoder", elevatorSubsystem.getInchesTraveled());
		SmartDashboard.putNumber("gyro", drivetrainSubsystem.getGyroValue());
	}

	@Override
	public void disabledInit() {
		log.info("Robot program is disabled and ready.");
		robotLogger.moveLogFile();
		robotLogger.flush();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		drivetrainSubsystem.resetAllSensors();
		m_autoSelected = m_chooser.getSelected();
		m_autoSelected.start();
	}

	@Override
	public void autonomousPeriodic() {
		robotLogger.log();
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		drivetrainSubsystem.resetAllSensors();
		if (m_autoSelected != null && !m_autoSelected.isCompleted()) {
			m_autoSelected.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {
		robotLogger.log();
		Scheduler.getInstance().run();
	}

	@Override
	public void testInit() {
		robotLogger.moveLogFile();
		logServer.runServer();
	}

	@Override
	public void testPeriodic() {
	}
}
