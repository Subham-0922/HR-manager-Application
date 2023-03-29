package dtoPackage;

import java.time.LocalDate;

public interface EmpDTO {
	public String getEname();
	public void setEname(String ename) ;
	public String getE_address();
	public void setE_address(String e_address) ;
	public String getUsername();
	public void setUsername(String username);
	public String getPassword();
	public void setPassword(String password);
	public LocalDate getDate();
	public void setDate(LocalDate date);
	public String getDname() ;
	public void setDname(String dname);
	public int getIsRemoved() ;
	public void setIsRemoved(int isRemoved);
	public double getSalary_per_month();
	public void setSalary_per_month(double salary_per_month);
	public int getAvailable_com_leave() ;
	public void setAvailable_com_leave(int available_com_leave);
	public int getAvailable_sick_leave();
	public void setAvailable_sick_leave(int available_sick_leave) ;
	public int getLeave_taken();
	public void setLeave_taken(int leave_taken) ;
	public String toString();
}
