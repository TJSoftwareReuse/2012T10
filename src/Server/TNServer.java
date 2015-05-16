package Server;

import java.util.Scanner;

import org.apache.log4j.Logger;

import tj.reuse.ConfigComponent;
import License.license;
import PM.PerformanceManager;
import edu.tongji.FaultManagement;

public class TNServer {
	public static final Logger logger = Logger.getLogger("stdout");
	private String configFilePath, fmFilePath, pmFilePath;
	private ConfigComponent cc;
	private FaultManagement fm;
	private PerformanceManager pm;
	private license lcs;
	private static TNServer myTnServer;
	private Boolean server_started;
	private String url = TNServer.class.getClassLoader().getResource("")
			.getPath();

	public static TNServer getinstance(String _configFilePath) {
		if (myTnServer != null) {
			return myTnServer;
		} else {
			myTnServer = new TNServer(_configFilePath);
			return myTnServer;
		}
	}

	public boolean server_status() {
		return server_started;
	}

	private void init() {
		try {
			lcs = new license(Integer.parseInt(cc.readValue(configFilePath,
					"License")));
			fmFilePath = cc.readValue(configFilePath, "FM");
			logger.info("read fmFilePath successfully");
			pmFilePath = new String(cc.readValue(configFilePath, "PM"));
			logger.info("read pmFilePath successfully");
			server_started = false;
			pm = new PerformanceManager(pmFilePath);
		} catch (Exception e) {
			logger.error("init the server wrong:config file not supported or not exists");
		}
	}

	private TNServer(String _configFilePath) {
		configFilePath = _configFilePath;
		System.setProperty("LOG_DIR", url);
		cc = new ConfigComponent();
		fm = FaultManagement.getInstance();
	}

	public void Start_Server() {
		System.out.println("Doing initialization....");
		this.init();
		System.out.println("Complete the initialization...");

		System.out.println("Welcome to the Server for Student name search");
		System.out
				.println("Input the student name in console followed by enter button,you will get the team number back");
		System.out
				.println("If you want to exit,just input exit in the console instead of the student name");

		String StudentName;

		fm.generateWarningMessage("start the server", fmFilePath);
		logger.info("server starting");

		server_started = true;

		while (server_status()) {
			System.out.println("Enter Command:");
			StudentName = new Scanner(System.in).nextLine().toString();
			if (StudentName.equals("exit")) {
				server_started = false;
				logger.info("server stoped");
				fm.generateWarningMessage("server stoped", fmFilePath);
				System.out.println("Server stoped...");
				break;
			}
						
			if (lcs.add_service()) {
				
				pm.AddData("receive request", 1);
				fm.generateWarningMessage("handle request", fmFilePath);
				pm.AddData("handle request", 1);
				String teamNum = cc.readValue(configFilePath, StudentName);
				pm.AddData("finished requested", 1);
				logger.info("finished request");
				if(teamNum!=null)
				{
					System.out.println("student " + StudentName + " is in team "
							+ teamNum);	
					
				}
				else {
					System.out.println("no name existed");
				}
				
			} else {
				fm.generateWarningMessage(
						"You have reached to the maximum license number",
						fmFilePath);
				pm.AddData("server rejected request", 1);
				logger.warn("You have reached to the maximum license number");
				System.out.println("No License available");
			}
		}
	}

}
