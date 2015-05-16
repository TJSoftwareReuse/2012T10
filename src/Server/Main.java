package Server;

import org.apache.log4j.Logger;

public class Main {
	//public static final Logger logger=Logger.getLogger("stdout");
	
	public  static void main(String args[])
	{
	
	TNServer myServer=TNServer.getinstance("C:/Users/dell/Desktop/config.property");
	
	myServer.Start_Server();
	
	}
		
}
