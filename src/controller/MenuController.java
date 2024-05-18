package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;
import database.DatabaseSingleton;
import model.Menu;

public class MenuController {
	protected static DatabaseConnection db = DatabaseSingleton.getInstance();
	
	public static Boolean insertMenu(Menu menu) {
		
		String query = "INSERT INTO Menus(ID, Name, Price, Stock) VALUES(?, ?, ?)";
		
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			stmt.setString(1, menu.getId());
			stmt.setString(2, menu.getName());
			stmt.setInt(3, menu.getPrice());
			stmt.setInt(4, menu.getStock());
			int rowAffected = stmt.executeUpdate();
			System.out.println("Rows Affected from insert: " + rowAffected);
			return rowAffected > 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static List<Menu> getAllMenu(){
		String query = "SELECT * FROM Menus";
		List<Menu> menus = new ArrayList<Menu>();
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("ID");
				String name = rs.getString("Name");
				String price = rs.getString("Price");
				String stock = rs.getString("Stock");
				Menu menu = new Menu(id, name, Integer.parseInt(price), Integer.parseInt(stock));
				menus.add(menu);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return menus;
	}
	
	public static List<String> getAllMenuId(){
		String query = "Select ID From Menus";
		List<String> menusID = new ArrayList<String>();
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("ID");
				menusID.add(id);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return menusID;
	}
	
	public static Boolean updateMenu(String id, String name, int price, int stock) {
		if(menuExists(id)) {
			String query = "UPDATE Menus SET Name=?, Price =?, Stock=? WHERE ID = ?";
			try {
				PreparedStatement stmt = db.connection.prepareStatement(query);
				stmt.setString(1, name);
				stmt.setInt(2, price);
				stmt.setInt(3,  stock);
				stmt.setString(4, id);
				int rowAffected = stmt.executeUpdate();
				return rowAffected > 0;
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static Boolean deleteMenu(String id) {
		if(menuExists(id)) {
			String query = "DELETE FROM Menus WHERE Title = ?";
			try {
				PreparedStatement stmt = db.connection.prepareStatement(query);
				stmt.setString(1, id);
				int rowAffected = stmt.executeUpdate();
				return rowAffected > 0;
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static Boolean menuExists(String id) {
		String query = "SELECT COUNT(*) FROM Menus WHERE ID = ?";
		try {
			PreparedStatement stmt = db.connection.prepareStatement(query);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				int count = rs.getInt(1);
				return count>0;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
