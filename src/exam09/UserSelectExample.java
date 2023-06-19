package exam09;

import java.sql.*;

public class UserSelectExample {
    public static void main(String[] args) {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://10.80.163.237:3306/thisisjava",
                    "java",
                    "mysql"
            );

            System.out.println("연결 성공");

            String sql = "select userId, userName, userPassword, userAge, userEmail " +
                    "from users " +
                    "where userid=?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "fall");

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                User user = new User();

                user.setUserId(rs.getString(1));
                user.setUserName(rs.getString(2));
                user.setUserPassword(rs.getString(3));
                user.setUserAge(rs.getInt(4));
                user.setUserEmail(rs.getString(5));
                System.out.println(user);


            } else {
                System.out.println("사용자 아이디가 존재하지 않음");
            }

            rs.close();
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