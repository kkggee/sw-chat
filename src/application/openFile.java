package application;

import java.awt.Container;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import javax.swing.*;

class openFile extends JFrame implements ActionListener  {
   Container c = getContentPane();
   FileDialog fd;
    JButton b1,b2;
    Socket s;
    JTextField tf;
    String directory="", file="";
    
    public openFile() throws Exception{
        b1=new JButton("파일선택");
        b1.addActionListener(this);
        tf=new JTextField();
        b2=new JButton("파일전송");
        b2.addActionListener(this);
        add(b1,"North");
        add(tf,"Center");
        add(b2,"South");  
        setBounds(300,300,300,300);
        setVisible(true);
   }

      
   @Override
   public void actionPerformed(ActionEvent e) {
	   try{
             if(e.getActionCommand().equals("파일선택")){
              fd=new FileDialog(this,"",FileDialog.LOAD);
              fd.setVisible(true);
              tf.setText("");
              directory=fd.getDirectory();
              file=fd.getFile();
              tf.setText(directory+file);
             }else{  
	              s=new Socket("192.168.0.18", 8081);
	              FileSender fs = new FileSender(s, directory, file);
	              fs.start();
	              JOptionPane.showMessageDialog(this, "파일전송완료!!");
	         }    
	   } catch (IOException e1) {
		   e1.printStackTrace();
	   }
   }
}

class FileSender extends Thread {
   
    String directory;
    String file;
    Socket socket;
    DataOutputStream dos;
    FileInputStream fis;
    BufferedInputStream bis;
 
    public FileSender(Socket socket, String directory, String file) {
       
        this.socket = socket;
        this.file = file;
        this.directory = directory;
        
        try {
            // 데이터 전송용 스트림 생성
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void run() {
       
       try {
         dos.writeUTF("file");
         dos.flush();
         
         //전송할 파일을 읽어서 Socket Server에 전송
         String result = fileRead(dos);
          System.out.println("result : " + result);
          
      } catch (IOException e) {
         e.printStackTrace();
      }finally{
            try { dos.close(); } catch (IOException e) { e.printStackTrace(); }
            try { bis.close(); } catch (IOException e) { e.printStackTrace(); }
        }

    }
    
    private String fileRead(DataOutputStream dos){
        String result;
       try {
            System.out.println("파일 전송 작업을 시작합니다.");
            dos.writeUTF(file);
            System.out.println("파일 이름(" + file + ")을 전송하였습니다.");
 
            // 파일을 읽어서 서버에 전송
            File file1 = new File(directory + "/" + file);
            fis = new FileInputStream(file1);
            bis = new BufferedInputStream(fis);
            
            int len;
            int size = 4096;
            byte[] data = new byte[size];
            while ((len = bis.read(data)) != -1) {
                dos.write(data, 0, len);
            }
            
            //서버에 전송
            dos.flush();
            result = "SUCCESS";
        } catch (IOException e) {
            e.printStackTrace();
            result = "ERROR";
        }finally{
            try { fis.close(); } catch (IOException e) { e.printStackTrace(); }
        }
        
        return result;
    }
}
