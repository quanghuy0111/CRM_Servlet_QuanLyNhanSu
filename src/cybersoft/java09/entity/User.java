package cybersoft.java09.entity;

public class User {
	private int id;
	private String email;
	private String passWord;
	private String fullName;
	private String avatar;
	private int role_Id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getRole_Id() {
		return role_Id;
	}
	public void setRole_Id(int role_Id) {
		this.role_Id = role_Id;
	}
	public User() {
		
	}
	public User(int id, String email, String passWord, String fullName, String avatar, int role_Id) {
		super();
		this.id = id;
		this.email = email;
		this.passWord = passWord;
		this.fullName = fullName;
		this.avatar = avatar;
		this.role_Id = role_Id;
	}
	

	public User(String email, String passWord, String fullName, String avatar, int role_Id) {
		super();
		this.email = email;
		this.passWord = passWord;
		this.fullName = fullName;
		this.avatar = avatar;
		this.role_Id = role_Id;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id = "+this.id;
	}

}







