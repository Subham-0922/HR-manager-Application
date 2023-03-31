package daoPackage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dtoPackage.DepartmentDTO;
import dtoPackage.EmpDTO;
import exceptionPackage.SomeThingWentWrong;

public class AdminDAOImpl implements AdminDAO {

	@Override
	public void addDepartment(DepartmentDTO dd) throws SomeThingWentWrong {
		// TODO Auto-generated method stub
		Connection c=null;
		try {
			c=DBUtils.getConnection();
			PreparedStatement ps=c.prepareStatement("insert into department(dname) values(?)");
			ps.setString(1,dd.getDname());
			if(ps.executeUpdate()==0) {
				throw new SomeThingWentWrong("Department Not added Due to Some Error");
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

	@Override
	public List<String> viewAllDepartments() throws SomeThingWentWrong {
		List<String> list=new ArrayList<>();
		Connection c=null;
		try {
			c=DBUtils.getConnection();
			PreparedStatement ps=c.prepareStatement("select Deptid,Dname from Department where isdeleted=false");
			ResultSet rs=ps.executeQuery();
			if(DBUtils.isResultEmpty(rs)) {
				throw new SomeThingWentWrong("No Data Found");
			}
			while(rs.next()) {
				String info="Department Id: "+rs.getInt(1)+" Department Name: "+rs.getString(2);
				list.add(info);
			}
			if(list.size()==0) {
				throw new SomeThingWentWrong("No Data Found");
				
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
	public void updateDepartmentName(String dept,String ndept) throws SomeThingWentWrong {
		// TODO Auto-generated method stub
		Connection c=null;
		try {
			c=DBUtils.getConnection();
			PreparedStatement ps=null;
			boolean check=DBUtils.isNumberic(dept);
			if(check) {
				int idept=Integer.parseInt(dept);
				ps=c.prepareStatement("update department set dname=? where deptid=?");
				ps.setInt(2, idept);
				ps.setString(1, ndept);
			}else {
				ps=c.prepareStatement("update department set dname=? where dname=?");
				ps.setString(2, dept);
				ps.setString(1, ndept);
			}
			if(ps.executeUpdate()==0) {
				throw new SomeThingWentWrong("Departmentnot Updated Due to Some Error");
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

	@Override
	public void addEmployee(EmpDTO emp) throws SomeThingWentWrong {
		// TODO Auto-generated method stub
		Connection c=null;
		try {
			int deptid=0;
			String dname="";
			c=DBUtils.getConnection();
			PreparedStatement ps=null;
			if(DBUtils.isNumberic(emp.getDname())) {
				ps=c.prepareStatement("select dname from department where deptid=?");
				deptid=Integer.parseInt(emp.getDname());
				ps.setInt(1, deptid);
				ResultSet rs=ps.executeQuery();
				if(DBUtils.isResultEmpty(rs)) {
					throw new SomeThingWentWrong("Department Not found");
				}
				
				while(rs.next()) {
					dname=rs.getString(1);
				}
			}else {
				ps=c.prepareStatement("select Deptid from department where dname=?");
				dname=emp.getDname();
				ps.setString(1, dname);
				ResultSet rs=ps.executeQuery();
				if(DBUtils.isResultEmpty(rs)) {
					throw new SomeThingWentWrong("Department Not found");
				}
				
				while(rs.next()) {
					deptid=rs.getInt(1);
				}
			}
			
			PreparedStatement ps2=c.prepareStatement("insert into employee(ename,e_address,edeptid,username,date_of_Joining,salary_per_month,dname) values(?,?,?,?,?,?,?)");
			ps2.setString(1, emp.getEname());
			ps2.setString(2, emp.getE_address());
			ps2.setInt(3, deptid);
			ps2.setString(4, emp.getUsername());
			ps2.setDate(5,Date.valueOf(LocalDate.now()));
			ps2.setDouble(6, emp.getSalary_per_month());
			ps2.setString(7, dname);
			
			if(ps2.executeUpdate()==0) {
				throw new SomeThingWentWrong("Employee Not added Due to Some Error");
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

	@Override
	public void updateDepartmentForEmployee(String department, String e_username) throws SomeThingWentWrong {
		// TODO Auto-generated method stub
		Connection c=null;
		try {
			PreparedStatement ps=null;
			int deptid=0;
			c=DBUtils.getConnection();
			ps=c.prepareStatement("select Deptid from department where dname=?");
			
			ps.setString(1, department);
			ResultSet rs=ps.executeQuery();
			if(DBUtils.isResultEmpty(rs)) {
				throw new SomeThingWentWrong("Department Not found");
			}
			
			while(rs.next()) {
				deptid=rs.getInt(1);
			}
			PreparedStatement ps2=c.prepareStatement("update employee set edeptid=?,dname=? where username=?");
			ps2.setInt(1, deptid);
			ps2.setString(2, department);
			ps2.setString(3, e_username);
			if(ps2.executeUpdate()==0) {
				throw new SomeThingWentWrong("Department Change fail due to wrong Input");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new SomeThingWentWrong("Something went Wrong");
		}finally {
			try {
				DBUtils.closeConnection(c);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void fireEmployee(String Username) throws SomeThingWentWrong {
		// TODO Auto-generated method stub
		Connection c=null;
		try {
			c=DBUtils.getConnection();
			PreparedStatement ps=c.prepareStatement("update employee set isRemoved=true where username=?");
			ps.setString(1, Username);
			if(ps.executeUpdate()==0) {
				throw new SomeThingWentWrong("Employee Not Found");
			}
			PreparedStatement ps2=c.prepareStatement("select eid from employee where username=?");
			ps2.setString(1, Username);
			ResultSet rs=ps2.executeQuery();
			if(DBUtils.isResultEmpty(rs)) {
				throw new SomeThingWentWrong("Department Not found");
			}
			
			while(rs.next()) {
				int eid=rs.getInt(1);
				PreparedStatement ps3=c.prepareStatement("update empleave set isRemoved=true where eid=?");
				ps3.setInt(1, eid);
				if(ps3.executeUpdate()==0) {
					
					throw new SomeThingWentWrong("Employee Fired Successfully No record Found for leave taken");
				}
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

	@Override
	public List<String> getListOFPendingLeaveRequest() throws SomeThingWentWrong {
		// TODO Auto-generated method stub
		Connection c=null;
		DateTimeFormatter dt=DateTimeFormatter.ofPattern("dd/MM/YYYY");
		List<String> list=new ArrayList<>();
		try {
			c=DBUtils.getConnection();
			PreparedStatement ps=c.prepareStatement("select Ename,e.dname,days_of_leave,type,reason,Date_of_leave from empleave l join employee e on e.eid=l.eid where status='pending' && e.isRemoved=false");
			ResultSet rs=ps.executeQuery();
			if(DBUtils.isResultEmpty(rs)) {
				throw new SomeThingWentWrong("There is no pending leave request available");
			}
			while(rs.next()) {
				String info=rs.getString(1)+" from Department "+rs.getString(2)+" requested for "+rs.getInt(3)+" days of leave under "+ (rs.getInt(4)==1 ? "complementry" : rs.getInt(4)==2 ? "sick" : "extra leave")+" due to "+rs.getString(5)+" on "+dt.format(rs.getDate(6).toLocalDate());
				list.add(info);
			}
			if(list.size()==0) {
				throw new SomeThingWentWrong("There is no pending leave request available");
				
			}
			return list;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Integer> getListOfPendingLID() throws SomeThingWentWrong {
		// TODO Auto-generated method stub
		Connection c=null;
		List<Integer> list=new ArrayList<>();
		try {
			c=DBUtils.getConnection();
			PreparedStatement ps=c.prepareStatement("select lid from empleave l join employee e on e.eid=l.eid where status='pending' && e.isRemoved=false");
			ResultSet rs=ps.executeQuery();
			if(DBUtils.isResultEmpty(rs)) {
				throw new SomeThingWentWrong("There is no pending leave request available");
			}
			while(rs.next()) {
				list.add(rs.getInt(1));
			}
			if(list.size()==0) {
				throw new SomeThingWentWrong("There is no pending leave request available");
				
			}
			return list;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void acceptLeave(int lid, String remark) throws SomeThingWentWrong {
		// TODO Auto-generated method stub
		Connection c=null;
		int eid=0;
		int type=0;
		try {
			c=DBUtils.getConnection();
			PreparedStatement ps=c.prepareStatement("select eid,type from empleave where lid=?");
			ps.setInt(1, lid);
			ResultSet rs=ps.executeQuery();
			if(DBUtils.isResultEmpty(rs)) {
				throw new SomeThingWentWrong("Something went wrong");
			}
			while(rs.next()) {
				eid=rs.getInt(1);
				type=rs.getInt(2);
			}
			String val="";
			if(type==1) {
				val="available_Com_Leave";
			}else if(type==2) {
				val="available_sick_leave";
			
			}else {
				val="leave_taken";
			}
			PreparedStatement ps2=c.prepareStatement("update empleave set status='Accepted',remark=? where lid=?");
			ps2.setString(1, remark);
			ps2.setInt(2, lid);
			ps2.executeUpdate();
			if(type==1||type==2) {
				PreparedStatement ps3=c.prepareStatement("update employee set "+val+"="+val+"-1 where eid=?");
				ps3.setInt(1, eid);
				ps3.executeUpdate();
			}else {
				PreparedStatement ps3=c.prepareStatement("update employee set "+val+"="+val+"+1 where eid=?");
				ps3.setInt(1, eid);
				ps3.executeUpdate();
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new SomeThingWentWrong("Something Went Wrong");
		}
	}

	@Override
	public void rejectLeave(int lid, String remark) throws SomeThingWentWrong {
		// TODO Auto-generated method stub
		Connection c=null;
		try {
			c=DBUtils.getConnection();
			PreparedStatement ps2=c.prepareStatement("update empleave set status='Rejected',remark=? where lid=?");
			ps2.setString(1, remark);
			ps2.setInt(2, lid);
			ps2.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
