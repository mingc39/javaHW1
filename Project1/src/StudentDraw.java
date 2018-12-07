//�ؾ��Ұ� 
//stdents �迭�� �޾ƿ;��� 
//�� graphType�� ���� stdents�迭 ���� Ÿ�Ժ����� �޾ƿͼ� 
//�װ� ����ؼ� �����뺰�� ������, �׷����� �׸���.

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class StudentDraw extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2770773134514289244L;
	DrawPanel dp;
	Student[] st;
	String gt;
	int graphnumber = 99;
	
	public StudentDraw(Student[] students, String graphType) {
		st =students;
		gt =graphType;
		dp = new DrawPanel();
		//�⼮,�߰� ����, �⸻ ����, ���� ����, ���� ����, ��ǥ, ����, ����, ��Ÿ ����
		switch(gt) {
			case"�⼮":
				graphnumber=0; break;
			case"�߰� ����":
				graphnumber=1; break;
			case"�⸻ ����":
				graphnumber=2; break;
			case"���� ����":
				graphnumber=3; break;
			case"���� ����":
				graphnumber=4; break;
			case"��ǥ ����":
				graphnumber=5; break;
			case"����":
				graphnumber=6; break;
			case"��Ÿ ����":
				graphnumber=7; break;
		}
		add(dp);
		//setSize(950, 630);
		setSize(950, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		//this.students=students[];
		//this.graphType = graphType;
	}

	/*public static void main(String[] args) {
		Student[] students = null;  //?
		String graphType = null;  //?...
		
		StudentDraw sd = new StudentDraw(students, graphType); 
		//�̰� ������ �ٸ� ���ο��� studentDraw�� ȣ���ؾ���
	}*/

	class DrawPanel extends JPanel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 8054082226261107522L;
		ArrayList<Integer> std = new ArrayList<Integer>();
		//�⼮,�߰� ����, �⸻ ����, ���� ����, ���� ����, ��ǥ ����, ����, ��Ÿ ����	
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(5));
			g2.drawLine(30, 30, 30, 330); // ���μ�
			g2.drawLine(30, 330, 850, 330); // ���μ�
			this.setBackground(Color.WHITE);
			g2.setColor(Color.BLACK);  
			g2.drawString("�л���", 10, 20); //�׷����� �л����� ��ġ
			//g2.setColor(Color.BLACK);  
			//g2.drawString(gt, 400, 20); //�׷����� �л����� ��ġ
			g2.setColor(Color.BLACK); 
			g2.drawString("����" , 860 , 555); //�׷����� ������ ��ġ*/
			
			
			int num[] = new int[11]; //���̵��� ���� ����
			
			for (int i = 0; i <st.length/*��ü��*/; i++) {
				int score =st[i].getScores()[graphnumber];
				if(0<=score&&score<10) {
					num[0] ++; //10���̸�
				}else if(10<=score&&score<20) {
					num[1] ++; //10����
				}else if(20<=score&&score<30) {
					num[2] ++; //20����
				}else if(30<=score&&score<40) {
					num[3] ++; //30����
				}else if(40<=score&&score<50) {
					num[4] ++; //40����
				}else if(50<=score&&score<60) {
					num[5] ++; //50����
				}else if(60<=score&&score<70) {
					num[6] ++; //60����
				}else if(70<=score&&score<80) {
					num[7] ++; //70����
				}else if(80<=score&&score<90) {
					num[8] ++; //80����
				}else if(90<=score&&score<100) {
					num[9] ++; //90����
				}else if(score==100) {
					num[10] ++; //100��
				}
			}
			
			int max = 0;
			for(int i : num)
				if(i > max) max = i;
			max += 10 - max % 10;
			
			g2.setStroke(new BasicStroke(1));
			for(int i = 1; i < max; i++) {
				if(i % 10 == 0) g2.setColor(Color.GRAY);
				else g2.setColor(Color.LIGHT_GRAY);
				g2.drawLine(30, 330 - ((330 / max) * i), 850, 330 - ((330 / max) * i));
				g2.setColor(Color.BLACK);
				g2.drawString(i + "", 10, 330 - ((330 / max) * i - 3));
			}
			
			
			
			
			for(int i=0; i<=10; i++) {
				int height = 330 - ((330 / max) * num[i]);//- (3 * num[i]);
				g2.setColor(Color.BLACK); //������ ���� -> �л��� 
				 // �������� ���� ǥ��
				g2.drawString(num[i] + "", 52 + (i * 75), height - 2);
				g2.setColor(Color.BLUE);
				g2.fillRect(40 + (i * 75), height, 30, 330 - height); // �׷����� ũ�⼳�� (x,y,����,����)
				//g2.setColor(Color.BLACK);
				//g2.drawString(40 + (i * 80)+"", height,  330 - height);
				g2.setColor(Color.BLACK); //������ ����  -> ������
				g2.drawString((i*10) + "", 40 + (i * 75) +12, 350);
				
			}
		}
	}
}