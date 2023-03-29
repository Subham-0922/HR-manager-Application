package daoPackage;

import java.util.List;

import dtoPackage.EmpDTO;
import exceptionPackage.SomeThingWentWrong;

public interface EmployeeDAO {
	public List<String> logInEmployee(String username,String password) throws SomeThingWentWrong;
	public void updateEmployeeDetails(EmpDTO d,int id) throws SomeThingWentWrong;
	public void changePassword(String user,String opass,String npass,int id) throws SomeThingWentWrong;
}
