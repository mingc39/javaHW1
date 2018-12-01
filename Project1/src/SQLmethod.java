
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

//데이터 베이스에 저장하고 삭제하는등 

public class SQLmethod {
	public static Connection makeConnection() {
		String url = "jdbc:mysql://localhost/practice?characterEncoding=UTF-8&serverTimezone=UTC";
		
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("DB connect중");
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
	
	public static void Insert(int id, String name, int at, int mt, int lt, int task, int quiz, int an, int re, int ETC) throws SQLException{
		Connection con = makeConnection();
		
		String sql = "INSERT INTO student(`id`, `name`, `attendance`,`middletest`,`lasttest`,`task`,`quiz`,`announce`,`report`,`ETC`)VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql.toString());
		pstmt.setInt(1, id);
		pstmt.setString(2, name);
		pstmt.setInt(3, at);
		pstmt.setInt(4, mt);
		pstmt.setInt(5, lt);
		pstmt.setInt(6, task);
		pstmt.setInt(7, quiz);
		pstmt.setInt(8, an);
		pstmt.setInt(9, re);
		pstmt.setInt(10, ETC);
		
		pstmt.execute();
		con.close();
		pstmt.close();
		/*if(pstmt.executeUpdate(sql) == 1) {
			System.out.println("레코드 추가 성공");
		}
		else{
			System.out.println("레코드 추가 실패");
		}
		con.close();
		pstmt.close();*/
	}
	
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
}
