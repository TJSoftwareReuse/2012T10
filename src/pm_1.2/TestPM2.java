package edu.tongji;
import junit.framework.TestCase;

public class TestPM2 extends TestCase{
	
	
	
	
	public void testPm1(){
		PerformanceManager p4 = new PerformanceManager("C:\\Users\\sse430-ssed\\Desktop\\", 4 , "Program4");
		p4.outputData.run();
		//System.out.println("未能成功输入program4的数据");
	
		PerformanceManager p5 = new PerformanceManager("C:\\Users\\sse430-ssed\\Desktop\\", 5 , "Program5");
		p5.outputData.run();
		//System.out.println("未能成功输入program5的数据");
		
		PerformanceManager p6 = new PerformanceManager("C:\\Users\\sse430-ssed\\Desktop\\", 6 , "Program6");
		p6.outputData.run();
		//System.out.println("未能成功输入program6的数据");
	}
}