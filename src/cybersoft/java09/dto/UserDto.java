package cybersoft.java09.dto;

public class UserDto {
	private int id;
	private String email;
	private String passWord;
	private String fullName;
	private String avatar;
	private String roleName;
	private int notDoneWorkPercent;
	private int pendingWorkPercent;
	private int finishWorkPercent;
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
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public UserDto(int id, String email, String passWord, String fullName, String avatar, String roleName) {
		super();
		this.id = id;
		this.email = email;
		this.passWord = passWord;
		this.fullName = fullName;
		this.avatar = avatar;
		this.roleName = roleName;
	}
	public UserDto() {
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id = "+this.id+" fullname "+this.fullName;
	}
	public int getNotDoneWorkPercent() {
		return notDoneWorkPercent;
	}
	public void setNotDoneWorkPercent(int notDoneWorkPercent) {
		this.notDoneWorkPercent = notDoneWorkPercent;
	}
	public int getPendingWorkPercent() {
		return pendingWorkPercent;
	}
	public void setPendingWorkPercent(int pendingWorkPercent) {
		this.pendingWorkPercent = pendingWorkPercent;
	}
	public int getFinishWorkPercent() {
		return finishWorkPercent;
	}
	public void setFinishWorkPercent(int finishWorkPercent) {
		this.finishWorkPercent = finishWorkPercent;
	}
	
}
