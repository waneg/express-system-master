import util.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/register")
public class Register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PrintWriter writer = resp.getWriter();
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement("select * from tb_courier where id = ?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                writer.write("fail");
                System.out.println(0);
                return;
            }
            pstmt.close();
            pstmt = conn.prepareStatement("insert into tb_courier values(?, ?, ?)");
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            pstmt.setString(3, name);
            int count = pstmt.executeUpdate();
            System.out.println(count);
            writer.write("success");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(pstmt, conn);
        }
    }
}
