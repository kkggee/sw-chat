package application;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Server implements Runnable{
    //ServerŬ����: ������ ���� ���Ӽ���, ����Ŭ���̾�Ʈ ����
	Vector<Service> allV;//��� �����(���ǻ���� + ��ȭ������)
	Vector<Service> waitV;//���� �����	   
	Vector<Room> roomV;//������ ��ȭ��(��ȭ������)

	public Server() {
		allV = new Vector<>();
		waitV = new Vector<>();
		roomV = new Vector<>();
		
		new Thread(this).start();
	}
	
	@Override
	public void run(){
	   try {
		ServerSocket ss = new ServerSocket(8080);
		//���� �������� ip + ��õ� port ----> ���ϼ��� 
		   System.out.println("Start Server.......");
		   while(true){
			   Socket s = ss.accept();//Ŭ���̾�Ʈ ���� ���	
			   Service ser = new Service(s, this);
		   }
	   } catch (IOException e) {
		e.printStackTrace();
	  }	  
	}	
   public static void main(String[] args) {
	   new Server();
   }
}

