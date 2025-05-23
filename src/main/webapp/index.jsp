<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HR Management System</title>
<link
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css"
	rel="stylesheet">
<style>
body {
	background: linear-gradient(to right, #3b82f6, #8b5cf6);
}
</style>
</head>
<body class="min-h-screen flex flex-col">
	<%-- Kiểm tra session và chuyển hướng nếu đã đăng nhập --%>
	<c:if test="${not empty sessionScope.user}">
		<c:choose>
			<c:when test="${sessionScope.user.role == 'admin'}">
				<c:redirect url="/views/dashboard/admin-dashboard.jsp" />
			</c:when>
			<c:when test="${sessionScope.user.role == 'hr'}">
				<c:redirect url="/views/dashboard/hr-dashboard.jsp" />
			</c:when>
			<c:when test="${sessionScope.user.role == 'employee'}">
				<c:redirect url="/views/dashboard/employee-dashboard.jsp" />
			</c:when>
		</c:choose>
	</c:if>

	<%-- Header --%>
	<header class="bg-white shadow">
		<div class="max-w-7xl mx-auto py-4 px-4 sm:px-6 lg:px-8">
			<h1 class="text-2xl font-bold text-gray-900">HR Management
				System</h1>
		</div>
	</header>

	<%-- Main Content --%>
	<main class="flex-grow flex items-center justify-center">
		<div class="text-center text-white">
			<h2 class="text-4xl font-extrabold mb-4">Chào mừng đến với Hệ
				thống Quản lý Nhân sự</h2>
			<p class="text-lg mb-6">Quản lý nhân viên, chấm công, lương, hợp
				đồng và nghỉ phép một cách dễ dàng và hiệu quả.</p>
			<a href="${pageContext.request.contextPath}/views/login.jsp"
				class="inline-block bg-blue-600 text-white px-6 py-3 rounded-lg hover:bg-blue-700 transition">
				Đăng nhập </a>
		</div>
	</main>

	<%-- Footer --%>
	<footer class="bg-gray-800 text-white py-4">
		<div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
			<p>&copy; 2025 HR Management System. All rights reserved.</p>
		</div>
	</footer>
	<div id="loading"
		class="hidden fixed inset-0 bg-gray-900 bg-opacity-50 flex items-center justify-center">
		<div
			class="animate-spin h-10 w-10 border-4 border-blue-500 border-t-transparent rounded-full"></div>
	</div>
	<script>
    window.onload = () => {
        document.getElementById('loading').classList.remove('hidden');
    };
</script>
</body>
</html>