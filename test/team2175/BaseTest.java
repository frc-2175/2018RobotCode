package team2175;

import org.junit.Before;

public class BaseTest {
	@Before
	public void beforeTest() {
		new MockJNIWrapper();
		new MockNetworkTablesJNI();
		new MockSmartDashboard();
	}
}
