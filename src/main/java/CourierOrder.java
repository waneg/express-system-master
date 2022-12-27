import com.fasterxml.jackson.databind.ObjectMapper;
import dto.CourierDetail;
import dto.OrderDetail;
import dto.OrderDetailList;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/courier_order")
public class CourierOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PrintWriter writer = resp.getWriter();
        List<OrderDetail> list = new ArrayList<>();
        String id = req.getParameter("id");
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement("select * from tb_bill where courier_id = ?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();
                od.setExpressId(rs.getString("id"));
                od.setSender(rs.getString("sender"));
                od.setReceiver(rs.getString("receiver"));
                od.setOrigin(rs.getString("origin"));
                od.setDestination(rs.getString("destination"));
                od.setCourierId(rs.getString("courier_id"));
                list.add(od);
            }
            OrderDetailList odl = new OrderDetailList();
            odl.setList(list);
            String json = new ObjectMapper().writeValueAsString(odl);
            writer.write(json);
            JDBCUtils.release(rs, pstmt, conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
}
