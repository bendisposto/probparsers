package de.be4.eventb.parser;

import java.util.Enumeration;

import junit.framework.TestSuite;
import junit.runner.ClassPathTestCollector;
import junit.runner.TestCollector;

/**
 * All tests on the classpath.
 */
public class AllTestsSuite extends TestSuite {

	/**
	 * Returns a TestSuite containing all tests on the classpath.
	 * 
	 * @return a TestSuite containing all tests on the classpath.
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static TestSuite suite() throws ClassNotFoundException {
		final TestSuite suite = new TestSuite();
		final Enumeration enumer = collectTestClasses();
		while (enumer.hasMoreElements()) {
			final Class klass = Class.forName((String) enumer.nextElement());
			if (klass != null) {
				suite.addTestSuite(klass);
			}
		}

		return suite;
	}

	/**
	 * Returns qualified class names of classes on the classpath, whos name ends
	 * with "Test".
	 * 
	 * @return qualified class names of classes on the classpath, whos name ends
	 *         with "Test".
	 */
	@SuppressWarnings("unchecked")
	private static Enumeration collectTestClasses() {
		final TestCollector collector = new ClassPathTestCollector() {
			@Override
			public boolean isTestClass(final String classFileName) {
				if (classFileName.endsWith("Test.class")) {
					return true;
				} else {
					return false;
				}
			}
		};

		return collector.collectTests();
	}
}
