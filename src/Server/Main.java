package Server;

import org.apache.log4j.Logger;

public class Main {
	//public static final Logger logger=Logger.getLogger("stdout");
	
	public  static void main(String args[])
	{
	//System.out.println("aaaa");
	TNServer myServer=TNServer.getinstance("C:/Users/dell/Desktop/config.property");
	System.out.println("aaaaaa");
	myServer.start_server();
	for(int i=0;i<10;i++)
	{
	String num=myServer.GetTeamNum("½ªÄ¾»Û");
	System.out.println(num);
	System.out.println(i);
	}
	}
		
}
