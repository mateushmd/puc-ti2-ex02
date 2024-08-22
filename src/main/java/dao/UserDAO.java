package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDAO extends DAO 
{
	public UserDAO()
	{
		super();
		connect();
	}
	
	public void finalize()
	{
		close();
	}
	
	public boolean insert(User user)
	{
		boolean status = false;
		try
		{
			Statement st = connection.createStatement();
			String sql = "INSERT INTO users (login, password, sex) "
							+ "VALUES('" + user.getLogin() + "', '"
							+ toMD5(user.getPassword()) + "', '" + user.getSex() + "');";
			System.out.println("\u001B[33m" + sql + "\u001B[0m");
			st.executeUpdate(sql);
			st.close();
			status = true;
		}
		catch (Exception ex) 
		{
			throw new RuntimeException(ex);
		}
		
		return status;
	}
	
	public User get(int id)
	{
		User user = null;
		
		try
		{
			Statement st = connection.createStatement();
			String sql = "SELECT * FROM users WHERE id = " + id;
			System.out.println("\u001B[33m" + sql + "\u001B[0m");
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next())
			{
				user = new User(rs.getInt("id"), rs.getString("login"), rs.getString("password"), rs.getString("sex").charAt(0));
			}
			
			st.close();
		}
		catch(SQLException ex)
		{
			System.err.println(ex.getMessage());
		}
		
		return user;
	}
	
	public List<User> get() 
	{
		return get("");
	}
	
	public List<User> getOrderById()
	{
		return get("id");
	}
	
	public List<User> getOrderByLogin()
	{
		return get("login");
	}
	
	public List<User> getOrderBySex()
	{
		return get("sex");
	}
	
	private List<User> get(String orderBy)
	{
		List<User> users = new ArrayList<User>();
		
		try
		{
			Statement st = connection.createStatement();
			String sql = "SELECT * FROM users" + (orderBy.trim().length() == 0 ? "" : "ORDER BY " + orderBy);
			System.out.println("\u001B[33m" + sql + "\u001B[0m");
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next())
			{
				users.add(new User(rs.getInt("id"), rs.getString("login"), rs.getString("password"), rs.getString("sex").charAt(0)));
			}
			
			st.close();
		}
		catch(SQLException ex)
		{
			System.err.println(ex.getMessage());
		}
		
		return users;
	}
	
	public List<User> getMaleSex()
	{
		List<User> users = new ArrayList<User>();
		
		try
		{
			Statement st = connection.createStatement();
			String sql = "SELECT * FROM users WHERE sex LIKE M";
			System.out.println("\u001B[33m" + sql + "\u001B[0m");
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next())
			{
				users.add(new User(rs.getInt("id"), rs.getString("login"), rs.getString("password"), rs.getString("sex").charAt(0)));
			}
			
			st.close();
		}
		catch(SQLException ex)
		{
			System.err.println(ex.getMessage());
		}
		
		return users;
	}
	
	public boolean update(User user)
	{
		boolean status = false;
		
		try
		{
			Statement st = connection.createStatement();
			String sql = "UPDATE users SET login = '" + user.getLogin() + "', password = '"
							+ toMD5(user.getPassword()) + "', sex = '" + user.getSex() + "' "
							+ "WHERE id = " + user.getId();
			System.out.println("\u001B[33m" + sql + "\u001B[0m");
			status = st.executeUpdate(sql) > 0;
			st.close();
		}
		catch(Exception ex)
		{
			throw new RuntimeException(ex);
		}
		
		return status;
	}
	
	public boolean delete(int id)
	{
		boolean status = false;
		
		try 
		{
			Statement st = connection.createStatement();
			String sql = "DELETE FROM users WHERE id = " + id;
			System.out.println("\u001B[33m" + sql + "\u001B[0m");
			status = st.executeUpdate(sql) > 0;
			st.close();
		}
		catch(SQLException ex)
		{
			throw new RuntimeException(ex);
		}
		
		return status;
	}
	
	public boolean authenticate(String login, String password)
	{
		boolean ans = false;
		
		try
		{
			Statement st = connection.createStatement();
			String sql = "SELECT * FROM users WHERE login LIKE '" + login + "' "
							+ "AND password LIKE '" + password + "'";
			System.out.println("\u001B[33m" + sql + "\u001B[0m");
			ResultSet rs = st.executeQuery(sql);
			ans = rs.next();
		}
		catch(SQLException ex)
		{
			System.err.println(ex.getMessage());
		}
		
		return ans;
	}
}
