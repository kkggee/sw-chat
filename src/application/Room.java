package application;
import java.util.Vector;

public class Room {
    String title;//������
    int count;//�� �ο���
    String boss;//����(�� ������)
    Vector<Service> userV;//userV: ���� �濡 ������ Client���� ����
    
    public Room() {
    	userV = new Vector<>();
	} 
}