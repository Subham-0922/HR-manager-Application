package uiPackage;

import java.time.LocalDate;
import java.util.Scanner;

import daoPackage.AdminDAO;
import daoPackage.AdminDAOImpl;
import dtoPackage.DepartmentDTO;
import dtoPackage.DepartmentDTOImpl;
import dtoPackage.EmpDTO;
import dtoPackage.EmpDTOImpl;
import exceptionPackage.SomeThingWentWrong;

public class UIAdmin {
	public static void addDepartment(Scanner sc) {
		System.out.println("Enter the Department Name");
		String Dname=sc.next();
		AdminDAO adao=new AdminDAOImpl();
		DepartmentDTO dm=new DepartmentDTOImpl(Dname);
		try {
			adao.addDepartment(dm);
			System.out.println("Department Added SuccessFully \u2713");
		} catch (SomeThingWentWrong e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	public static void viewAllDepartments() {
		AdminDAO adao=new AdminDAOImpl();
		try {
			adao.viewAllDepartments().forEach(System.out::println);
		} catch (SomeThingWentWrong e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	public static void updateDepartmentName(Scanner sc) {
		System.out.println("Enter the Department Id or Department name You want to change");
		String dept=sc.next();
		System.out.println("Enter the New Name of the Department");
		String ndept=sc.next();
		AdminDAO adao=new AdminDAOImpl();
		try {
			adao.updateDepartmentName(dept, ndept);
			System.out.println("Department Name Updated SuccessFully");
		} catch (SomeThingWentWrong e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	public static void addNewEmployee(Scanner sc) {
		System.out.println("Enter New Employee Name");
		String ename=sc.next();
		System.out.println("Enter New Employee Address");
		String EAddress=sc.next();
		System.out.println("Enter New Employee Department Id Number or Department Name");
		String dept=sc.next();
		System.out.println("Enter New Employee Salary per Month");
		double salary=sc.nextDouble();
		System.out.println("Enter Your Unique UserName");
		String username=sc.next();
		EmpDTO em=new EmpDTOImpl(ename,EAddress,dept,username,salary);
		AdminDAO adao=new AdminDAOImpl();
		try {
			adao.addEmployee(em);
		} catch (SomeThingWentWrong e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	public static void changeDepartmentForEmployee(Scanner sc) {
		System.out.println("Enter the Employee Username to Change");
		String username=sc.next();
		System.out.println("Enter the new Department Name");
		String department=sc.next();
		AdminDAO adao=new AdminDAOImpl();
		try {
			adao.updateDepartmentForEmployee(department, username);
			
		} catch (SomeThingWentWrong e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	public static void fireEmployee(Scanner sc) {
		System.out.println("Enter Employee UserName to Fire");
		String username=sc.next();
		AdminDAO adao=new AdminDAOImpl();
		try {
			adao.fireEmployee(username);
			System.out.println("Employee Fired Successfully");
		} catch (SomeThingWentWrong e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	public static void adminUI(Scanner sc) {
		System.out.println("-----------Welcome Admin----------");
		int choice=0;
		do {
			System.out.println("Select 1 for Add Department");
			System.out.println("Select 2 for View All Departments");
			System.out.println("Select 3 for Update Department Name");
			System.out.println("Select 4 for Add a new Employee");
			System.out.println("Select 5 for Change Department of An Employee");
			System.out.println("Select 6 for Approve or Deny of employee leave Request");
			System.out.println("Select 7 for Fire An Employee");
			System.out.println("Select 8 for Logging Out");
			
			System.out.println("Log Out");
			choice=sc.nextInt();
			switch(choice) {
			case 1:
				addDepartment(sc);
				break;
			case 2:
				viewAllDepartments();
				break;
			case 3:
				updateDepartmentName(sc);
				break;
			case 4:
				addNewEmployee(sc);
				break;
			case 5:
				changeDepartmentForEmployee(sc);
				break;
			case 6:
				System.out.println("Look Into leaves request");
				break;
			case 7:
				fireEmployee(sc);
				break;
			case 8:
				return;
			default:
				System.out.println("Please Choose An Valid Option");
				break;
				
				
			}
		}while(choice!=8);
		
	}
}
