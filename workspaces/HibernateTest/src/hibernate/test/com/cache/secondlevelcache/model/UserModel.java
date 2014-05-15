package hibernate.test.com.cache.secondlevelcache.model;

import java.io.Serializable;

public class UserModel implements Serializable{
	private static final long serialVersionUID = -5121812640999313420L;
	private int uuid;
	private int userId;
	private String name;
	private int age;
	public int getUuid() {
		return uuid;
	}
	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	

}
