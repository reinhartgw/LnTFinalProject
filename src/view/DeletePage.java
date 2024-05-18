package view;

import java.util.List;

import controller.MenuController;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DeletePage {
	private Stage stage;
	private Scene scene;
	private BorderPane borderPane;
	private GridPane gridPane;
	private Label idLabel;
	private Button deleteButton;
	private ComboBox<String> idComboBox;
	protected Button backButton;
	
	public DeletePage(Stage stage) {
		this.stage = stage;
		init();
		setLayout();
		scene = new Scene(borderPane, 600, 600);
	}
	
	private void init() {
		borderPane = new BorderPane();
		gridPane = new GridPane();
		idLabel = new Label("ID");
		idComboBox = new ComboBox<>();
		deleteButton = new Button("Delete Menu");
		backButton = new Button("Back to Home");
		List<String> menus = MenuController.getAllMenuId();
		idComboBox.setItems(FXCollections.observableArrayList(menus));
		deleteButton.setOnAction(event -> {
			String selectedId = idComboBox.getValue();

			boolean updated = MenuController.deleteMenu(selectedId);			
			if(updated) {
				System.out.println("Menu successfully deleted");
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
		gridPane.add(deleteButton, 0, 2);
		gridPane.add(backButton, 0, 3);
		borderPane.setCenter(gridPane);
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
}
