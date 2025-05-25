package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DepartmentDAO;
import model.Department;
import model.User;

/**
 * Servlet implementation class DepartmentServlet
 */
@WebServlet("/DepartmentServlet")
public class DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DepartmentDAO departmentDAO=new DepartmentDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") == null ||
	            !((User) request.getSession().getAttribute("user")).getRole().equals("admin")) {
	            response.sendRedirect(request.getContextPath() + "/views/login.jsp");
	            return;
	        }

	        String pathInfo = request.getPathInfo();
	        try {
	            if (pathInfo == null || pathInfo.equals("/")) {
	                // Hiển thị danh sách phòng ban
	                List<Department> departments = departmentDAO.getAll();
	                request.setAttribute("departments", departments);
	                request.getRequestDispatcher("/views/department/department-list.jsp").forward(request, response);
	            } else if (pathInfo.equals("/add")) {
	                // Hiển thị form thêm phòng ban
	                request.getRequestDispatcher("/views/department/department-form.jsp").forward(request, response);
	            } else if (pathInfo.equals("/edit")) {
	                // Hiển thị form sửa phòng ban
	                int departmentId = Integer.parseInt(request.getParameter("id"));
	                Department department = departmentDAO.findById(departmentId);
	                request.setAttribute("department", department);
	                request.getRequestDispatcher("/views/department/department-form.jsp").forward(request, response);
	            } else if (pathInfo.equals("/delete")) {
	                // Xóa phòng ban
	                int departmentId = Integer.parseInt(request.getParameter("id"));
	                departmentDAO.delete(departmentId);
	                response.sendRedirect(request.getContextPath() + "/departments");
	            }
	        } catch (SQLException e) {
	            request.setAttribute("error", "Lỗi cơ sở dữ liệu: " + e.getMessage());
	            request.getRequestDispatcher("/views/error.jsp").forward(request, response);
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Kiểm tra quyền truy cập
        if (request.getSession().getAttribute("user") == null ||
            !((User) request.getSession().getAttribute("user")).getRole().equals("admin")) {
            response.sendRedirect(request.getContextPath() + "/views/login.jsp");
            return;
        }

        String pathInfo = request.getPathInfo();
        try {
            Department department = new Department();
            department.setName(request.getParameter("name"));
            String managerIdStr = request.getParameter("managerId");
            if (managerIdStr != null && !managerIdStr.trim().isEmpty()) {
                department.setManagerId(Integer.parseInt(managerIdStr));
            } else {
                department.setManagerId(null);
            }

            // Kiểm tra trùng tên
            Department existing = departmentDAO.findByName(department.getName());
            if (existing != null && (pathInfo.equals("/add") || existing.getDepartmentId() != department.getDepartmentId())) {
                request.setAttribute("error", "Tên phòng ban đã tồn tại");
                request.setAttribute("department", department);
                request.getRequestDispatcher("/views/department/department-form.jsp").forward(request, response);
                return;
            }

            if (pathInfo.equals("/add")) {
                // Thêm phòng ban mới
                departmentDAO.add(department);
                response.sendRedirect(request.getContextPath() + "/departments");
            } else if (pathInfo.equals("/edit")) {
                // Cập nhật phòng ban
                department.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));
                departmentDAO.update(department);
                response.sendRedirect(request.getContextPath() + "/departments");
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Lỗi cơ sở dữ liệu: " + e.getMessage());
            request.getRequestDispatcher("/views/error.jsp").forward(request, response);
        }
	}

}
