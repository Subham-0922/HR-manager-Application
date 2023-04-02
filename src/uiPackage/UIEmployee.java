package uiPackage;

import java.util.Scanner;

import daoPackage.EmployeeDAO;
import daoPackage.EmployeeDAOImpl;
import dtoPackage.EmpDTO;
import dtoPackage.EmpDTOImpl;
import dtoPackage.LeaveDTO;
import dtoPackage.LeaveDTOImpl;
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
			System.out.println("Password Changed Successfully");
		} catch (SomeThingWentWrong e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());		
		}
	}
	public static String getDepartmentById(int id) {
		EmployeeDAO edao=new EmployeeDAOImpl();
		try {
			return edao.getDepartmentName(id);
			
		} catch (SomeThingWentWrong e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return "";
	}
	public static void applyForLeave(Scanner sc,int id) {
		
		System.out.println("Enter the number of days of leave you need");
		int daysOfLeave=sc.nextInt();
		String dname=getDepartmentById(id);
		int type=0;
		String type_of_leave="";
		do {
			System.out.println("Enter 1 for Complementary Leave");
			System.out.println("Enter 2 for Sick Leave");
			System.out.println("Enter 3 for Extra Leave");
			type=sc.nextInt();
			switch(type) {
			case 1:
				type_of_leave="Complementary Leave";
				break;
			case 2:
				type_of_leave="Sick Leave";
				break;
			case 3:
				type_of_leave="Extra Leave";
				break;
			default:
				System.out.println("Please Choose A valid Option");
				break;
			}
		}while(type!=1&&type!=2&&type!=3);
		System.out.println("Enter Reason for taking for leave");
		sc.nextLine();
		String reason=sc.nextLine();
		LeaveDTO ld=new LeaveDTOImpl(id, dname, daysOfLeave, type_of_leave, reason);
		EmployeeDAO edao=new EmployeeDAOImpl();
		try {
			edao.addLeaveRequest(ld);
			System.out.println("Leave request has been added");
		} catch (SomeThingWentWrong e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	public static void knowLeaveStatus(int id) {
		EmployeeDAO edao=new EmployeeDAOImpl();
		try {
			System.out.println("Showing Leave Request Status");
			edao.showLeaveStatus(id).forEach(c->System.out.println(c));
		} catch (SomeThingWentWrong e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	public static void knowHistoryOfLeave(int id){
		EmployeeDAO edao=new EmployeeDAOImpl();
		try {
			System.out.println("Showing Leave Request History");
			edao.showHistoryOfLeave(id).forEach(c->System.out.println(c));
		} catch (SomeThingWentWrong e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	public static void knowThisMonthSalary(int id) {
		EmployeeDAO edao=new EmployeeDAOImpl();
		try {
			System.out.println("Salary For This Month is "+edao.getSalaryForTheMonth(id));
		} catch (SomeThingWentWrong e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	public static void knowAnnualSalary(int id) {
		EmployeeDAO edao=new EmployeeDAOImpl();
		try {
			System.out.println("Your Annual Salary is "+edao.getAnnualSalary(id));
		} catch (SomeThingWentWrong e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	public static boolean deleteMyAccount(int id) {
		EmployeeDAO edao=new EmployeeDAOImpl();
		try {
			edao.deleteMyAccount(id);
			System.out.println("Your Account is Deleted Back to Main Menu");
			return true;
		} catch (SomeThingWentWrong e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return false;
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
				break;
			case 4:
				knowLeaveStatus(id);
				break;
			case 5:
				knowHistoryOfLeave(id);
				break;
			case 6:
				knowThisMonthSalary(id);
				break;
			case 7:
				knowAnnualSalary(id);
				break;
			case 8:
				if(deleteMyAccount(id)) {
					return;
				}
			case 9:
				return;
			default:
				System.out.println("Please Enter Valid Choice");
			}
		}while(choice!=0);
		sc.close();
	}
}
