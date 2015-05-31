package Server;

import java.awt.List;
import java.awt.color.CMMException;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

import org.apache.log4j.Logger;

import tj.reuse.ConfigComponent;
import License.license;
import PM.PerformanceManager;
import edu.tongji.FaultManagement;

public class Server3_5 {
	public static final Logger logger = Logger.getLogger("stdout");
	private String configFilePath, fmFilePath, pmFilePath;
	private ConfigComponent cc;
	private FaultManagement fm;
	private PerformanceManager pm;
	private license lcs;
	private String fmMessage;
	private static Server3_5 myTnServer;
	private Boolean server_started;
	private String url = Server3_5.class.getClassLoader().getResource("")
			.getPath();

	// get the instance of server
	public static Server3_5 getinstance(String _configFilePath) {
		if (myTnServer != null) {
			return myTnServer;
		} else {
			myTnServer = new Server3_5(_configFilePath);
			return myTnServer;
		}
	}

	// get the status of server
	public boolean server_status() {
		return server_started;
	}

	// initialize the server
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
			fmMessage = "";
		} catch (Exception e) {
			logger.error("init the server wrong:config file not supported or not exists");
		}
	}

	// construct function
	private Server3_5(String _configFilePath) {
		configFilePath = _configFilePath;
		System.setProperty("LOG_DIR", url);
		cc = new ConfigComponent();
		fm = FaultManagement.getInstance();
	}

	// start the server
	@SuppressWarnings("deprecation")
	public void Start_Server() {
		System.out.println("Doing initialization....");
		this.init();
		System.out.println("Complete the initialization...");

		System.out.println("Welcome to the Server3_5");
		// System.out.println("Input the team num in console followed by enter button,you will get the student name back");
		// System.out.println("If you want to exit,just input exit in the console instead of the team num");

		String command;
		if (!fmMessage.equals("start the server")) {
			fm.generateWarningMessage("start the server", fmFilePath);
			fmMessage = "start the server";
		}
		logger.info("server starting");

		server_started = true;

		// infinite while loop,break only when the user input stop
		while (server_status()) {
			System.out
					.println("Enter 'configure' to modify the configuration.");
			System.out
					.println("Enter 'query' to query team number or team members.");
			System.out.println("Enter 'exit' to exit.");
			System.out.println("Enter Command:");
			command = new Scanner(System.in).nextLine().toString();
			if (command.equals("exit")) {
				server_started = false;
				logger.info("server stoped");
				System.out.println("Server stoped...");
				pm.stop_PM();
				break;
			}
			if (command.equals("configure")) {
				System.out
						.println("Enter 'fm' to modify the path of the output file.");
				System.out
						.println("Enter 'pm' to modify the interval of outputing performance information.");
				System.out
						.println("Enter 'lcs' to modify the number of licenses.");
				System.out.println("Enter 'exit' to exit.");
				System.out.println("Enter Command:");
				command = new Scanner(System.in).nextLine().toString();
				if (command.equals("fm")) {
					System.out.println("Enter the new path.");
					System.out.println("Enter Command:");
					command = new Scanner(System.in).nextLine().toString();
					cc.writeProperties(configFilePath, "FM", command);
					fmFilePath = command;
					System.out.println("Succeed.");

				}
				if (command.equals("pm")) {
					System.out.println("Enter the new interval.");
					System.out.println("Enter Command:");
					command = new Scanner(System.in).nextLine().toString();
					pm.setInterval(Integer.parseInt(command));
					System.out.println("Succeed.");

				}
				if (command.equals("lcs")) {
					System.out.println("Enter the new number.");
					System.out.println("Enter Command:");
					command = new Scanner(System.in).nextLine().toString();
					cc.writeProperties(configFilePath, "License", command);
					lcs = new license(Integer.parseInt(command));
					System.out.println("Succeed.");

				}
				if (command.equals("exit")) {
					server_started = false;
					logger.info("server stoped");
					pm.stop_PM();
					System.out.println("Server stoped...");
					break;
				}

			}
			if (command.equals("query")) {
				System.out.println("Enter 'tn' to query the team number.");
				System.out.println("Enter 'tm' to query all team members.");
				System.out.println("Enter 'exit' to exit.");
				System.out.println("Enter Command:");
				command = new Scanner(System.in).nextLine().toString();
				if (command.equals("tn")) {
					System.out.println("Enter the student name.");
					System.out.println("Enter Command:");
					command = new Scanner(System.in).nextLine().toString();
					pm.AddData("receive message", 1);
					// judge whether the service available
					if (lcs.add_service()) {
						if (!fmMessage.equals("offer service")) {
							fm.generateWarningMessage("offer service",
									fmFilePath);
							fmMessage = "offer service";
						}

						pm.AddData("offer service", 1);
						String teamNum = cc.readValue(configFilePath, command);
						pm.AddData("return message", 1);
						logger.info("finished service");
						if (teamNum != null) {
							if (command.equals("License")
									|| command.equals("PMFilePath")
									|| command.equals("FMFilePath")) {
								System.out.println("not a name");

							} else {
								System.out.println("student " + command
										+ " is in team " + teamNum);

							}
						} else {
							System.out.println("student not exist");

						}

					} else {
						if (!fmMessage.equals("denial of service")) {
							fm.generateWarningMessage("denial of service",
									fmFilePath);
							fmMessage = "denial of service";
						}
						pm.AddData("denial of service", 1);
						pm.AddData("return message", 1);
						logger.warn("no license available");
						System.out.println("no license available");

					}
				}
				if (command.equals("tm")) {
					System.out.println("Enter the team number.");
					System.out.println("Enter Command:");
					command = new Scanner(System.in).nextLine().toString();
					pm.AddData("receive message", 1);
					// judge whether the service available
					if (lcs.add_service()) {
						Vector<String> StudentNames = new Vector<String>();
						if (!fmMessage.equals("offer service")) {
							fm.generateWarningMessage("offer service",
									fmFilePath);
							fmMessage = "offer service";
						}
						pm.AddData("offer service", 1);

						Properties properties = cc
								.readProperties(configFilePath);

						// get the key set
						Set<Object> studentSet = properties.keySet();

						// find the available keys
						for (Object str : studentSet) {
							String tempname = (String) str;
							String tempnum = properties.getProperty(tempname);
							if (tempnum.equals(command)) {
								if (!tempname.equals("License")) {
									StudentNames.add(tempname);
								}
							}
						}
						pm.AddData("return message", 1);
						logger.info("finished request");
						System.out.println("We have " + StudentNames.size()
								+ " student in group " + command);
						for (int i = 0; i < StudentNames.size(); i++) {
							System.out.println(StudentNames.get(i));
						}

					} else {
						if (!fmMessage.equals("denial of service")) {
							fm.generateWarningMessage("denial of service",
									fmFilePath);
							fmMessage = "denial of service";
						}
						pm.AddData("denial of service", 1);
						pm.AddData("return message", 1);
						logger.warn("no license available");
						System.out.println("no license available");

					}
				}
				if (command.equals("exit")) {
					server_started = false;
					logger.info("server stoped");
					pm.stop_PM();
					System.out.println("Server stoped...");
					break;
				}

			}

		}
	}
}
