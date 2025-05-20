package mypackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class InsertChatServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		HttpSession session = req.getSession();

		if (session.getAttribute("unique_id") != null) {

			try {
				Connection conn = new DatabaseConfig().getConnection();

				String incoming_id = req.getParameter("incoming_id");
				String outgoing_id = req.getParameter("outgoing_id");
				String message = req.getParameter("message");

				if (!message.isBlank()) {

					String query2 = "INSERT INTO  `messages`  (incoming_msg_id, outgoing_msg_id, msg) VALUES (?,?,?)";

					PreparedStatement pstmt = conn.prepareStatement(query2);
					pstmt.setString(1, incoming_id);
					pstmt.setString(2, outgoing_id);
					pstmt.setString(3, message);

					pstmt.executeUpdate();

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			req.getRequestDispatcher("user-login").forward(req, resp);
		}

	}

}
