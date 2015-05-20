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
		//����ΪTNServer�������д���·���ĵ���
		TNServer testServer=TNServer.getinstance(wrongPathString);
		//�����ڴ���·�����Ƿ���ִ��
		try{
			testServer.Start_Server();
		}catch (Exception e) {
			System.out.println("It's not running now!");
		}
		//�����Ƿ����ڵ����и���·����������Ϊ�������Ԥ��
		testServer.getinstance(rightPathString);
		try{
			testServer.Start_Server();
		}catch (Exception e) {
			System.out.println("It's not running now!");
		}
	}

	public void testServer_status() {
		TNServer testServer=TNServer.getinstance(rightPathString);
		
		//��ִ��Start_Serverǰ���鿴Server_status�ķ���ֵ�Ƿ���ȷ��
		System.out.println("Now I didn't start the program , what will it return?\n");
		System.out.println((testServer.server_status() ? "It has started":"It hasn't started"));
		
		
		
		testServer.Start_Server();
		//��ִ��Start_Server�󣬲鿴Server_status�ķ���ֵ�Ƿ���ȷ����Ϊ���ں�̨����ִ�У� �ڿ���̨����exit���˳��Ż������ӡ
		System.out.println("Now I have stoped the program , what will it return?\n");
		System.out.println((testServer.server_status() ? "It has started":"It hasn't started"));
	}

	public void testStart_Server() {
		//��������·��������Start_Server�ĸ��ֹ��ܣ���Ϊjunitֻ�ܾ�̬���ԣ����Բ�������Ҫ�ڴ˽��и������������в��ԡ�
		
		TNServer testServer=TNServer.getinstance(rightPathString);
		try{
			
			testServer.Start_Server();
			
		}catch (Exception e) {
			
			System.out.println("It's not running now!");
			
		}
	}

}
