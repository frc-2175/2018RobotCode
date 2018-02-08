package team2175.mock;

import edu.wpi.first.networktables.NetworkTablesJNI;
import mockit.Mock;
import mockit.MockUp;

public class MockNetworkTablesJNI extends MockUp<NetworkTablesJNI> {
	@Mock
	public void $clinit() {
		System.out.println("Mock $clinit for NetworkTablesJNI called");
	}

	@Mock
	public static int getDefaultInstance() {
		// dummy
		return 0;
	}

	@Mock
	public static int getEntry(int inst, String key) {
		// dummy
		return 0;
	}
}