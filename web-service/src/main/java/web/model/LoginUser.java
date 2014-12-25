package web.model;

public class LoginUser {
    private String password;
    private String username;
    private String authority;
    
    public String getPassword() {
	return password;
    }
    public void setPassword(String password) {
	this.password = password;
    }
    public String getUsername() {
	return username;
    }
    public void setUsername(String username) {
	this.username = username;
    }
    public String getAuthority() {
	return authority;
    }
    public void setAuthority(String authority) {
	this.authority = authority;
    }
}
