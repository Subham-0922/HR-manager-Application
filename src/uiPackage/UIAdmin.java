package uiPackage;

import java.util.List;
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
			System.out.println("Showing Department Details");
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
			System.out.println("New Employee Added To Database");
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
			System.out.println("Details Updated For the Employee");
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
	public static void approveAndRejectLeaveRequest(Scanner sc) {
		AdminDAO adao=new AdminDAOImpl();
		try {
			List<String> list=adao.getListOFPendingLeaveRequest();
			List<Integer> lid=adao.getListOfPendingLID();
			if(list.size()==0) {
				throw new SomeThingWentWrong("No Pending Request Available");
			}
			int i=1;
			for(String st:list) {
				System.out.println(i+"-> "+st);
				i++;
			}
			
			int choice=0;
			int opt=0;
			do {
				System.out.println("Enter index of request to approve or reject Or enter 0 to exit this menu");
				choice=sc.nextInt();
				if(choice<0||choice>list.size()) {
					System.out.println("Please Select Valid Option");
				}else {
					do {
						System.out.println("Enter 1 to accept or 2 to reject");
						opt=sc.nextInt();
						if(opt!=1&&opt!=2) {
							System.out.println("Please Choose Valid Option");
						}else {
							if(opt==1) {
								System.out.println("Enter Remark to accept");
								sc.nextLine();
								String remark=sc.nextLine();
								adao.acceptLeave(lid.get(choice-1),remark);
								list.remove(choice-1);
								lid.remove(choice-1);
								System.out.println("Successfully Accepted the Leave Request");
							}else {
								System.out.println("Enter Remark to Reject");
								sc.nextLine();
								String remark=sc.nextLine();
								adao.rejectLeave(lid.get(choice-1),remark);
								list.remove(choice-1);
								lid.remove(choice-1);
								System.out.println("Successfully Rejected the Leave Request");
							}
						}
					}while(opt!=1&&opt!=2);
					
				}
				
			}while(list.size()!=0 && choice!=0);
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
			try {
				choice=sc.nextInt();
			}catch(Exception e) {
				System.out.println("Please Enter Valid Option");
			}
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
				approveAndRejectLeaveRequest(sc);
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
