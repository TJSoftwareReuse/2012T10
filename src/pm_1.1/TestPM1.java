package PM;
import junit.framework.TestCase;

public class TestPM1 extends TestCase{
	
	
	
	
	public void testPm1(){
		PerformanceManager p1 = new PerformanceManager("C:\\Users\\sse430-ssed\\Desktop\\", 1 , "Program1");
		p1.outputData.run();
		//System.out.println("δ�ܳɹ�����program1������");
	
		PerformanceManager p2 = new PerformanceManager("C:\\Users\\sse430-ssed\\Desktop\\", 2 , "Program2");
		p2.outputData.run();
		//System.out.println("δ�ܳɹ�����program2������");
		
		PerformanceManager p3 = new PerformanceManager("C:\\Users\\sse430-ssed\\Desktop\\", 3 , "Program3");
		p3.outputData.run();
		//System.out.println("δ�ܳɹ�����program3������");
	}
}