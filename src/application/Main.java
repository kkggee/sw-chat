package application;
import javafx.application.Application;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.layout.BorderPane;
import java.awt.BorderLayout; import java.awt.event.ActionEvent; import java.awt.event.ActionListener;
import java.io.BufferedReader; import java.io.IOException; import java.io.InputStreamReader; import java.io.PrintWriter;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.io.BufferedReader; 
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException; 
import java.io.InputStream; 
import java.io.InputStreamReader; 
import java.io.OutputStream; 
import java.io.OutputStreamWriter; 
import java.net.ServerSocket; 
import java.net.Socket;
import java.net.Socket;

public class Main extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root=FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene=new Scene(root);
		// System.out.println("hi");
		primaryStage.setTitle("Main");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setWidth(360); 
        primaryStage.setHeight(550); 
        primaryStage.setResizable(false);
	}
	
	public static void main(String[] args) throws IOException{
        launch(args);
	}
}