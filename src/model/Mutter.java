package model;

import java.io.Serializable;

public class Mutter implements Serializable{

	//field
	private String userName;//ユーザー名
	private String text;//つぶやき内容

	//instanse
	public Mutter() {

	}
	public Mutter(String userName, String text) {
		this.userName = userName;
		this.text = text;
	}

	//method
	public String getUserName() {
		return userName;
	}
	public String getText() {
		return text;
	}
}
