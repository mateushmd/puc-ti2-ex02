package model;

public class User 
{
	private int id;
	private String login;
	private String password;
	private char sex;
	
	public User()
	{
		this.id = -1;
		this.login = "";
		this.password = "";
		this.sex = '*';
	}
	
	public User(String login, String password, char sex)
	{
		this.login = login;
		this.password = password;
		this.sex = sex;
	}
	
	public User(int id, String login, String password, char sex)
	{
		this(login, password, sex);
		this.id = id;
	}
	
	public int getId() { return id; }
	
	public String getLogin() { return login; }
	public void setLogin(String login) { this.login = login; }
	
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; } 
	
	public char getSex() { return sex; }
	public void setSex(char sex) { this.sex = sex; }
	
	@Override
	public String toString()
	{
		return "User [id = " + id + ", login = " + login + ", password = " + password + ", sex = " + sex + "]";
	}
}
