package exam09;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

public class BoardInsertExample {
    public static void main(String[] args) {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://10.80.163.163:3306/thisisjava",
                    "java",
                    "mysql"
            );

            System.out.println("연결 성공");

            String sql = new StringBuilder()
                    .append("INSERT INTO boards (btitle, bcontent, bwriter, bdate, bfilename, bfiledata)")
                    .append("VALUES(?, ?, ?, now(), ?, ?)")
                    .toString();

            PreparedStatement pstmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, "해 뜨는 날");
            pstmt.setString(2, "쨍한 해가 떠요");
            pstmt.setString(3, "sun");
            pstmt.setString(4, "sun.png");
            pstmt.setBlob(5, new FileInputStream("src/exam09/sun.png"));

            int rows = pstmt.executeUpdate();
            System.out.println("저장된 행 수 : " + rows);

            if(rows == 1) {
                ResultSet rs = pstmt.getGeneratedKeys();

                if(rs.next()) {
                    int bno = rs.getInt(1);
                    System.out.println("저장된 bno : " + bno);
                }
                rs.close();
            }

            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                    System.out.println("연결 종료");
                } catch (SQLException e) {}
            }
        }
    }
}