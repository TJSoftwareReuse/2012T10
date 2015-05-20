import junit.framework.TestCase;

public class TestLicense2 extends TestCase{
	public void testNewLicense(){
		License l2 = new License(2);
		if(!l2.add_service()){
			System.out.println("剩余激活次数已不足！");
		}
	}
}