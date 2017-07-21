package com.yunye.tests;

import com.yunye.code.PayInfo;
import com.yunye.code.analyzeAndPrint.OfficeAnalyze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
	public static void main(String args){
		Socket s = null;
		InputStream in = null;
		try{
			s = new Socket("183.174.60.20",10000);
			
			String identify_code =args;  //验证码
			in= s.getInputStream();
			PrintStream out = new PrintStream(s.getOutputStream());
			out.write(identify_code.getBytes());
			
			
			 BufferedReader reader = new BufferedReader(
					 new InputStreamReader(in));
			 
			 //获取文件名
			 String file_name = reader.readLine().replace("\n","");
			 String type = reader.readLine();
			String[] split = type.replace("\n","").split(" ");
			//默认规定的文件路径
			 String path ="D://";
			 File file = new File(path+file_name);
			 
			 if(!file.exists()){
				 file.createNewFile();
			 }
			 else
			 {
			 	file.delete();
			 	file.createNewFile();
			 }
			 
			 FileOutputStream des_file = new FileOutputStream(file);
			 
			 //将从服务器来的数据写入终端机
			 byte b[] =new byte[1024];
			 int len =0;
			 //System.out.println("文件接收中");
			 while((len = in.read(b))!=-1){
				 des_file.write(b);
			 }
			 des_file.close();
			OfficeAnalyze officeAnalyze = OfficeAnalyze.getInstance();
			officeAnalyze.setFile(file);
			PayInfo payInfo = PayInfo.getInstance();
			if(split[0] == "1")
			{
				payInfo.setIsdouble(true);
			}
			else
			{
				payInfo.setIsdouble(false);
			}
			payInfo.setAll(true);
			officeAnalyze.setPages(Integer.parseInt(split[1]));
			payInfo.setMultiPages(Integer.parseInt(split[2]));
			payInfo.setCopies(Integer.parseInt(split[3]));

			officeAnalyze.print();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
