package model;

import java.sql.Date;
import java.time.LocalDateTime;

public class Employee {
	 private int employeeId;
	    private String employeeCode;
	    private String fullName;
	    private String gender; // "M" or "F"
	    private Date dob; // Date of birth
	    private String email;
	    private String phone;
	    private String address; // Nullable
	    private Integer departmentId; // Nullable
	    private Date hireDate;
	    private String position;
	    private Integer userId; // Nullable
	    private String status; // "active" or "inactive"
	    private LocalDateTime createdAt;
		public int getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(int employeeId) {
			this.employeeId = employeeId;
		}
		public String getEmployeeCode() {
			return employeeCode;
		}
		public void setEmployeeCode(String employeeCode) {
			this.employeeCode = employeeCode;
		}
		public String getFullName() {
			return fullName;
		}
		public void setFullName(String fullName) {
			this.fullName = fullName;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public Date getDob() {
			return dob;
		}
		public void setDob(Date dob) {
			this.dob = dob;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public Integer getDepartmentId() {
			return departmentId;
		}
		public void setDepartmentId(Integer departmentId) {
			this.departmentId = departmentId;
		}
		public Date getHireDate() {
			return hireDate;
		}
		public void setHireDate(Date hireDate) {
			this.hireDate = hireDate;
		}
		public String getPosition() {
			return position;
		}
		public void setPosition(String position) {
			this.position = position;
		}
		public Integer getUserId() {
			return userId;
		}
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public LocalDateTime getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}
		public Employee(int employeeId, String employeeCode, String fullName, String gender, Date dob, String email,
				String phone, String address, Integer departmentId, Date hireDate, String position, Integer userId,
				String status, LocalDateTime createdAt) {
			super();
			this.employeeId = employeeId;
			this.employeeCode = employeeCode;
			this.fullName = fullName;
			this.gender = gender;
			this.dob = dob;
			this.email = email;
			this.phone = phone;
			this.address = address;
			this.departmentId = departmentId;
			this.hireDate = hireDate;
			this.position = position;
			this.userId = userId;
			this.status = status;
			this.createdAt = createdAt;
		}
		public Employee() {
			super();
		}
	
}
