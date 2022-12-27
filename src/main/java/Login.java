import util.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn;
        PreparedStatement pstmt;
        ResultSet rs;
        PrintWriter writer = resp.getWriter();
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String job = req.getParameter("job");
        try {
            conn = JDBCUtils.getConnection();
            if (job.equals("courier")) {
                pstmt = conn.prepareStatement("select * from tb_courier where id = ? and password = ?");
                pstmt.setString(1, id);
                pstmt.setString(2, password);
            } else {
                pstmt = conn.prepareStatement("select * from tb_admin where id = ? and password = ?");
                pstmt.setString(1, id);
                pstmt.setString(2, password);
            }
            rs = pstmt.executeQuery();
            if (rs.next()) {
                writer.write("success");
                System.out.println("success");
            } else {
                writer.write("fail");
                System.out.println("fail");
            }
            JDBCUtils.release(rs, pstmt, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
