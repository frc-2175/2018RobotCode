package team2175;

import mockit.Mock;
import mockit.MockUp;
import edu.wpi.first.wpilibj.hal.JNIWrapper;

public class MockJNIWrapper extends MockUp<JNIWrapper> {
    @Mock
    public void $clinit() {
        System.out.println("Mock $clinit for JNIWrapper called");
    }
}