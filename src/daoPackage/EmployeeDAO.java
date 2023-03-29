package daoPackage;

import java.util.List;

import dtoPackage.EmpDTO;
import dtoPackage.LeaveDTO;
import exceptionPackage.SomeThingWentWrong;

public interface EmployeeDAO {
	public List<String> logInEmployee(String username,String password) throws SomeThingWentWrong;
	public void updateEmployeeDetails(EmpDTO d,int id) throws SomeThingWentWrong;
	public void changePassword(String user,String opass,String npass,int id) throws SomeThingWentWrong;
	public String getDepartmentName(int id) throws SomeThingWentWrong;
	public void addLeaveRequest(LeaveDTO ld) throws SomeThingWentWrong;
	public List<String> showLeaveStatus(int id)throws SomeThingWentWrong;
	public List<String> showHistoryOfLeave(int id)throws SomeThingWentWrong;
	public double getSalaryForTheMonth(int id) throws SomeThingWentWrong;
	public double getAnnualSalary(int id) throws SomeThingWentWrong;
	public void deleteMyAccount(int id) throws SomeThingWentWrong;
	
}
