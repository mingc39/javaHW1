
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.NullPointerException;


// module처럼 SQL을 사용하게 하기위한 클라스
public class SQLmethod {
	
	// DB와 연결해주는 메소드
	public static Connection makeConnection() {
		String url = "jdbc:mysql://localhost/practice?characterEncoding=UTF-8&serverTimezone=UTC";
		
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, "root", "tjsvndrl12!");
		}
		catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		catch(SQLException e) {
			System.out.println("SQLException = " + e.getMessage());
		}
		return con;
	}
	
	// DB에서 Student의 데이터를 불러와 Student 배열로 반환
	public static Student[] open() throws SQLException{
		Connection con = makeConnection();
		String sql = "SELECT *FROM `student`";
		PreparedStatement pstmt = con.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		int num = 0;
		while(rs.next()) {
			num++;
		}
		rs.close();
		rs = pstmt.executeQuery();
		Student stu[] = new Student[num];
		for(int n = 0; rs.next();n++) {
			stu[n] = new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("attendance"), rs.getInt("middletest"), rs.getInt("lasttest"), rs.getInt("task"), rs.getInt("quiz"), rs.getInt("announce"), rs.getInt("report"), rs.getInt("ETC"));
		}
		pstmt.close();
		rs.close();
		return stu;
	}
	
	// Student 배열을 받아와 DB에 저장
	public static void Insert(Student stu[]) throws SQLException{
		Connection con = makeConnection();
		for(int n = 0; n < stu.length ;n++) {
			if(search(stu[n])) {
				String sql = "INSERT INTO student(`id`, `name`, `attendance`,`middletest`,`lasttest`,`task`,`quiz`,`announce`,`report`,`ETC`)VALUES"
						+ "(?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, stu[n].getStudentID());
				pstmt.setString(2, stu[n].getName());
				pstmt.setInt(3, stu[n].getAttendanceScore());
				pstmt.setInt(4, stu[n].getMidTest());
				pstmt.setInt(5, stu[n].getFinalTest());
				pstmt.setInt(6, stu[n].getHomework());
				pstmt.setInt(7, stu[n].getQuiz());
				pstmt.setInt(8, stu[n].getPt());
				pstmt.setInt(9, stu[n].getReport());
				pstmt.setInt(10, stu[n].getOthers());
				
				pstmt.execute();
				pstmt.close();
			}
			else {	
			}
		}
		con.close();
	}
	
	// Insert메소드를 사용하기 전 중복되는 id가 있는지 확인
	public static boolean search(Student stu) throws SQLException{
		String sql = "SELECT *FROM `student`";
		Connection con = makeConnection();
		PreparedStatement pstmt = con.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			if(stu.getStudentID() == rs.getInt("id")) {
				return false;
			}
		}
		return true;
	}
	
}
