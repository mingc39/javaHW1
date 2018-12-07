//해야할것 
//stdents 배열을 받아와야함 
//단 graphType에 따라 stdents배열 안의 타입변수를 받아와서 
//그걸 사용해서 점수대별로 나누고, 그래프를 그린다.

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StudentDraw extends JFrame {
	DrawPanel dp;
	Student[] st;
	String gt;
	int graphnumber = 99;
	
	public StudentDraw(Student[] students, String graphType) {
		st =students;
		gt =graphType;
		dp = new DrawPanel();
		//출석,중간 시험, 기말 시험, 과제 점수, 퀴즈 점수, 발표, 점수, 보고서, 기타 점수
		switch(gt) {
			case"출석":
				graphnumber=0; break;
			case"중간 시험":
				graphnumber=1; break;
			case"기말 시험":
				graphnumber=2; break;
			case"과제 점수":
				graphnumber=3; break;
			case"퀴즈 점수":
				graphnumber=4; break;
			case"발표 점수":
				graphnumber=5; break;
			case"보고서":
				graphnumber=6; break;
			case"기타점수":
				graphnumber=7; break;
		}
		add(dp);
		setSize(950, 630);
		//setSize(950, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		//this.students=students[];
		//this.graphType = graphType;
	}

	/*public static void main(String[] args) {
		Student[] students = null;  //?
		String graphType = null;  //?...
		
		StudentDraw sd = new StudentDraw(students, graphType); 
		//이게 원래는 다른 메인에서 studentDraw를 호출해야함
	}*/

	class DrawPanel extends JPanel {
		
		ArrayList<Integer> std = new ArrayList<Integer>();
		//출석,중간 시험, 기말 시험, 과제 점수, 퀴즈 점수, 발표 점수, 보고서, 기타 점수	
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(5));
			//g2.drawLine(30, 30, 30, 330); // 세로선
			//g2.drawLine(30, 750, 850, 330); // 가로선
			g2.drawLine(30, 30, 30, 550); // 세로선
			g2.drawLine(30, 550, 850, 550); // 가로선
			this.setBackground(Color.WHITE);
			g2.setColor(Color.BLACK);  
			g2.drawString("학생수", 10, 20); //그래프상 학생수의 위치
			g2.setColor(Color.BLACK);  
			g2.drawString(gt, 400, 20); //그래프상 학생수의 위치
			g2.setColor(Color.BLACK); 
			g2.drawString("점수" , 860 , 555); //그래프상 점수의 위치
			int num[] = new int[11]; //아이들의 점수 비율
			
			for (int i = 0; i <=st.length/*객체수*/; i++) {
				int score =st[i].getScores()[graphnumber];
				if(0<=score&&score<10) {
					num[0] ++; //10점미만
				}else if(10<=score&&score<20) {
					num[1] ++; //10점대
				}else if(20<=score&&score<30) {
					num[2] ++; //20점대
				}else if(30<=score&&score<40) {
					num[3] ++; //30점대
				}else if(40<=score&&score<50) {
					num[4] ++; //40점대
				}else if(50<=score&&score<60) {
					num[5] ++; //50점대
				}else if(60<=score&&score<70) {
					num[6] ++; //60점대
				}else if(70<=score&&score<80) {
					num[7] ++; //70점대
				}else if(80<=score&&score<90) {
					num[8] ++; //80점대
				}else if(90<=score&&score<100) {
					num[9] ++; //90점대
				}else if(score==100) {
					num[10] ++; //100점
				}
			}
			
			for(int i=0; i<=10; i++) {
				int height = 550 -(30 *num[i]);//- (3 * num[i]);
				g2.setColor(Color.BLACK); //세로의 점수 -> 학생수 
				g2.drawString(num[i] + "", 10, height); // 세로축의 점수 표기
				g2.setColor(Color.BLUE);
				g2.fillRect(40 + (i * 70), height, 25, 550 - height); // 그래프의 크기설정 (x,y,가로,세로)
				//g2.setColor(Color.BLACK);
				//g2.drawString(40 + (i * 80)+"", height,  330 - height);
				g2.setColor(Color.BLACK); //가로의 순서  -> 점수대
				g2.drawString((i*10) + "", 40 + (i * 70)+7, 570);
			}
		}
	}
}
