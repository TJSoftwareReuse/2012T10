package Server;

import static org.junit.Assert.*;

import org.junit.Test;

public class Server3_5Test {
	String wrongPathString = "I'm a wrong path";
	String rightPathString = "D:\\eclipse\\2012T10-master\\src\\Server\\Config_file_for_test\\config.properties";
	@Test
	public void testGetinstance() {
		//����ΪTNServer�������д���·���ĵ���
		Server3_5 testServer=Server3_5.getinstance(wrongPathString);
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
	
	@Test
	public void testServer_status() {
		
		Server3_5 testServer=Server3_5.getinstance(rightPathString);		
		//��ִ��Start_Serverǰ���鿴Server_status�ķ���ֵ�Ƿ���ȷ��
		System.out.println("Now I didn't start the program , what will it return?\n");
		System.out.println((testServer.server_status() ? "It has started":"It hasn't started"));
		
		
		
		testServer.Start_Server();
		//��ִ��Start_Server�󣬲鿴Server_status�ķ���ֵ�Ƿ���ȷ����Ϊ���ں�̨����ִ�У� �ڿ���̨����exit���˳��Ż������ӡ
		System.out.println("Now I have stoped the program , what will it return?\n");
		System.out.println((testServer.server_status() ? "It has started":"It hasn't started"));
	}

	@Test
	public void testStart_Server() {
		//��������·��������Start_Server�ĸ��ֹ��ܣ���Ϊjunitֻ�ܾ�̬���ԣ����Բ�������Ҫ�ڴ˽��и������������в��ԡ�
		
		Server3_5 testServer=Server3_5.getinstance(rightPathString);		
		try{
			
			TestThread test = new TestThread();

	        test.start();
	        try {
	            Thread.currentThread().sleep(2000);
	        } catch (InterruptedException e) {
	             
	        }
	        test.interrupt();

			
		}catch (Exception e) {
			
			System.out.println("It's not running now!");
			
		}
	}

}


