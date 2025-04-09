package Entity;

public class User {
	
	private int userid;
	private String username;
	private String passsword;
	private String role;
	
	
	public User(int userid, String username, String passsword, String role) {
		
		this.userid = userid;
		this.username = username;
		this.passsword = passsword;
		this.role = role;
		
		
	}


	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPasssword() {
		return passsword;
	}


	public void setPasssword(String passsword) {
		this.passsword = passsword;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", passsword=" + passsword + ", role=" + role
				+ "]";
	}
	
	
	

}
