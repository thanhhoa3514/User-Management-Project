package model;

import java.time.LocalDateTime;

public class Salary {
	private int salaryId;
    private int employeeId;
    private int month;
    private int year;
    private double basicSalary;
    private double allowance; // Nullable, default 0
    private double bonus; // Nullable, default 0
    private double deduction; // Nullable, default 0
    private double totalSalary;
    private int workingDays; // Default 0
    private LocalDateTime createdAt;

    // Default constructor
    public Salary() {
    }

   

    // Getters and Setters
    public int getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getAllowance() {
        return allowance;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public Salary(int salaryId, int employeeId, int month, int year, double basicSalary, double allowance, double bonus,
			double deduction, double totalSalary, int workingDays, LocalDateTime createdAt) {
		super();
		this.salaryId = salaryId;
		this.employeeId = employeeId;
		this.month = month;
		this.year = year;
		this.basicSalary = basicSalary;
		this.allowance = allowance;
		this.bonus = bonus;
		this.deduction = deduction;
		this.totalSalary = totalSalary;
		this.workingDays = workingDays;
		this.createdAt = createdAt;
	}

	public double getDeduction() {
        return deduction;
    }

    public void setDeduction(double deduction) {
        this.deduction = deduction;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
