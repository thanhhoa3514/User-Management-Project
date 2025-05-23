package model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

public class Attendance {
	private int attendanceId;
    private int employeeId;
    private Date date;
    private Time checkIn; // Nullable
    private Time checkOut; // Nullable
    private Double totalHours; // Nullable
    private String status; // "present", "absent", "late"
    private LocalDateTime createdAt;

    // Default constructor
    public Attendance() {
    }

    // Constructor with all fields
    public Attendance(int attendanceId, int employeeId, Date date, Time checkIn, Time checkOut,
                     Double totalHours, String status, LocalDateTime createdAt) {
        this.attendanceId = attendanceId;
        this.employeeId = employeeId;
        this.date = date;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalHours = totalHours;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Time checkIn) {
        this.checkIn = checkIn;
    }

    public Time getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Time checkOut) {
        this.checkOut = checkOut;
    }

    public Double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Double totalHours) {
        this.totalHours = totalHours;
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

}
