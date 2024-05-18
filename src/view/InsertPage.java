package view;

import controller.MenuController;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import model.Menu;

public class InsertPage {
	private Stage stage;
	private Scene scene;
	private BorderPane borderPane;
	private GridPane gridPane;
	private Label idLabel, nameLabel, priceLabel, stockLabel;
	private TextField id, name, price, stock;
	private Button insertButton;
	protected Button backButton;
	
	public InsertPage(Stage stage) {
		this.stage = stage;
		init();
		setLayout();
		setEventHandler();
		scene = new Scene(borderPane, 600, 600);
	}
	
	private void init() {
		borderPane = new BorderPane();
		gridPane = new GridPane();
		idLabel = new Label("ID");
		nameLabel = new Label("Name");
		priceLabel = new Label("Price");
		stockLabel = new Label("Stock");
		id = new TextField();
		name = new TextField();
		price = new TextField();
		stock = new TextField();
		insertButton = new Button("Insert Menu");
		backButton = new Button("Back to Home");
	}
	
	private void setLayout() {
		gridPane.add(idLabel, 0, 0);
		gridPane.add(id, 1, 0);
		gridPane.add(nameLabel, 0, 1);
		gridPane.add(name, 1, 1);
		gridPane.add(priceLabel, 0, 2);
		gridPane.add(price, 1, 2);
		gridPane.add(stockLabel, 0, 3);
		gridPane.add(stock, 1, 3);
		gridPane.add(insertButton, 0, 4);
		gridPane.add(backButton, 0, 5);
		borderPane.setCenter(gridPane);
	}
	
	private void setEventHandler() {
		insertButton.setOnAction(event -> {
			String menuID = id.getText();
			String menuName = name.getText();
			int menuPrice, menuStock;
			try {
				menuPrice = Integer.parseInt(price.getText());
			}catch(NumberFormatException e) {
				System.out.println("Invalid price format");
				return;
			}
			try {
				menuStock = Integer.parseInt(stock.getText());
			}catch(NumberFormatException e) {
				System.out.println("Invalid stock format");
				return;
			}
			if(menuID.isEmpty() || menuName.isEmpty()) {
				System.out.println("Please fill out all the fields");
				return;
			}
			Menu menu = new Menu(menuID, menuName, menuPrice, menuStock);
			boolean inserted = MenuController.insertMenu(menu);
			if(inserted) {
				System.out.println("Successfully added menu");
				HomePage homePage = new HomePage(stage);
				stage.setScene(homePage.getScene());
				stage.show();
			}
		});
		backButton.setOnAction(event -> {
			HomePage homePage = new HomePage(stage);
			stage.setScene(homePage.getScene());
			stage.show();
		});
	}
	
	public Scene getScene() {
		return this.scene;
	}
}
