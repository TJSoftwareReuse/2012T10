import junit.framework.*;



public class TestLicense extends TestCase{
	public void testNewLicense(){
		License l1 = new License(1);
		if(!l1.add_service()){
			System.out.println("ʣ�༤������Ѳ��㣡");
		}
		if(!l1.add_service()){
			System.out.println("ʣ�༤������Ѳ��㣡");
		}
	}
}

