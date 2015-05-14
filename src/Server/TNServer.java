package Server;


import org.apache.log4j.Logger;

import tj.reuse.ConfigComponent;
import License.license;
import PM.PerformanceManager;
import edu.tongji.FaultManagement;

public class TNServer {
	public static final Logger logger=Logger.getLogger("stdout");
	private String configFilePath,fmFilePath,pmFilePath;
	private ConfigComponent cc;
	private FaultManagement fm;
	private PerformanceManager pm;
	private license lcs;
	private static TNServer myTnServer;
	private Boolean server_started;
	private String url=TNServer.class.getClassLoader().getResource("").getPath();	
	
	
	public static TNServer getinstance(String _configFilePath)
	{
		if(myTnServer!=null)
		{
			return myTnServer;
		}
		else{
			myTnServer=new TNServer(_configFilePath);
			return myTnServer;
		}
	}
	public boolean server_status()
	{
		return server_started;
	}
	public boolean start_server()
	{
		
		pm = new PerformanceManager(pmFilePath);
		server_started=true;
		System.out.println("Server started...");
		return server_status();
	}
	public boolean stop_server()
	{
		server_started=false;
		System.out.println("Server stoped..");
		return server_status();
	}
	
	private TNServer(String _configFilePath){
		System.setProperty("LOG_DIR", url);
		configFilePath = _configFilePath;
		cc = new ConfigComponent();
		fm = FaultManagement.getInstance();	
		try{
		lcs = new license(Integer.parseInt(cc.readValue(configFilePath, "License")));
		fmFilePath = cc.readValue(configFilePath, "FM");
		logger.info("read fmFilePath successfully");
		pmFilePath = new String(cc.readValue(configFilePath, "PM"));
		logger.info("read pmFilePath successfully");
		server_started=false;
		}
		catch(Exception e)
		{
			logger.error("init the server wrong:config file not supported");
		}
	}

	
	public String GetTeamNum(String name){
		if(!server_started)
			return "server is stoped";
		pm.AddData("receive request", 1);
		if(lcs.add_service()){
			fm.generateWarningMessage("handle request", fmFilePath);
			pm.AddData("handle request", 1);
			String teamNum = cc.readValue(configFilePath, name);
			System.out.println(teamNum);
			pm.AddData("finished requested", 1);
			logger.info("finished request");
			return teamNum;
		}
		else {
			fm.generateWarningMessage("You have reached to the maximum license number", fmFilePath);
			pm.AddData("server rejected request",1);
			logger.warn("You have reached to the maximum license number");
			return new String("You have reached to the maximum license number");
		}
	}
	
}
