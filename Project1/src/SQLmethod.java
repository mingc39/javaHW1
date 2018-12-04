
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// moduleó�� SQL�� ����ϰ� �ϱ����� Ŭ��
public class SQLmethod {
	
	// DB�� �������ִ� �޼ҵ�
	public static Connection makeConnection() {
		String url = "jdbc:mysql://localhost/practice?characterEncoding=UTF-8&serverTimezone=UTC";
		
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("DB connect��");
			con = DriverManager.getConnection(url, "root", "tjsvndrl12!");
			System.out.println("DB connect complete");
			
		}
		catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		catch(SQLException e) {
			System.out.println("SQLException = " + e.getMessage());
		}
		return con;
	}
	
	// DB�� Student�� �����͸� ����
	public static void Insert(Student stu) throws SQLException{
		Connection con = makeConnection();
		
		String sql = "INSERT INTO student(`id`, `name`, `attendance`,`middletest`,`lasttest`,`task`,`quiz`,`announce`,`report`,`ETC`)VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql.toString());
		pstmt.setInt(1, stu.getStudentID());
		pstmt.setString(2, stu.getName());
		pstmt.setInt(3, stu.getAttendanceScore());
		pstmt.setInt(4, stu.getMidTest());
		pstmt.setInt(5, stu.getFinalTest());
		pstmt.setInt(6, stu.getHomework());
		pstmt.setInt(7, stu.getQuiz());
		pstmt.setInt(8, stu.getPt());
		pstmt.setInt(9, stu.getReport());
		pstmt.setInt(10, stu.getOthers());
		
		pstmt.execute();
		con.close();
		pstmt.close();
	}
	
	//�й��� �Է¹޾� �ش� �л��� ����
	public static void Delete(int id) throws SQLException{
		Connection con = makeConnection();
		Statement stmt = con.createStatement();
		String sql = "DELETE FROM `student` WHERE id =" + id;
		
		if(stmt.executeUpdate(sql) == 1) {
			System.out.println("succeed");
		}
		else {
			System.out.println("fail");
		}
		con.close();
		stmt.close();
	}
	
	//�߰������� ����� ����
	public static double avgMT() throws SQLException{
		Connection con = makeConnection();
		String sql = "SELECT *FROM `student`";
		PreparedStatement pstmt = con.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		double total = 0;
		double num = 0;
		while(rs.next()) {
			total += rs.getInt("middletest");
			num++;
		}
		return total/num;
	}
	
	// �⸻���
	public static double avgLT() throws SQLException{
		Connection con = makeConnection();
		String sql = "SELECT *FROM `student`";
		PreparedStatement pstmt = con.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		double total = 0;
		double num = 0;
		while(rs.next()) {
			total += rs.getInt("lasttest");
			num++;
		}
		return total/num;
	}
	
	// �������
	public static double avgTASK() throws SQLException{
		Connection con = makeConnection();
		String sql = "SELECT *FROM `student`";
		PreparedStatement pstmt = con.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		double total = 0;
		double num = 0;
		while(rs.next()) {
			total += rs.getInt("task");
			num++;
		}
		return total/num;
	}
	
	// �������
	public static double avgQUIZ() throws SQLException{
		Connection con = makeConnection();
		String sql = "SELECT *FROM `student`";
		PreparedStatement pstmt = con.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		double total = 0;
		double num = 0;
		while(rs.next()) {
			total += rs.getInt("quiz");
			num++;
		}
		return total/num;
	}
	
	// ��ǥ���
	public static double avgAN() throws SQLException{
		Connection con = makeConnection();
		String sql = "SELECT *FROM `student`";
		PreparedStatement pstmt = con.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		double total = 0;
		double num = 0;
		while(rs.next()) {
			total += rs.getInt("announce");
			num++;
		}
		return total/num;
	}
	
	// �������
	public static double avgREPORT() throws SQLException{
		Connection con = makeConnection();
		String sql = "SELECT *FROM `student`";
		PreparedStatement pstmt = con.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		double total = 0;
		double num = 0;
		while(rs.next()) {
			total += rs.getInt("report");
			num++;
		}
		return total/num;
	}
	
	
	// ��Ÿ���
	public static double avgETC() throws SQLException{
		Connection con = makeConnection();
		String sql = "SELECT *FROM `student`";
		PreparedStatement pstmt = con.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		double total = 0;
		double num = 0;
		while(rs.next()) {
			total += rs.getInt("ETC");
			num++;
		}
		return total/num;
	}
	
}
