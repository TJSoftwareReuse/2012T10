package edu.tongji;
import junit.framework.*;
import junit.textui.*;

public class TestAll extends TestSuite{
	public static Test suite(){
		TestSuite suite = new TestSuite("TestSuite Test");
		suite.addTestSuite(TestPM1.class);
		suite.addTestSuite(TestPM2.class);
		return suite;
	}
	public static void main(String args[]){
		TestRunner.run(suite());
	}
}
