package reflect;

import java.util.List;


public class User {
	private int id;
    @NotNull("用户名不能为空")
	private String username;
	private String password;
	private String nickname;
    @NotNull("年龄不能为空")
	private int age;
	/**
	 * 有两种用户，0表示普通用户，1表示管理员
	 */
	private int type;
	private List<Address> addresses;
	private List<String> interes;
	
	public List<String> getInteres() {
		return interes;
	}
	public void setInteres(List<String> interes) {
		this.interes = interes;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", nickname=" + nickname + ", type=" + type
				+ ", addresses=" + addresses + ",age="+age+"]";
	}

    public static void main(String[] args) {
        User u = new User();
        System.out.println(u);
    }
}
