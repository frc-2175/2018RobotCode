package team2175.bamboozle;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Timer.Interface;

public class BamboozleStaticInterface implements Timer.StaticInterface {

	@Override
	public double getFPGATimestamp() {
		return 0;
	}

	@Override
	public double getMatchTime() {
		return 0;
	}

	@Override
	public void delay(double seconds) {}

	@Override
	public Interface newTimer() {
		// TODO Auto-generated method stub
		return null;
	}

}
