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

@WebServlet("/courier_detail")
public class CourierDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");
        PrintWriter writer = resp.getWriter();
        Connection conn;
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement("select * from tb_courier where id = ?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                writer.write(name);
                System.out.println("next");
            }
            writer.close();
            JDBCUtils.release(rs, pstmt, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
