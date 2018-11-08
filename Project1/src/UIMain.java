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
		
		setTitle("���� ����");
		
		menu();
		
		DefaultTableModel tableModel = new DefaultTableModel(new String[] {"�й�", "�̸�", "�⼮", "�߰� ����", "�⸻ ����", "����", "����", "��ǥ", "����", "��Ÿ"}, 30);
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
		JMenu menuFile = new JMenu("����");
		menuFile.setMnemonic(KeyEvent.VK_F);
		JMenu menuEdit = new JMenu("����");
		menuEdit.setMnemonic(KeyEvent.VK_E);
		JMenu menuGraph = new JMenu("�׷���");
		menuGraph.setMnemonic(KeyEvent.VK_G);
		
		listener = new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				switch(((JMenuItem) (e.getSource())).getText()) {
				case "DB ����":
					break;
				case "DB ����":
					break;
				case "CSV ����":
					break;
				case "CSV ����":
					break;
				case "����":
					System.exit(0);
					break;
				}
			}
		};
		item = new JMenuItem("DB ����");
		item.addActionListener(listener);
		menuFile.add(item);
		item = new JMenuItem("DB ����");
		item.addActionListener(listener);
		menuFile.add(item);
		menuFile.addSeparator();
		item = new JMenuItem("CSV ����", KeyEvent.VK_O);
		item.addActionListener(listener);
		menuFile.add(item);
		item = new JMenuItem("CSV ����", KeyEvent.VK_S);
		item.addActionListener(listener);
		menuFile.add(item);
		menuFile.addSeparator();
		item = new JMenuItem("����");
		item.addActionListener(listener);
		menuFile.add(item);
		
		listener = new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				switch(((JMenuItem) (e.getSource())).getText()) {
				case "�Է�":
					new UIInput();
					break;
				case "����":
					break;
				case "�˻�":
					break;
				case "���":
					break;
				case "�⼮ üũ":
					break;
				case "�ݿ� ����":
					break;
				}
			}
		};
		item = new JMenuItem("�Է�");
		item.addActionListener(listener);
		menuEdit.add(item);
		item = new JMenuItem("����");
		item.addActionListener(listener);
		menuEdit.add(item);
		menuEdit.addSeparator();
		item = new JMenuItem("�˻�");
		item.addActionListener(listener);
		menuEdit.add(item);
		item = new JMenuItem("���");
		item.addActionListener(listener);
		menuEdit.add(item);
		menuEdit.addSeparator();
		item = new JMenuItem("�⼮ üũ");
		item.addActionListener(listener);
		menuEdit.add(item);
		menuEdit.addSeparator();
		item = new JMenuItem("�ݿ� ����");
		item.addActionListener(listener);
		menuEdit.add(item);
		
		listener = new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				new UIGraph();
				/*switch(((JMenuItem) (e.getSource())).getText()) {
				case "�Է�":
					break;
				case "����":
					break;
				case "�˻�":
					break;
				case "���":
					break;
				case "�⼮ üũ":
					break;
				case "�ݿ� ����":
					break;
				}*/
			}
		};
		item = new JMenuItem("�⼮");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("�߰� ����");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("�⸻ ����");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("���� ����");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("���� ����");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("��ǥ ����");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("����");
		item.addActionListener(listener);
		menuGraph.add(item);
		item = new JMenuItem("��Ÿ ����");
		item.addActionListener(listener);
		menuGraph.add(item);
		
		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuGraph);
		setJMenuBar(menuBar);
		
	}

}
