package daoPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dtoPackage.EmpDTO;
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
			return list;
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
		return list;
	}

	@Override
	public void updateEmployeeDetails(EmpDTO d, int id) throws SomeThingWentWrong {
		// TODO Auto-generated method stub
		Connection c=null;
		try {
			c=DBUtils.getConnection();
			PreparedStatement ps=c.prepareStatement("update employee set ename=?,e_address=?,username=? where eid=? && isRemoved=false");
			ps.setString(1, d.getDname());
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
	

}
