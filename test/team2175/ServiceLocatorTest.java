package team2175;

import static org.junit.Assert.assertSame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc.team2175.ServiceLocator;

public class ServiceLocatorTest extends BaseTest {
	private final Foo foo = new Foo();

	@Before
	@After
	public void clearServiceLocator() {
		ServiceLocator.clear();
	}

	@Test
	public void testGet_CorrectInstance() {
		ServiceLocator.register(foo);
		final Foo actual = ServiceLocator.get(Foo.class);

		assertSame("Same object not retrieved from ServiceLocator", foo, actual);
	}

	@Test(expected = IllegalStateException.class)
	public void testGet_ExceptionOnMiss() {
		ServiceLocator.get(Foo.class);
	}

	private static class Foo {
	}

	@Test
	public void testRegister_FirstTime_Success() throws Exception {
		ServiceLocator.register(foo);
	}

	@Test(expected = IllegalStateException.class)
	public void testRegister_SecondTime_Fail() throws Exception {
		ServiceLocator.register(foo);
		ServiceLocator.register(foo);
	}
}
