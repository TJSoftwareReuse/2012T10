package pm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;
import java.util.Vector;


public class PerformanceManager {
	
	private String storePath;
	private HashMap<String, Vector<Integer>> performanceData=new HashMap<String,Vector<Integer>>();
	
	public PerformanceManager(String key,Integer value, String path)
	{
		storePath = new String(path);
		AddData(key,value);
		OutputDataToFile outputData = new OutputDataToFile();
		outputData.start();
	}
	public PerformanceManager(String path)
	{
		storePath = new String(path);
		OutputDataToFile outputData = new OutputDataToFile();
		outputData.start();
	}
	
	public boolean AddData(String key,Integer value)
	{
		if(!performanceData.containsKey(key))
		{
			performanceData.put(key,new Vector<Integer>());
		}
		if(performanceData.get(key).add(value)) return true;
		else return false;
	}
	
	
	public void write() throws IOException
	{
		Calendar cal1 = Calendar.getInstance();  
		//System.out.println(cal1);
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));       //非常关键的！！！ 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss"); 
        //System.out.println(sdf.format(cal1.getTime()));  
//		File file=new File(storePath+"/"+sdf.format(cal1.getTime()));
		try {
			//System.out.println(storePath+sdf.format(cal1.getTime()));
			String realpath=storePath+sdf.format(cal1.getTime())+".txt";
			System.out.println(realpath);
//			File file=new File(realpath);
//			file.createNewFile();
			PrintWriter writer = new PrintWriter(realpath);
			
			Iterator iter = performanceData.entrySet().iterator();
			
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = (String)entry.getKey();
				Vector<Integer> val = (Vector<Integer>)entry.getValue();
				int sum_value=0;
					for (int i=0;i<val.size();i++)
						{
							sum_value+=val.get(i);
						}
				String value_tempString=key+":"+sum_value;
				writer.println(value_tempString);
				}
			
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//File f=new File(path+"");
		
	}

	class OutputDataToFile extends Thread
	{
		public void run()
		{
			
			while(true)
			{
			try {		
				write();
				performanceData.clear();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try{
				sleep(60000);

			}catch(InterruptedException e){
			e.printStackTrace();	
			}
			
			}
		}
	}
}