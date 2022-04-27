  package application;

import java.net.URL;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MembersController{
	@FXML AnchorPane signup_main;
	@FXML TextField name;
	@FXML TextField id;
	@FXML PasswordField pwd;
	@FXML TextField tel;
	@FXML Button submitBtn;
	@FXML Button cancelBtn;
	

	String DRIVER="com.mysql.cj.jdbc.Driver";
	String URL="jdbc:mysql://localhost/signup";
	String USER="root";
	String PASS="0000";
	
	public Connection getConn() {
			  Connection con=null;
			  try {
				  Class.forName(DRIVER);
				  con=DriverManager.getConnection(URL,USER,PASS); //연결
			  }catch(Exception e) {
				  e.printStackTrace();
			  }
			  return con;
	}
	@FXML public void submitAction(ActionEvent event) throws Exception{
		 String uName=name.getText();
		 String uId=id.getText();
	 	 String uPwd=pwd.getText();
	 	 String uTel=tel.getText();
		
		 boolean ok=false;
	     Connection con = null;       
	     PreparedStatement ps = null; 
	     
	     String name=uName;
	     String id=uId;
	     String pwd=uPwd;
	     String tel=uTel;
	     
	     try {
	    	 con=getConn();
	    	 String sql="insert into signup values(?,?,?,?)"; //signup이라는 테이블에 값 삽입
	    	 
	    	 ps=con.prepareStatement(sql);
	    	 ps.setString(1,name);
	    	 ps.setString(2,id);
	    	 ps.setString(3,pwd);
	    	 ps.setString(4,tel);
	    	 int r=ps.executeUpdate();
	    	 if(r>0) {
	    		 System.out.println("가입 성공");
	    		 ok=true;
	    	 }else {
	    		 System.out.println("가입 실패");
	    	 }
	     }catch(Exception e) {
	    	 e.printStackTrace();
	     }finally{
			   if(ps!=null) try{ps.close();}catch(SQLException ex){}
			   if(con!=null) try{con.close();}catch(SQLException ex){}
			  }
	}
	
	@FXML public void cancelAction(ActionEvent event) throws Exception{ //cancel버튼 누르면 Main페이지로 돌아가기
	      Stage primaryStage = new Stage();
	      Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
	      primaryStage.setScene(new Scene(root));
	        primaryStage.show();
	        primaryStage.setResizable(false);
	        
	        Stage stage = (Stage)cancelBtn.getScene().getWindow();
	        stage.close();
	}

	public void main(String[] args) {

		new MembersController();
	}
}
