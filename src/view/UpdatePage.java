package view;

import java.util.List;

import controller.MenuController;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UpdatePage {
	private Stage stage;
	private Scene scene;
	private BorderPane borderPane;
	private GridPane gridPane;
	private Label idLabel, nameLabel, priceLabel, stockLabel;
	private TextField name, price, stock;
	private Button updateButton;
	private ComboBox<String> idComboBox;
	protected Button backButton;
	
	public UpdatePage(Stage stage) {
		this.stage = stage;
		init();
		setLayout();
		scene = new Scene(borderPane, 600, 600);
	}
	
	private void init() {
		borderPane = new BorderPane();
		gridPane = new GridPane();
		idLabel = new Label("ID");
		nameLabel = new Label("Name");
		priceLabel = new Label("Price");
		stockLabel = new Label("Stock");
		idComboBox = new ComboBox<>();
		name = new TextField();
		price = new TextField();
		stock = new TextField();
		updateButton = new Button("Update Menu");
		backButton = new Button("Back to Home");
		List<String> menus = MenuController.getAllMenuId();
		idComboBox.setItems(FXCollections.observableArrayList(menus));
		updateButton.setOnAction(event -> {
			String selectedId = idComboBox.getValue();
			String menuName = name.getText();
			String menuPrice = price.getText();
			String menuStock = stock.getText();
			boolean updated = MenuController.updateMenu(selectedId, menuName, Integer.parseInt(menuPrice), Integer.parseInt(menuStock));
			
			if(updated) {
				System.out.println("Menu successfully updated");
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
	
	private void setLayout() {
		gridPane.add(idLabel, 0, 0);
		gridPane.add(idComboBox, 1, 0);
		gridPane.add(nameLabel, 0, 1);
		gridPane.add(name, 1, 1);
		gridPane.add(priceLabel, 0, 2);
		gridPane.add(price,  1, 2);
		gridPane.add(stockLabel, 0, 3);
		gridPane.add(stock,  1, 3);
		gridPane.add(updateButton, 0, 4);
		gridPane.add(backButton, 0, 5);
		borderPane.setCenter(gridPane);
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
}
