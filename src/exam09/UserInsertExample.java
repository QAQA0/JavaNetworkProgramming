package exam09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserInsertExample {
    public static void main(String[] args) {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://10.80.163.163:3306/thisisjava",
                    "java",
                    "mysql"
            );

            String sql = new StringBuilder()
                    .append("INSERT INTO users (userid, username, userpassword, userage, useremail)")
                    .append("VALUES(?, ?, ?, ?, ?)")
                    .toString();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "spring");
            pstmt.setString(2, "유봄");
            pstmt.setString(3, "1324");
            pstmt.setInt(4, 23);
            pstmt.setString(5, "spring@mycompany.com");

            int rows = pstmt.executeUpdate();
            System.out.println("저장된 행 수 : " + rows);

            pstmt.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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