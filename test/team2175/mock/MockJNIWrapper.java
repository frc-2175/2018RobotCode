package team2175.mock;

import edu.wpi.first.wpilibj.hal.JNIWrapper;
import mockit.Mock;
import mockit.MockUp;

public class MockJNIWrapper extends MockUp<JNIWrapper> {
	@Mock
	public void $clinit() {
		System.out.println("Mock $clinit for JNIWrapper called");
	}
}