package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/users/*")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO = new UserDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Kiểm tra quyền truy cập
		if (request.getSession().getAttribute("user") == null
				|| !((User) request.getSession().getAttribute("user")).getRole().equals("admin")) {
			response.sendRedirect(request.getContextPath() + "/views/login.jsp");
			return;
		}

		String pathInfo = request.getPathInfo();
		if (pathInfo == null || pathInfo.equals("/")) {
			// Hiển thị danh sách người dùng
			List<User> users = null;
			try {
				users = userDAO.getAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("users", users);
			request.getRequestDispatcher("/views/user/user-list.jsp").forward(request, response);
		} else if (pathInfo.equals("/add")) {
			// Hiển thị form thêm người dùng
			request.getRequestDispatcher("/views/user/user-form.jsp").forward(request, response);
		} else if (pathInfo.equals("/edit")) {
			// Hiển thị form sửa người dùng
			int userId = Integer.parseInt(request.getParameter("id"));
			User user = null;
			try {
				user = userDAO.findByUsername(userDAO.getAll().stream().filter(u -> u.getUserId() == userId).findFirst()
						.orElse(null).getUsername());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("user", user);
			request.getRequestDispatcher("/views/user/user-form.jsp").forward(request, response);
		} else if (pathInfo.equals("/delete")) {
			// Xóa người dùng
			int userId = Integer.parseInt(request.getParameter("id"));
			try {
				userDAO.delete(userId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath() + "/users");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Kiểm tra quyền truy cập
		if (request.getSession().getAttribute("user") == null
				|| !((User) request.getSession().getAttribute("user")).getRole().equals("admin")) {
			response.sendRedirect(request.getContextPath() + "/views/login.jsp");
			return;
		}

		String pathInfo = request.getPathInfo();
		if (pathInfo.equals("/add")) {
			// Thêm người dùng mới
			User user = new User();
			user.setUsername(request.getParameter("username"));
			user.setPassword(request.getParameter("password")); // Nên mã hóa mật khẩu trong thực tế
			user.setRole(request.getParameter("role"));
			try {
				userDAO.add(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath() + "/users");
		} else if (pathInfo.equals("/edit")) {
			// Cập nhật người dùng
			User user = new User();
			user.setUserId(Integer.parseInt(request.getParameter("userId")));
			user.setUsername(request.getParameter("username"));
			user.setPassword(request.getParameter("password")); // Nên mã hóa mật khẩu trong thực tế
			user.setRole(request.getParameter("role"));
			try {
				userDAO.update(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath() + "/users");
		}
	}

}
