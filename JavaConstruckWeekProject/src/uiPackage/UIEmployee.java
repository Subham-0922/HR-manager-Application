package uiPackage;

import java.util.Scanner;

import daoPackage.EmployeeDAO;
import daoPackage.EmployeeDAOImpl;
import dtoPackage.EmpDTO;
import dtoPackage.EmpDTOImpl;
import exceptionPackage.SomeThingWentWrong;

public class UIEmployee {
	public static int id;
	public static String name;
	public static void updateDetails(Scanner sc,int id) {
		System.out.println("Enter New Employee Name");
		String ename=sc.next();
		System.out.println("Enter New Employee Address");
		String EAddress=sc.next();
		System.out.println("Enter Your New UserName");
		String username=sc.next();
		EmpDTO em=new EmpDTOImpl(ename,EAddress,"",username,0);
		EmployeeDAO edao=new EmployeeDAOImpl();
		try {
			edao.updateEmployeeDetails(em, id);
			System.out.println("Updated Successfully");
		} catch (SomeThingWentWrong e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void changePassword(Scanner sc,int id) {
		System.out.println("Enter Your Username");
		String username=sc.next();
		System.out.println("Enter Your Old Password");
		String opass=sc.next();
		System.out.println("Enter Your New Password");
		String npass=sc.next();
		EmployeeDAO edao=new EmployeeDAOImpl();
		try {
			edao.changePassword(username, opass, npass, id);
		} catch (SomeThingWentWrong e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());		
		}
	}
	public static String getDepartmentById(int id) {
		
	}
	public static void applyForLeave(Scanner sc,int id) {
		String dname=getDepartmentById(id);
	}
	
	public static void employeeUI(Scanner sc) {
		System.out.println("Welcome "+name);
		int choice=0;
		do {
			
			System.out.println("Select 1 to Update Details");
			System.out.println("Select 2 to Change Password");
			System.out.println("Select 3 to apply for Leave");
			System.out.println("Select 4 to Know the Status of leave applied");
			System.out.println("Select 5 to Know History of Leaves for the Employee");
			System.out.println("Select 6 to Know Total Salary of the Month");
			System.out.println("Select 7 to Know the Annual Salary");
			System.out.println("Select 8 to Delete Account");
			System.out.println("Select 9 to Log Out");
			choice=sc.nextInt();
			switch(choice) {
			case 1:
				updateDetails(sc,id);
				break;
			case 2:
				changePassword(sc,id);
				break;
			case 3:
				applyForLeave(sc,id);
				System.out.println("Apply for Leave");
				break;
			case 4:
				System.out.println("Know Status of leave");
				break;
			case 5:
				System.out.println("See the History of Leaves");
				break;
			case 6:
				System.out.println("Monthly Salary for this Month");
				break;
			case 7:
				System.out.println("See the Annual Salary");
				break;
			case 8:
				System.out.println("Delete this Account");
				
				return;
			case 9:
				
				return;
			}
		}while(choice!=0);
		sc.close();
	}
}
