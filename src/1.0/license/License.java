
public class License{
	
	private boolean provide_service;
	private int max_license;
	private int num_license;
//	private static license single_licenseLicense;
	
	public boolean getProvide_service() {
		return provide_service;
	}

	public int getMax_license() {
		return max_license;
	}

	public License(int _max_license)
	{
		if( _max_license > 0 ){
		max_license=_max_license;
		num_license=0;
		provide_service =true;
		}
		else{
			System.out.println("Please insert right number!");
			provide_service = false;
		}
	}
	
//	public license getinstance(int _max_license)
//	{
//		if(single_licenseLicense==null )
//		{
//		single_licenseLicense=new license(_max_license ); 
//		}
//		return single_licenseLicense;
//	}
	public boolean add_service()
	{
		num_license++;
		if(num_license<max_license)
		{			
			provide_service =true;			
			return true;
		}if(num_license==max_license)
		{
			provide_service=false;
			return true;			
		}
		else
		{
			provide_service=false;
			return false;
		}
	}
	
	
	
}
