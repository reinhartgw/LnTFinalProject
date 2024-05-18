package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Menu {
	String id;
	String name;
	int price;
	int stock;
	
	public Menu(String id, String name, int price, int stock) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public StringProperty idProperty() {
		return new SimpleStringProperty(id);
	}
	public StringProperty nameProperty() {
		return new SimpleStringProperty(name);
	}
	public StringProperty priceProperty() {
		return new SimpleStringProperty(Integer.toString(price));
	}
	public StringProperty stockProperty() {
		return new SimpleStringProperty(Integer.toString(stock));
	}
}
