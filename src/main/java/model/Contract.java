package model;

import java.sql.Date;
import java.time.LocalDateTime;

public class Contract {
	private int contractId;
    private int employeeId;
    private String contractCode;
    private String contractType;
    private Date startDate;
    private Date endDate; // Nullable
    private double salary;
    private String status; // "active", "expired", "terminated"
    private String note; // Nullable
    private LocalDateTime createdAt;

    // Default constructor
    public Contract() {
    }

    // Constructor with all fields
    public Contract(int contractId, int employeeId, String contractCode, String contractType,
                    Date startDate, Date endDate, double salary, String status, String note,
                    LocalDateTime createdAt) {
        this.contractId = contractId;
        this.employeeId = employeeId;
        this.contractCode = contractCode;
        this.contractType = contractType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.salary = salary;
        this.status = status;
        this.note = note;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
