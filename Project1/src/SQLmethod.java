
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// module처럼 SQL을 사용하게 하기위한 클라스
public class SQLmethod {
	public static String url = "localhost/practice?characterEncoding=UTF-8&serverTimezone=UTC";
	public static String user = "root";
	public static String password = "tjsvndrl12!";
	
	// DB와 연결해주는 메소드
	public static Connection makeConnection() {
		
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//con = DriverManager.getConnection(url, "root", "tjsvndrl12!");
			con = DriverManager.getConnection("jdbc:mysql://" + url, user, password);
		}
		catch(ClassNotFoundException e) {
			//System.out.println(e.getMessage());
		}
		catch(SQLException e) {
			//System.out.println("SQLException = " + e.getMessage());
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
			int Chk[][] = new int[16][2];
			stu[n] = new Student(rs.getInt("id"), rs.getString("name"), rs.getInt("attendance"), rs.getInt("middletest"), rs.getInt("lasttest"), rs.getInt("task"), rs.getInt("quiz"), rs.getInt("announce"), rs.getInt("report"), rs.getInt("ETC"));
			for(int a = 0; a<16;a++) {
				for(int b= 0; b< 2 ; b++) {
					String str = "UCheck" + ((a*2 + b) + 1);
					Chk[a][b] = rs.getInt(str);
				}
			}
			stu[n].setAttendance(Chk);
			
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
				String sql = "INSERT INTO student VALUES"
						+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				//(`id`, `name`, `attendance`,`middletest`,`lasttest`,`task`,`quiz`,`announce`,`report`,`ETC`, 'UCheck')
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
				
				int Chk[][] = stu[n].getAttendance();
				for(int a = 0; a<16;a++) {
					for(int b= 0; b< 2 ; b++) {
						pstmt.setInt((11 + (a*2) + b), Chk[a][b]);
					}
				}
				
				pstmt.execute();
				pstmt.close();
			}
			else {	
			}
		}
		con.close();
	}
	
	public static void Delete() throws SQLException{
		Connection con = makeConnection();
		Statement stmt = con.createStatement();
		String sql = "TRUNCATE `student`";
		
		if(stmt.executeUpdate(sql) == 1) {
			System.out.println("succeed");
		}
		else {
			System.out.println("fail");
		}
		con.close();
		stmt.close();
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
