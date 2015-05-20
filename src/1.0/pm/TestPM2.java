package pm;
import junit.framework.TestCase;

public class TestPM2 extends TestCase{
	
	
	
	
	public void testPm1(){
	PerformanceManager p1=new PerformanceManager("C:\\Users\\sse430-ssed\\Desktop\\");

	if(!p1.AddData("program2",4))
	{
		System.out.println("未能成功输入program2的数据");
	}
	if(!p1.AddData("program2",5))
	{
		System.out.println("未能成功输入program2的数据");
	}
	}
}