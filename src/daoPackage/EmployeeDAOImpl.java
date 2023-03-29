package daoPackage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dtoPackage.EmpDTO;
import dtoPackage.LeaveDTO;
import exceptionPackage.SomeThingWentWrong;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public List<String> logInEmployee(String username, String password) throws SomeThingWentWrong {
		// TODO Auto-generated method stub
		Connection c=null;
		int eid=0;
		String empName="";
		List<String> list=new ArrayList<>();
		try {
			c=DBUtils.getConnection();
			PreparedStatement ps=c.prepareStatement("select Eid,ename from Employee where username=? && password=? && isRemoved=false");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(DBUtils.isResultEmpty(rs)) {
				throw new SomeThingWentWrong("No Data Found");
			}
			while(rs.next()) {
				eid=rs.getInt(1);
				empName=rs.getString(2);
			}
			list.add(empName);
			list.add(eid+"");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				
				DBUtils.closeConnection(c);
				return list;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public void updateEmployeeDetails(EmpDTO d, int id) throws SomeThingWentWrong {
		// TODO Auto-generated method stub
		Connection c=null;
		try {
			c=DBUtils.getConnection();
			PreparedStatement ps=c.prepareStatement("update employee set ename=?,e_address=?,username=? where eid=? && isRemoved=false");
			ps.setString(1, d.getEname());
			ps.setString(2, d.getE_address());
			ps.setString(3, d.getUsername());
			ps.setInt(4, id);
			if(ps.executeUpdate()==0) {
				throw new SomeThingWentWrong("Something Went Wrong,UserName Already Taken");
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				DBUtils.closeConnection(c);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void changePassword(String user, String opass, String npass, int id) throws SomeThingWentWrong {
		// TODO Auto-generated method stub
		Connection c=null;
		try {
			c=DBUtils.getConnection();
			PreparedStatement ps=c.prepareStatement("update employee set password=? where eid=? && username=? && password=?");
			ps.setString(1, npass);
			ps.setInt(2, id);
			ps.setString(3, user);
			ps.setString(4,opass);
			if(ps.executeUpdate()==0) {
				throw new SomeThingWentWrong("Something Went Wrong,Old Password is Wrong");
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				DBUtils.closeConnection(c);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getDepartmentName(int id) throws SomeThingWentWrong {
		// TODO Auto-generated method stub
		Connection c=null;
		String department="";
		try {
			c=DBUtils.getConnection();
			PreparedStatement ps=c.prepareStatement("select dname from employee where eid=?");
			
			ps.setInt(1, id);
			
			ResultSet rs=ps.executeQuery();
//			if(DBUtils.isResultEmpty(rs)) {
//				throw new SomeThingWentWrong("No Data Found");
//			}
			while(rs.next()) {
				department=rs.getString(1);
			}
			
			return department;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				DBUtils.closeConnection(c);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return department;
	}

	@Override
	public void addLeaveRequest(LeaveDTO ld) throws SomeThingWentWrong {
		// TODO Auto-generated method stub
		Connection c=null;
		try {
			c=DBUtils.getConnection();
			PreparedStatement ps2=c.prepareStatement("Select count(eid) from empleave where eid=? && status='pending'");
			ps2.setInt(1, ld.getEid());
			ResultSet rs=ps2.executeQuery();
			
			if(!DBUtils.isResultEmpty(rs)) {
				while(rs.next()) {
					if(rs.getInt(1)>=5) {
						throw new SomeThingWentWrong("Already 5 leave request are pending,Can't add more");
					}
				}
			}
			
			PreparedStatement ps=c.prepareStatement("insert into empleave(eid,dname,days_of_leave,type,reason,date_of_leave) values(?,?,?,?,?,?)");
			
			ps.setInt(1, ld.getEid());
			ps.setString(2, ld.getDname());
			ps.setInt(3,ld.getDays_of_leave() );
			if(ld.getType().equals("Complementary Leave")) {
				ps.setInt(4,1);
			}else if(ld.getType().equals("Sick Leave")) {
				ps.setInt(4,2);
			}else {
				ps.setInt(4, 3);
			}
			
			ps.setString(5,ld.getReason());
			ps.setDate(6, Date.valueOf(LocalDate.now()));
			if(ps.executeUpdate()==0) {
				throw new SomeThingWentWrong("Leave Request Failed due to some error please try after some time");
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				DBUtils.closeConnection(c);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public List<String> showLeaveStatus(int id) throws SomeThingWentWrong {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<>();
		Connection c=null;
		try {
			c=DBUtils.getConnection();
			PreparedStatement ps=c.prepareStatement("select days_of_leave,reason,status,remark,Date_of_leave from empleave where eid=? order by Date_of_leave desc limit 5");
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(DBUtils.isResultEmpty(rs)) {
				throw new SomeThingWentWrong("No Data Found");
			}
			while(rs.next()) {
				String info="No Of Days Leave Applied For "+rs.getInt(1)+"being "+rs.getString(2)+".Current Status is "+rs.getString(3)+",Remark is "+rs.getString(4)+", Applied on "+rs.getDate(5).toLocalDate();
				list.add(info);
			}
			return list;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				DBUtils.closeConnection(c);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List<String> showHistoryOfLeave(int id) throws SomeThingWentWrong {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<>();
		Connection c=null;
		try {
			c=DBUtils.getConnection();
			PreparedStatement ps=c.prepareStatement("select days_of_leave,reason,status,remark,Date_of_leave from empleave where eid=? order by Date_of_leave desc");
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(DBUtils.isResultEmpty(rs)) {
				throw new SomeThingWentWrong("No Data Found");
			}
			while(rs.next()) {
				String info="No Of Days Leave Applied For "+rs.getInt(1)+"being "+rs.getString(2)+".Current Status is "+rs.getString(3)+",Remark is "+rs.getString(4)+", Applied on "+rs.getDate(5).toLocalDate();
				list.add(info);
			}
			return list;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				DBUtils.closeConnection(c);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public double getSalaryForTheMonth(int id) throws SomeThingWentWrong {
		// TODO Auto-generated method stub
		int count=0;
		double salary=0;
		Connection c=null;
		try {
			c=DBUtils.getConnection();
			PreparedStatement ps=c.prepareStatement("select count(*) from empleave where eid=? && status='Approved' && type=3");
			
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(DBUtils.isResultEmpty(rs)) {
				throw new SomeThingWentWrong("No Data Found");
			}
			while(rs.next()) {
				count=rs.getInt(1);
			}
			PreparedStatement ps2=c.prepareStatement("select round(salary_per_Month-(salary_per_Month/30*?),2) from employee where eid=?");
			ps2.setInt(1, count);
			ps2.setInt(2, id);
			ResultSet rs2=ps2.executeQuery();
			if(DBUtils.isResultEmpty(rs2)) {
				throw new SomeThingWentWrong("No Data Found");
			}
			while(rs2.next()) {
				salary=rs2.getDouble(1);
			}
			
			return salary;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				DBUtils.closeConnection(c);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return salary;
	}

	@Override
	public double getAnnualSalary(int id) throws SomeThingWentWrong {
		// TODO Auto-generated method stub
		double salary=0;
		Connection c=null;
		try {
			c=DBUtils.getConnection();
			
			PreparedStatement ps2=c.prepareStatement("select round(salary_per_Month*12),2) from employee where eid=?");
			
			ps2.setInt(1, id);
			ResultSet rs2=ps2.executeQuery();
			if(DBUtils.isResultEmpty(rs2)) {
				throw new SomeThingWentWrong("No Data Found");
			}
			while(rs2.next()) {
				salary=rs2.getDouble(1);
			}
			
			return salary;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				DBUtils.closeConnection(c);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return salary;
		
	}

	@Override
	public void deleteMyAccount(int id) throws SomeThingWentWrong {
		// TODO Auto-generated method stub
		Connection c=null;
		try {
			c=DBUtils.getConnection();
			PreparedStatement ps=c.prepareStatement("update employee set isRemoved=true where eid=?");
			ps.setInt(1, id);
			if(ps.executeUpdate()==0) {
				System.out.println("Something Went Wrong Please try After Some Time");
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				DBUtils.closeConnection(c);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
