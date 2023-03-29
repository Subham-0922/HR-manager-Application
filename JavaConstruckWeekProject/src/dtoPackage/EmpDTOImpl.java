package dtoPackage;

import java.time.LocalDate;

public class EmpDTOImpl implements EmpDTO {
	private String ename;
	private String E_address;
	
	private String dname;
	private String username;
	private String password="123456";
	private LocalDate date;
	private int isRemoved=0;
	private double Salary_per_month;
	private int available_com_leave=18;
	private int available_sick_leave=12;
	private int leave_taken=0;
	public EmpDTOImpl(String ename, String e_address,String dept,String username,
			double salary_per_month) {
		this.ename = ename;
		E_address = e_address;
		dname=dept;
		this.username = username;
		
		
		Salary_per_month = salary_per_month;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getE_address() {
		return E_address;
	}
	public void setE_address(String e_address) {
		E_address = e_address;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public int getIsRemoved() {
		return isRemoved;
	}
	public void setIsRemoved(int isRemoved) {
		this.isRemoved = isRemoved;
	}
	public double getSalary_per_month() {
		return Salary_per_month;
	}
	public void setSalary_per_month(double salary_per_month) {
		Salary_per_month = salary_per_month;
	}
	public int getAvailable_com_leave() {
		return available_com_leave;
	}
	public void setAvailable_com_leave(int available_com_leave) {
		this.available_com_leave = available_com_leave;
	}
	public int getAvailable_sick_leave() {
		return available_sick_leave;
	}
	public void setAvailable_sick_leave(int available_sick_leave) {
		this.available_sick_leave = available_sick_leave;
	}
	public int getLeave_taken() {
		return leave_taken;
	}
	public void setLeave_taken(int leave_taken) {
		this.leave_taken = leave_taken;
	}
	@Override
	public String toString() {
		return "Employee Name: " + ename + " Employee Address=" + E_address + " Department Name: " + dname + "UserName: " + username
				+ " Date Of Joining: " + date + " Salary_per_month: " + Salary_per_month + " Complementary Leave Available: "
				+ available_com_leave + " Available Sick Leave: " + available_sick_leave + " Leave Taken: "
				+ leave_taken ;
	}
	
	
	
	
	
}
