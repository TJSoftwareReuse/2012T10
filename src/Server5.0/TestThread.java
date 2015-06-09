package Server;

import java.io.IOException;

public class TestThread extends Thread {

     
        public void run() {
            int i = 0;
            while(i<5){
            	//多线程调用5次
                System.out.println("已经循环了"+i+"次");
        		Server3_5 testServer=Server5_0.getinstance("D:\\eclipse\\2012T10-master\\src\\Server\\Config_file_for_test\\config.properties");		
        		try{
        			
        			testServer.Start_Server();
        			
        		}catch (Exception e) {
        			
        			System.out.println("It's not running now!");
        			
        		}
                
                i++;
            }
        }
    
}