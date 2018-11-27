

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
			g2.drawLine(30, 30, 30, 330); // ���μ�
			g2.drawLine(30, 330, 850, 330); // ���μ�
			this.setBackground(Color.WHITE);
			g2.setColor(Color.BLACK);  
			g2.drawString("�л���", 10, 20); //�׷����� �л����� ��ġ
			g2.setColor(Color.BLACK); 
			g2.drawString("����" , 860 , 335); //�׷����� ������ ��ġ
			int num[] = new int[11]; //���̵��� ���� ����
			//num[0]=10;
			//num[2]=30;
			
			
			for (int i = 0; i < students.size/*��ü��*/(); i++) {
				int score = students.get(i);
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
				//������ ������ �޾Ƽ� �װſ� ���� ���������� ����� ������
				//�Ʒ��� �ٽ� �ݺ����� �Ἥ num�� ����ŭ ���̸� �����ϰ� �����.
			}
			
			for(int i=0; i<=10; i++) {
				int height = 330 - (3 * num[i]);
				g2.setColor(Color.BLACK); //������ ���� -> �л��� 
				g2.drawString(num[i] + "", 10, height); // �������� ���� ǥ��
				g2.setColor(Color.BLUE);
				g2.fillRect(40 + (i * 75), height, 30, 330 - height); // �׷����� ũ�⼳�� (x,y,����,����)
				g2.setColor(Color.BLACK); //������ ����  -> ������
				g2.drawString((i*10) + "", 40 + (i * 75) +12, 350);
			}
		}
	}
}
