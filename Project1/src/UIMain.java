import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

//UIMain.java
//����â
public class UIMain extends JFrame {
	
	private static final long serialVersionUID = -9063420066930412578L;
	private StudentTable studentTable;
	
	public UIMain(StudentTable studentTable) {
		
		// â ���� ����
		setTitle("���� ����");
		
		this.studentTable = studentTable;
		
		// �޴� �߰�
		menu();
		
		// â�� ǥ �߰�
		add(studentTable.getScroll());
		
		// â �⺻ ����
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(JOptionPane.showConfirmDialog(null, "������ �����Ͻðڽ��ϱ�?", "����", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		setSize(1000,500);
		setVisible(true);
		
	}
	
	// �޴� �߰�
	private void menu() {
		
		// ����
		JMenuItem item;
		ActionListener listener;
		
		// �޴� ��, �޴� ��ü ����
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("����");
		menuFile.setMnemonic(KeyEvent.VK_F);
		JMenu menuEdit = new JMenu("����");
		menuEdit.setMnemonic(KeyEvent.VK_E);
		JMenu menuGraph = new JMenu("�׷���");
		menuGraph.setMnemonic(KeyEvent.VK_G);
		
		// =======================<���� �޴� ����>=======================
		// ������ ����
		listener = new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				switch(((JMenuItem) (e.getSource())).getText()) {
				case "DB ����":
					JOptionPane.showMessageDialog(null, "����� �����ϴ�.", "DB", JOptionPane.INFORMATION_MESSAGE);
					break;
				case "DB ����":
					JOptionPane.showMessageDialog(null, "����� �����ϴ�.", "DB", JOptionPane.INFORMATION_MESSAGE);
					break;
				case "CSV ����":
					JOptionPane.showMessageDialog(null, "����� �����ϴ�.", "CSV", JOptionPane.INFORMATION_MESSAGE);
					break;
				case "CSV ����":
					JOptionPane.showMessageDialog(null, "����� �����ϴ�.", "CSV", JOptionPane.INFORMATION_MESSAGE);
					break;
				case "����":
					if(JOptionPane.showConfirmDialog(null, "������ �����Ͻðڽ��ϱ�?", "����", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
						System.exit(0);
					break;
				}
			}
		};
		// �޴� ���� �� �߰�
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
		
		// =======================<���� �޴� ����>=======================
		// ������ ����
		listener = new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				switch(((JMenuItem) (e.getSource())).getText()) {
				case "�Է�":
					new UIInput(studentTable);
					break;
				case "����":
					if(studentTable.getSelectedRow() < 0) JOptionPane.showMessageDialog(null, "������ �л��� ǥ���� �������ּ���.", "����", JOptionPane.ERROR_MESSAGE);
					else {
						new UIInput(studentTable, studentTable.getSelectedStudentIndex());
					}
					break;
				case "�˻�":
					JOptionPane.showMessageDialog(null, "����� �����ϴ�.", "�˻�", JOptionPane.INFORMATION_MESSAGE);
					break;
				case "���":
					new UIStatistics(studentTable);
					break;
				case "�⼮ üũ":
					JOptionPane.showMessageDialog(null, "����� �����ϴ�.", "�⼮ üũ", JOptionPane.INFORMATION_MESSAGE);
					break;
				case "�ݿ� ����":
					new UIRaito(studentTable);
					break;
				case "���� ����":
					new UIGrade(studentTable);
					break;
				}
			}
		};
		// �޴� ���� �� �߰�
		item = new JMenuItem("�Է�");
		item.addActionListener(listener);
		item.setAccelerator(KeyStroke.getKeyStroke('N', Event.CTRL_MASK));
		menuEdit.add(item);
		item = new JMenuItem("����");
		item.addActionListener(listener);
		item.setAccelerator(KeyStroke.getKeyStroke('E', Event.CTRL_MASK));
		menuEdit.add(item);
		menuEdit.addSeparator(); // ======================================
		item = new JMenuItem("�˻�");
		item.addActionListener(listener);
		item.setAccelerator(KeyStroke.getKeyStroke('F', Event.CTRL_MASK));
		menuEdit.add(item);
		item = new JMenuItem("���");
		item.addActionListener(listener);
		item.setAccelerator(KeyStroke.getKeyStroke('T', Event.CTRL_MASK));
		menuEdit.add(item);
		menuEdit.addSeparator(); // ======================================
		item = new JMenuItem("�⼮ üũ");
		item.addActionListener(listener);
		item.setAccelerator(KeyStroke.getKeyStroke('U', Event.CTRL_MASK));
		menuEdit.add(item);
		menuEdit.addSeparator(); // ======================================
		item = new JMenuItem("�ݿ� ����");
		item.addActionListener(listener);
		item.setAccelerator(KeyStroke.getKeyStroke('R', Event.CTRL_MASK));
		menuEdit.add(item);
		item = new JMenuItem("���� ����");
		item.addActionListener(listener);
		item.setAccelerator(KeyStroke.getKeyStroke('G', Event.CTRL_MASK));
		menuEdit.add(item);
		
		// =======================<�׷��� �޴� ����>=======================
		// ������ ����
		listener = new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "����� �����ϴ�.", "�׷���", JOptionPane.INFORMATION_MESSAGE);
			}
		};
		// �޴� ���� �� �߰�
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
		
		// �޴� �ٿ� �޴� �߰�
		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuGraph);
		setJMenuBar(menuBar);
		
	}
	
}
