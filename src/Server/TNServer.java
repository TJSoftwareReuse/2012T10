package Server;


import tj.reuse.ConfigComponent;
import License.license;
import PM.PerformanceManager;
import edu.tongji.FaultManagement;

public class TNServer {

	private String configFilePath,fmFilePath,pmFilePath;
	private ConfigComponent cc;
	private FaultManagement fm;
	private PerformanceManager pm;
	private license lcs;
	//private int i;
	private TNServer myTnServer;
	private Boolean server_started;

	public TNServer getinstance(String _configFilePath)
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
	public void start_server()
	{
		pm = new PerformanceManager(pmFilePath);
		server_started=true;
		System.out.println("Server started...");
	}
	public void stop_server()
	{
		server_started=false;
		pm=null;
		System.out.println("Server stoped..");
	}
	
	private TNServer(String _configFilePath){
		configFilePath = _configFilePath;
		cc = new ConfigComponent();
		fm = FaultManagement.getInstance();
		lcs = new license(Integer.parseInt(cc.readValue(configFilePath, "License容量")));
		fmFilePath = new String(cc.readValue(configFilePath, "FM文件路径"));
		pmFilePath = new String(cc.readValue(configFilePath, "PM文件路径"));
		server_started=false;
	}

	
	public String GetTeamNum(String name){
		if(!server_started)
			return "server is stoped";
		pm.AddData("收到消息", 1);
		if(lcs.isProvide_service()){
			fm.generateWarningMessage("提供服务", fmFilePath);
			pm.AddData("提供服务", 1);
			String teamNum = cc.readValue(configFilePath, "name");
			pm.AddData("返回消息", 1);
			return teamNum;
		}
		else {
			fm.generateWarningMessage("拒绝服务", fmFilePath);
			pm.AddData("拒绝服务", 1);
			pm.AddData("返回消息", 1);
			return new String("拒绝服务");
		}
	}
	
}
