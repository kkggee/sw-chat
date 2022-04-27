package application;
import java.util.Vector;

public class Room {
    String title;//방제목
    int count;//방 인원수
    String boss;//방장(방 개설자)
    Vector<Service> userV;//userV: 같은 방에 접속한 Client정보 저장
    
    public Room() {
    	userV = new Vector<>();
	} 
}