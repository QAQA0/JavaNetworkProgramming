package exam09;

import java.io.FileInputStream;
import java.sql.*;

public class BoardUpdateExample {
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

            String sql = new StringBuilder()
                    .append("UPDATE boards SET ")
                    .append("btitle=?, ")
                    .append("bcontent=?, ")
                    .append("bfilename=?, ")
                    .append("bfiledata=? ")
                    .append("WHERE bno=?")
                    .toString();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "snstkfka");
            pstmt.setString(2, "snsdmfh aksems tkfka");
            pstmt.setString(3, null);
            pstmt.setBlob(4, (Blob) null);
            pstmt.setInt(5, 11);

            int rows = pstmt.executeUpdate();
            System.out.println("저장된 행 수 : " + rows);

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