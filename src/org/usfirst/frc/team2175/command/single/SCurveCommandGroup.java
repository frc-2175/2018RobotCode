package org.usfirst.frc.team2175.command.single;

import org.usfirst.frc.team2175.command.BaseCommandGroup;

public class SCurveCommandGroup extends BaseCommandGroup {

	public SCurveCommandGroup(double dx, double dy, double r) {
		scurve(dx, dy, r, true, true);
	}

	public SCurveCommandGroup(double dx, double dy, double r, boolean doSecondStep) {
		scurve(dx, dy, r, doSecondStep, false);
	}

	public SCurveCommandGroup(double dx, double dy, double r, boolean doSecondStep, boolean doSecondCurve) {
		scurve(dx, dy, r, doSecondStep, doSecondCurve);
	}

	public void scurve(double dx, double dy, double r, boolean doSecondStep, boolean doSecondCurve) {
		double sign = Math.signum(dx);
		dx = Math.abs(dx);

		double h = Math.sqrt(dy * dy + (dx - 2 * r) * (dx - 2 * r)) / 2;

		double f = Math.sqrt(h * h - r * r);
		double d = 2 * f;

		double phi = Math.atan(dy / r);
		double mu = Math.atan(f / r);
		double theta = Math.PI - phi - mu;

		addSequential(new DriveCurve(sign * r, Math.toDegrees(theta), 1, true, false));
		if (doSecondStep) {
			addSequential(new DriveStraightCommand(.7, d, false, false));
		}
		if (doSecondCurve) {
			addSequential(new DriveCurve(-sign * r, Math.toDegrees(theta), 1, false, true));
		}
	}
}
