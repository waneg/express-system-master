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

@WebServlet("/insert_order")
public class InsertOrder extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Connection conn;
        PreparedStatement pstmt;
        PrintWriter writer = resp.getWriter();
        String sender = req.getParameter("sender");
        String receiver = req.getParameter("receiver");
        String origin = req.getParameter("origin");
        String destination = req.getParameter("destination");
        String courierId = req.getParameter("courierId");
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement("insert into tb_bill(sender, receiver, origin, destination, courier_id) values(?,?,?,?,?)");
            pstmt.setString(1, sender);
            pstmt.setString(2, receiver);
            pstmt.setString(3, origin);
            pstmt.setString(4, destination);
            pstmt.setString(5, courierId);
            int count = pstmt.executeUpdate();
            if (count > 0) {
                writer.write("success");
            } else {
                writer.write("fail");
            }
            JDBCUtils.release(pstmt, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
