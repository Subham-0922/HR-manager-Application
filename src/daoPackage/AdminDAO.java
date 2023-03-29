package daoPackage;

import java.util.List;

import dtoPackage.DepartmentDTO;
import dtoPackage.EmpDTO;
import exceptionPackage.SomeThingWentWrong;

public interface AdminDAO {
	public void addDepartment(DepartmentDTO dd) throws SomeThingWentWrong;
	public List<String> viewAllDepartments() throws SomeThingWentWrong; 
	public void updateDepartmentName(String dept,String ndept) throws SomeThingWentWrong;
	public void addEmployee(EmpDTO emp) throws SomeThingWentWrong;
	public void updateDepartmentForEmployee(String department,String e_username) throws SomeThingWentWrong;
	public void fireEmployee(String Username) throws SomeThingWentWrong;
	
}
