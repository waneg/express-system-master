package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtils {
    //加载驱动
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/express_system";
        String user = "root";
        String password = "1234";
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    //释放资源
    public static void release(ResultSet rs, PreparedStatement pstmt, Connection conn) throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (pstmt != null) {
            pstmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public static void release(PreparedStatement pstmt, Connection conn) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}