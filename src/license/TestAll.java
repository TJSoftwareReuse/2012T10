import junit.framework.*;
import junit.textui.*;

public class TestAll extends TestSuite{
	public static Test suite(){
		TestSuite suite = new TestSuite("TestSuite Test");
		suite.addTestSuite(TestLicense.class);
		suite.addTestSuite(TestLicense2.class);
		return suite;
	}
	public static void main(String args[]){
		TestRunner.run(suite());
	}
}
