package model;

import java.time.LocalDateTime;

public class Department{
	private int departmentId;
    private String name;
    private Integer managerId; // Nullable, vì manager_id có thể để trống
    private LocalDateTime createdAt;

    // Default constructor
    public Department() {
    }

    // Constructor with all fields
    public Department(int departmentId, String name, Integer managerId, LocalDateTime createdAt) {
        this.departmentId = departmentId;
        this.name = name;
        this.managerId = managerId;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
