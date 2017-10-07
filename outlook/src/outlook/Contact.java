package outlook;

public class Contact {
private String name;
private String email;
private String inumber;
private String phone;
private String department;
private String location;

public Contact(String email)
{
	this.email  = email;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getInumber() {
	return inumber;
}
public void setInumber(String inumber) {
	this.inumber = inumber;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
}
