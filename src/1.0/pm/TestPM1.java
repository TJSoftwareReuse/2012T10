package pm;
import junit.framework.TestCase;

public class TestPM1 extends TestCase{
	
	
	
	
	public void testPm1(){
	PerformanceManager p1=new PerformanceManager("C:\\Users\\sse430-ssed\\Desktop\\");

	if(!p1.AddData("program1",2))
	{
		System.out.println("未能成功输入program1的数据");
	}
	if(!p1.AddData("program2",3))
	{
		System.out.println("未能成功输入program1的数据");
	}
	}
}