import junit.framework.*;



public class TestLicense extends TestCase{
	public void testNewLicense(){
		License l1 = new License(1);
		if(!l1.add_service()){
			System.out.println("剩余激活次数已不足！");
		}
		if(!l1.add_service()){
			System.out.println("剩余激活次数已不足！");
		}
	}
}

