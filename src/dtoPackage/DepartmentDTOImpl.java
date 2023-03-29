package dtoPackage;

public class DepartmentDTOImpl implements DepartmentDTO{
	private String dname;
	private int isDeleted=0;

	public DepartmentDTOImpl(String dname) {
		this.dname = dname;
	}
	@Override
	public String getDname() {
		return dname;
	}
	@Override
	public void setDname(String dname) {
		this.dname = dname;
	}

	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public String tomyString(int id) {
		return "Department Id: "+id+" Department Name: " + dname;
	}
	
}
