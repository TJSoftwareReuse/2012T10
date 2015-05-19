package Server;

import junit.framework.TestCase;
import java.util.Scanner;

import org.apache.log4j.Logger;

import tj.reuse.ConfigComponent;
import License.license;
import PM.PerformanceManager;
import edu.tongji.FaultManagement;
public class TNServerTest extends TestCase {
	String wrongPathString = "I'm a wrong path";
	String rightPathString = "D:\\eclipse\\2012T10-master\\src\\Server\\Config_file_for_test\\config.properties";
	public void testGetinstance() {
		//测试为TNServer建立带有错误路径的单例
		TNServer testServer=TNServer.getinstance(wrongPathString);
		//测试在错误路径下是否能执行
		try{
			testServer.Start_Server();
		}catch (Exception e) {
			System.out.println("It's not running now!");
		}
		//测试是否能在单例中更改路径，如果结果为否则符合预期
		testServer.getinstance(rightPathString);
		try{
			testServer.Start_Server();
		}catch (Exception e) {
			System.out.println("It's not running now!");
		}
	}

	public void testServer_status() {
		TNServer testServer=TNServer.getinstance(rightPathString);
		
		//在执行Start_Server前，查看Server_status的返回值是否正确。
		System.out.println("Now I didn't start the program , what will it return?\n");
		System.out.println((testServer.server_status() ? "It has started":"It hasn't started"));
		
		
		
		testServer.Start_Server();
		//在执行Start_Server后，查看Server_status的返回值是否正确。因为会在后台不断执行， 在控制台输入exit后退出才会继续打印
		System.out.println("Now I have stoped the program , what will it return?\n");
		System.out.println((testServer.server_status() ? "It has started":"It hasn't started"));
	}

	public void testStart_Server() {
		//测试正常路径输入下Start_Server的各种功能，因为junit只能静态测试，所以测试者需要在此进行各种输入来进行测试。
		
		TNServer testServer=TNServer.getinstance(rightPathString);
		try{
			
			testServer.Start_Server();
			
		}catch (Exception e) {
			
			System.out.println("It's not running now!");
			
		}
	}

}
