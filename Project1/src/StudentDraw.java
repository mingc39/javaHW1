

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

	public StudentDraw() {
		dp = new DrawPanel();
		add(dp);
		setSize(950, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		StudentDraw sd = new StudentDraw();
	}

	class DrawPanel extends JPanel {
		ArrayList<Integer> students = new ArrayList<Integer>();

		public DrawPanel() {
		}

		public void drawScore(int score) {
			students.add(score);
			repaint();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(5));
			g2.drawLine(30, 30, 30, 330); // 세로선
			g2.drawLine(30, 330, 850, 330); // 가로선
			this.setBackground(Color.WHITE);
			g2.setColor(Color.BLACK);  
			g2.drawString("학생수", 10, 20); //그래프상 학생수의 위치
			g2.setColor(Color.BLACK); 
			g2.drawString("점수" , 860 , 335); //그래프상 점수의 위치
			int num[] = new int[11]; //아이들의 점수 비율
			//num[0]=10;
			//num[2]=30;
			
			
			for (int i = 0; i < students.size/*객체수*/(); i++) {
				int score = students.get(i);
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
				//위에는 점수를 받아서 그거에 따른 점수별대의 사람을 나누고
				//아래는 다시 반복문을 써서 num의 수만큼 높이를 조정하게 만든다.
			}
			
			for(int i=0; i<=10; i++) {
				int height = 330 - (3 * num[i]);
				g2.setColor(Color.BLACK); //세로의 점수 -> 학생수 
				g2.drawString(num[i] + "", 10, height); // 세로축의 점수 표기
				g2.setColor(Color.BLUE);
				g2.fillRect(40 + (i * 75), height, 30, 330 - height); // 그래프의 크기설정 (x,y,가로,세로)
				g2.setColor(Color.BLACK); //가로의 순서  -> 점수대
				g2.drawString((i*10) + "", 40 + (i * 75) +12, 350);
			}
		}
	}
}
