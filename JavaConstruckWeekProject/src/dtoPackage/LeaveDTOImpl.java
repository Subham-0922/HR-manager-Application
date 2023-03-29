package dtoPackage;

public class LeaveDTOImpl implements LeaveDTO{
	private int eid;
	private String dname;
	private int days_of_leave;
	private String type;
	private String reason;
	private String status="Pending";
	private String remark="Pending";
	public LeaveDTOImpl(int eid, String dname, int days_of_leave, String type, String reason) {
		this.eid = eid;
		this.dname = dname;
		this.days_of_leave = days_of_leave;
		this.type = type;
		this.reason = reason;
		
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public int getDays_of_leave() {
		return days_of_leave;
	}
	public void setDays_of_leave(int days_of_leave) {
		this.days_of_leave = days_of_leave;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return " Department Name: " + dname + " No of Days for Leave: " + days_of_leave + " Type Of Leave: " + type
				+ " Reason to take Leave: " + reason + " Status Of Leave: " + status;
	}
	
}
