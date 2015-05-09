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
		lcs = new license(Integer.parseInt(cc.readValue(configFilePath, "License����")));
		fmFilePath = new String(cc.readValue(configFilePath, "FM�ļ�·��"));
		pmFilePath = new String(cc.readValue(configFilePath, "PM�ļ�·��"));
		server_started=false;
	}

	
	public String GetTeamNum(String name){
		if(!server_started)
			return "server is stoped";
		pm.AddData("�յ���Ϣ", 1);
		if(lcs.isProvide_service()){
			fm.generateWarningMessage("�ṩ����", fmFilePath);
			pm.AddData("�ṩ����", 1);
			String teamNum = cc.readValue(configFilePath, "name");
			pm.AddData("������Ϣ", 1);
			return teamNum;
		}
		else {
			fm.generateWarningMessage("�ܾ�����", fmFilePath);
			pm.AddData("�ܾ�����", 1);
			pm.AddData("������Ϣ", 1);
			return new String("�ܾ�����");
		}
	}
	
}
