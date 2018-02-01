package team2175.mock;

import edu.wpi.first.wpilibj.hal.HAL;
import mockit.Mock;
import mockit.MockUp;

public class MockHAL extends MockUp<HAL> {

	@Mock
	public static int report(int resource, int instanceNumber, int context, String feature) {
		return 0;
	}
	
}
