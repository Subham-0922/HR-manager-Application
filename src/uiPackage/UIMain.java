package uiPackage;

import java.util.List;
import java.util.Scanner;

import daoPackage.EmployeeDAO;
import daoPackage.EmployeeDAOImpl;
import exceptionPackage.SomeThingWentWrong;

public class UIMain {
	public static int empLogIn(Scanner sc) {
		int id=0;
		String username="";
		String password="";
		System.out.println("Enter Your UserName");
		try {
			username=sc.next();
		}catch(Exception c){
			System.out.println("Please Enter Valid Input");
		}
		
		System.out.println("Enter Your Password");
		try {
			password=sc.next();
		}catch(Exception c){
			System.out.println("Please Enter Valid Input");
		}
		EmployeeDAO edao=new EmployeeDAOImpl();
		try {
			List<String> list=edao.logInEmployee(username, password);
			id=Integer.parseInt(list.get(1));
			UIEmployee.name=list.get(0);
			
		} catch (IndexOutOfBoundsException |SomeThingWentWrong e) {
			// TODO Auto-generated catch block
			
		}
		return id;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int choice=0;
		do {
			System.out.println("Welcome to Human Resource Management System");
			System.out.println("Select 1 for Admin Log In");
			System.out.println("Select 2 for Employee Log In");
			System.out.println("Select 0 to Close the Application");
			choice=sc.nextInt();
			switch(choice) {
			case 1:
				System.out.println("Please Enter Your Admin Username");
				String name=sc.next().toLowerCase();
				System.out.println("Please Enter Your Admin Password");
				String pass=sc.next().toLowerCase();
				if(name.equals("admin")&&pass.equals("admin")) {
					UIAdmin.adminUI(sc);
				}
				
				break;
			case 2:
				int id=0;
				try {
					id=empLogIn(sc);
				}catch(IndexOutOfBoundsException e) {
					System.out.println("Log in not Successfull");
				}
				
				if(id!=0) {
					UIEmployee.id=id;
					UIEmployee.employeeUI(sc);
					
				}else {
					System.out.println("Log in not Successfull");
				}
				break;
			case 3:
				System.out.println("Thank You For Using this System");
				break;
			case 4:
				System.out.println("Please Choose A valid Option");
				break;
			}
		}while(choice!=0);
		sc.close();
	}

}
