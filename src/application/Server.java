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
    //Server클래스: 소켓을 통한 접속서비스, 접속클라이언트 관리
	Vector<Service> allV;//모든 사용자(대기실사용자 + 대화방사용자)
	Vector<Service> waitV;//대기실 사용자	   
	Vector<Room> roomV;//개설된 대화방(대화방사용자)

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
		//현재 실행중인 ip + 명시된 port ----> 소켓서비스 
		   System.out.println("Start Server.......");
		   while(true){
			   Socket s = ss.accept();//클라이언트 접속 대기	
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

