package application;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FindController {
	
	@FXML Button findID;
	@FXML Button findPw;
	@FXML Button Backbtn;
	
	@FXML TextField findName;
	@FXML TextField findPhone;
	@FXML TextField findInputId;
	@FXML TextField findInputName;
	
    String DRIVER="oracle.jdbc.driver.OracleDriver";
	String URL="jdbc:oracle:thin:@localhost:1521:orcl";
	String USER="system";
	String PASS="1234";
	
    public Connection getConn() { //연결
        Connection con=null;
        try {
           Class.forName(DRIVER);
           con=DriverManager.getConnection(URL,USER,PASS);
        }catch(Exception e) {
           e.printStackTrace();
        }
        return con;
    }
    
    @FXML public void Find_id(ActionEvent event) throws Exception{
    	String uName=findName.getText();
    	String uTel=findPhone.getText();
    	
        Connection con = null;
        PreparedStatement pst = null;
 	    ResultSet rs = null;
 	    
 	    try {
 		   con=getConn();
 		   String sql="select id from signup where name=? AND tel=?"; //sql문
 		   pst=con.prepareStatement(sql);
 		   pst.setString(1,uName);
 		   pst.setString(2,uTel);
 		   rs=pst.executeQuery();
 		   
 		   if(rs.next()) {
 			   String ID = rs.getString("id");
			   Alert loginFail = new Alert(AlertType.CONFIRMATION);
			   loginFail.setHeaderText("아이디");
			   loginFail.setContentText(ID);
			   loginFail.showAndWait();
 		   }	   
		   else { //아이디 찾기 실패
			   Alert loginFail = new Alert(AlertType.ERROR);
			   loginFail.setHeaderText("실패");
			   loginFail.setContentText("Failed to find");
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
    
    @FXML public void Find_pw(ActionEvent event) throws Exception{
        String uID=findInputId.getText();
        String uName=findInputName.getText();
        
        Connection con = null;
        PreparedStatement pst = null;
 	    ResultSet rs = null;
 	    
 	    try {
 		   con=getConn();
 		   String sql="select pwd from signup where id=? AND name=?"; //sql문
 		   pst=con.prepareStatement(sql);
 		   pst.setString(1,uID);
 		   pst.setString(2,uName);
 		   rs=pst.executeQuery();
 		   
 		   if(rs.next()) {
 			   String PW = rs.getString("pwd");
			   Alert loginFail = new Alert(AlertType.CONFIRMATION);
			   loginFail.setHeaderText("비밀번호");
			   loginFail.setContentText(PW);
			   loginFail.showAndWait();	
 		   }	   
		   else { //아이디 찾기 실패
			   Alert loginFail = new Alert(AlertType.ERROR);
			   loginFail.setHeaderText("실패");
			   loginFail.setContentText("Failed to find");
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
    
    @FXML public void back() throws Exception { //회원가입 페이지로 넘어가기
	     
	      Stage primaryStage = new Stage();
	      Parent back = FXMLLoader.load(getClass().getResource("Main.fxml"));
	      primaryStage.setScene(new Scene(back));
	      primaryStage.show();
	      primaryStage.setResizable(false);
	        
	        Stage stage = (Stage)Backbtn.getScene().getWindow();
	        stage.close();
	   }
}
