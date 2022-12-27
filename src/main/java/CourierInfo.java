import com.fasterxml.jackson.databind.ObjectMapper;
import dto.CourierDetail;
import dto.CourierInfoList;
import util.JDBCUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/courier_info")
public class CourierInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        PrintWriter writer = resp.getWriter();
        List<CourierDetail> list = new ArrayList<>();
        try {
            conn = JDBCUtils.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from tb_courier");
            while (rs.next()) {
                CourierDetail courierDetail = new CourierDetail();
                courierDetail.setId(rs.getString("id"));
                courierDetail.setName(rs.getString("name"));
                list.add(courierDetail);
            }
            CourierInfoList courierInfoList = new CourierInfoList();
            courierInfoList.setList(list);
            String json = new ObjectMapper().writeValueAsString(courierInfoList);
            writer.write(json);
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
