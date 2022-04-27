package application;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.net.UnknownHostException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.application.Platform;
import java.sql.*;
public class LoginController {
	@FXML Button btnLogin;
	@FXML Button btnSignup;
	@FXML TextField inputID;
	@FXML PasswordField inputPW;
	@FXML Button btnFind;
	
	String DRIVER="com.mysql.cj.jdbc.Driver";
	String URL="jdbc:mysql://localhost/signup";
	String USER="root";
	String PASS="0000";
    public Connection getConn() { //����
           Connection con=null;
           try {
              Class.forName(DRIVER);
              con=DriverManager.getConnection(URL,USER,PASS);
           }catch(Exception e) {
              e.printStackTrace();
           }
           return con;
   }
    @FXML public void Login(ActionEvent event) throws Exception{
       String uId=inputID.getText();
       String uPwd=inputPW.getText();
       
       Connection con = null;
       PreparedStatement pst = null;
	   ResultSet rs = null;
	   MainChat m;
	   String ID=uId;
	   String PW=uPwd;
     
	   try {
		   con=getConn();
		   String sql="select id,pwd from signup where id=? and pwd=?"; //sql��
		   pst=con.prepareStatement(sql);
		   pst.setString(1,uId);
		   pst.setString(2,uPwd);
		   rs=pst.executeQuery();
         
		   if(rs.next()) {
			   if(rs.getString("id").equals(uId) && rs.getString("pwd").equals(uPwd)) { //���̵� ��� ��ġ�� ���
				   
				   try {
					   System.out.println("login success");
					   m=new MainChat();
				   }catch(Exception e) {
					   e.printStackTrace();
				   }
			   }
		   }

		   else { //�α��� ����
			   	Alert loginFail = new Alert(AlertType.ERROR);
			   	loginFail.setHeaderText("����");
			   	loginFail.setContentText("Login Fail");
			   	loginFail.showAndWait();
           }	 
	   }catch(Exception e) {
		   e.printStackTrace();
	   }finally {
		   if(con != null) try {con.close();} catch (SQLException e) {e.printStackTrace();}
		   if(pst != null) try {pst.close();} catch (SQLException e) {e.printStackTrace();}
		   if(rs != null) try {rs.close();} catch (SQLException e) {e.printStackTrace();}
	   } 
   }
   
   @FXML public void Signup() throws Exception { //ȸ������ �������� �Ѿ��
	     
	      Stage primaryStage = new Stage();
	      Parent signUp = FXMLLoader.load(getClass().getResource("signupbtn/SignupBtn.fxml"));
	      primaryStage.setScene(new Scene(signUp));
	      primaryStage.show();
	      primaryStage.setResizable(false);
	        
	        Stage stage = (Stage)btnSignup.getScene().getWindow();
	        stage.close();
	   }
	@FXML public void IDPWFind() throws Exception{ //ID/PW�ٲٴ� �������� �Ѿ��
	      Stage primaryStage = new Stage();
	      Parent idpwFind = FXMLLoader.load(getClass().getResource("IDPWFind.fxml"));
	      primaryStage.setScene(new Scene(idpwFind));
	      primaryStage.show();
	      primaryStage.setResizable(false);
	        
	      Stage stage = (Stage)btnFind.getScene().getWindow();
	      stage.close();
	}
}
