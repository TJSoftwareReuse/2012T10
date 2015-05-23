package Server;

import java.awt.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

import org.apache.log4j.Logger;

import tj.reuse.ConfigComponent;
import License.license;
import PM.PerformanceManager;
import edu.tongji.FaultManagement;

public class Server3_0 {
	public static final Logger logger = Logger.getLogger("stdout");
	private String configFilePath, fmFilePath, pmFilePath;
	private ConfigComponent cc;
	private FaultManagement fm;
	private PerformanceManager pm;
	private license lcs;
	private static Server3_0 myTnServer;
	private Boolean server_started;
	private String url = Server3_0.class.getClassLoader().getResource("")
			.getPath();

	public static Server3_0 getinstance(String _configFilePath) {
		if (myTnServer != null) {
			return myTnServer;
		} else {
			myTnServer = new Server3_0(_configFilePath);
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

	private Server3_0(String _configFilePath) {
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
				.println("Input the team num in console followed by enter button,you will get the student’s name back");
		System.out
				.println("If you want to exit,just input exit in the console instead of the team num");

		String Teamnum;

		fm.generateWarningMessage("start the server", fmFilePath);
		logger.info("server starting");

		server_started = true;

		while (server_status()) {
			System.out.println("Enter Command:");
			Teamnum = new Scanner(System.in).nextLine().toString();
			if (Teamnum.equals("exit")) {
				server_started = false;
				logger.info("server stoped");
				fm.generateWarningMessage("server stoped", fmFilePath);
				pm.stop_PM();
				System.out.println("Server stoped...");
				break;
			}

			if (lcs.add_service()) {
				Vector<String> StudentNames = new Vector<String>();
				pm.AddData("receive request", 1);
				fm.generateWarningMessage("handle request", fmFilePath);
				pm.AddData("handle request", 1);

				Properties properties = cc.readProperties(configFilePath);

				Set<Object> studentSet = properties.keySet();

				for (Object str : studentSet) {
					String tempname = (String) str;
					String tempnum = properties.getProperty(tempname);
					if (tempnum.equals(Teamnum)) {
						if (!tempname.equals("License")) {
							StudentNames.add(tempname);
						}
					}
				}

				pm.AddData("finished requested", 1);
				logger.info("finished request");
				System.out.println("We have " + StudentNames.size()
						+ " student in group " + Teamnum);
				for (int i = 0; i < StudentNames.size(); i++) {
					System.out.println(StudentNames.get(i));
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
