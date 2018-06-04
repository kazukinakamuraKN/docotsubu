package model;

import java.io.Serializable;

public class User implements Serializable {
	
	//field
	private String name;//ユーザー名
	private String pass;//パスワード
	
	//instanse
	public User() {
		
	}
	public User(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}
	
	//method
	public String getName() {
		return name;
	}
	public String getPass() {
		return pass;
	}
}
