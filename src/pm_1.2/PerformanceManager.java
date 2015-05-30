package edu.tongji;

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

import javax.xml.stream.events.StartDocument;

public class PerformanceManager {

	private String storePath;
	private boolean status;
	private long interval = 60000;
	OutputDataToFile outputData = null;
	private HashMap<String, Vector<Integer>> performanceData = new HashMap<String, Vector<Integer>>();

	public PerformanceManager(String key, Integer value, String path) {
		this.setStatus(true);
		storePath = new String(path);
		AddData(key, value);
		outputData = new OutputDataToFile();
		outputData.start();
	}

	public PerformanceManager(String path) {
		this.setStatus(true);
		storePath = new String(path);
		outputData = new OutputDataToFile();
		outputData.start();
	}
	
	public long getInterval() {
		return interval;
	}
	
	public void setInterval(long interval) {
		this.interval = interval;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void stop_PM() {
		this.setStatus(false);
	}

	public void AddData(String key, Integer value) {
		if (!performanceData.containsKey(key)) {
			performanceData.put(key, new Vector<Integer>());
		}
		performanceData.get(key).add(value);
	}

	public static void isExist(String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		}
	}

	public void write(String startTime) throws IOException {
		Calendar calEnd = Calendar.getInstance();
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
		try {
			PerformanceManager.isExist(storePath);
			String realpath = storePath + startTime + "To" +sdf.format(calEnd.getTime()) + ".txt";
			System.out.println(realpath);
			PrintWriter writer = new PrintWriter(realpath);

			Iterator iter = performanceData.entrySet().iterator();

			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = (String) entry.getKey();
				Vector<Integer> val = (Vector<Integer>) entry.getValue();
				int sum_value = 0;
				for (int i = 0; i < val.size(); i++) {
					sum_value += val.get(i);
				}
				String value_tempString = key + ":" + sum_value;
				writer.println(value_tempString);
			}

			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	class OutputDataToFile extends Thread {
		public void run() {
			while (isStatus()) {
				Calendar calStart = Calendar.getInstance();
				TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
				try {
					sleep(interval);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				try {
					write(sdf.format(calStart));
					performanceData.clear();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		}
	}
}