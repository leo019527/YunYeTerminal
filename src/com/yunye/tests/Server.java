package com.yunye.tests;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

public class Server{
	
	public static String findFile(String identify_code){
		
		if(identify_code==null)return null;
		
		return "test.docx";
	}
	
	public static void main(String[] args){
		ServerSocket ss = null;
		try{
		//�������������ӣ��˿ں�Ϊ12345
		ss = new ServerSocket(10000);
		
		//�������˲�����Ӧ�ն˻������������˶��߳�ʵ�ֶ������ͬʱ����
		while(true){
			Socket s= ss.accept();
			
			//��Ӧ��ǰ������ն˻�
			new Start(s).start();
		}

		}catch(IOException e){
			e.printStackTrace();
		}	
	}
	
}

class Start extends Thread{
	Socket s = null;
	InputStream in =null;
	OutputStream out = null;
	BufferedReader reader = null;
	PrintStream print = null;
	
	public Start(Socket s){
		this.s = s;
	}
	
	
	
	public void run(){
		try{
		
			in = s.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in));
		
			String identify_code = reader.readLine();//�õ���֤��
			String file_path =Server.findFile(identify_code);	//findFile�����ļ�
			String file_name;
			
			if(file_path.lastIndexOf("//")!=-1)file_name = file_path.substring(file_path.lastIndexOf("//")).trim();
			else file_name = file_path.trim();
			
			
			FileInputStream des_file = null;
			try{
				des_file = new FileInputStream(file_path);
				
				
				out = s.getOutputStream();
				byte[] b = new byte[1024];
				int size = des_file.available();
				int len = 0;
				//���ļ���Ҳ������ն˻�
				String temp = file_name+"\n";
				
				out.write(temp.getBytes());
				out.flush();
				
				while((len = des_file.read(b))!=-1){
					
					out.write(b);
					out.flush();
				}
				
			}catch(IOException e){
				System.out.println("File not found!");
				e.printStackTrace();
			}
		}catch(IOException e){
			
			e.printStackTrace();
		}
	}
}