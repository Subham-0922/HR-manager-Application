package dtoPackage;

public interface LeaveDTO {
	public int getEid() ;
	public void setEid(int eid);
	public String getDname();
	public void setDname(String dname);
	public int getDays_of_leave() ;
	public void setDays_of_leave(int days_of_leave);
	public String getType();
	public void setType(String type);
	public String getReason();
	public void setReason(String reason);
	public String getStatus();
	public void setStatus(String status);
	public String toString();
}
