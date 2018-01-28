package team2175;

import org.junit.Before;

import edu.wpi.first.wpilibj.Timer;
import team2175.bamboozle.BamboozleStaticInterface;
import team2175.mock.MockHAL;
import team2175.mock.MockJNIWrapper;
import team2175.mock.MockNetworkTablesJNI;
import team2175.mock.MockSmartDashboard;

public class BaseTest {
	@Before
	public void beforeTest() {
		new MockHAL();
		new MockJNIWrapper();
		new MockNetworkTablesJNI();
		new MockSmartDashboard();

		Timer.SetImplementation(new BamboozleStaticInterface());
	}
}
