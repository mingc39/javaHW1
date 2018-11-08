import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UIMain extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9063420066930412578L;

	public UIMain() {
		
		setTitle("성적 관리");
		
		menu();
		
		DefaultTableModel tableModel = new DefaultTableModel(new String[] {"학번", "이름", "출석", "중간 시험", "기말 시험", "과제", "퀴즈", "발표", "보고서", "기타"}, 30);
		JTable table = new JTable(tableModel);
		JScrollPane scroll = new JScrollPane(table);
		add(scroll);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000,500);
		setVisible(true);
		
	}
	
	void menu() {
		
		JMenuItem item;
		ActionListener listener;
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("파일");
		menuFile.setMnemonic(KeyEvent.VK_F);
		JMenu menuEdit = new JMenu("수정");
		menuEdit.setMnemonic(KeyEvent.VK_E);
		JMenu menuGraph = new JMenu("그래프");
		menuGraph.setMnemonic(KeyEvent.VK_G);
		
		listener = new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				switch(((JMenuItem) (e.getSource())).getText()) {
				case "DB 열기":
					break;
				case "DB 저장":
					break;
				case "CSV 열기":
					break;
				case "CSV 저장":
					break;
				case "종료":
					System.exit(0);
					break;
				}
			}
		};
		item = new JMenuItem("DB 열기");
		item.addActionListener(listener);
		menuFile.add(item);
		item = new JMenuItem("DB 저장");
		item.addActionListener(listener);
		menuFile.add(item);
		menuFile.addSeparator();
		item = new JMenuItem("CSV 열기", KeyEvent.VK_O);
		item.addActionListener(listener);
		menuFile.add(item);
		item = new JMenuItem("CSV 저장", KeyEvent.VK_S);
		item.addActionListener(listener);
		menuFile.add(item);
		menuFile.addSeparator();
		item = new JMenuItem("종료");
		item.addActionListener(listener);
		menuFile.add(item);
		
		listener = new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				switch(((JMenuItem) (e.getSource())).getText()) {
				case "입력":
					new UIInput();
					break;
				case "수정":
					break;
				case "검색":
					break;
				case "평균":
					break;
				case "출석 체크":
					break;
				case "반영 비율":
					break;
				}
			}
		};
		item = new JMenuItem("입력");
		item.addActionListener(listener);
		menuEdit.add(item);
		item = new JMenuItem("수정");
		item.addActionListener(listener);
		menuEdit.add(item);
		menuEdit.addSeparator();
		item = new JMenuItem("검색");
		item.addActionListener(listener);
		menuEdit.add(item);
		item = new JMenuItem("평균");
		item.addActionListener(listener);
		menuEdit.add(item);
		menuEdit.addSeparator();
		item = new JMenuItem("출석 체크");
		item.addActionListener(listener);
		menuEdit.add(item);
		menuEdit.addSeparator();
		item = new JMenuItem("반영 비율");
		item.addActionListener(listener);
		menuEdit.add(item);
		
		listener = new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				new UIGraph();
				/*switch(((JMenuItem) (e.getSource())).getText()) {
				case "입력":
					break;
				case "수정":
					break;
				case "검색":
					break;
				case "평균":
					break;
				case "출석 체크":
					break;
				case "반영 비율":
					break;
				}*/
			}
		};
		item = new JMenuItem("출석");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("중간 시험");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("기말 시험");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("과제 점수");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("퀴즈 점수");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("발표 점수");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("보고서");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("기타 점수");
		item.addActionListener(listener);
		menuGraph.add(item);
		
		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuGraph);
		setJMenuBar(menuBar);
		
	}

}
