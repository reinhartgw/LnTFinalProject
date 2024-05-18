package view;

import java.util.List;

import controller.MenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Menu;

public class ViewPage {
	protected Stage stage;
	protected Scene scene;
	protected BorderPane borderPane;
	protected GridPane gridPane;
	protected Label viewLabel;
	protected Button backButton;
	protected TableView<Menu> menuTable;
	protected ObservableList<Menu> listMenus;
	
	public ViewPage(Stage stage) {
		this.stage = stage;
		initialize();
		setLayout();
		this.scene = new Scene(borderPane, 600, 600);
	}

	@SuppressWarnings("unchecked")
	private void initialize() {
		List<Menu> menus = MenuController.getAllMenu();
		listMenus = FXCollections.observableArrayList(menus);
		
		borderPane = new BorderPane();
		gridPane = new GridPane();
		viewLabel = new Label("View All Menus");
		backButton = new Button("Back to Home");
		TableColumn<Menu, String> idCol = new TableColumn<>("ID");
		idCol.setCellValueFactory(cell -> cell.getValue().idProperty());
		TableColumn<Menu, String> nameCol = new TableColumn<>("Name");
		nameCol.setCellValueFactory(cell -> cell.getValue().nameProperty());
		TableColumn<Menu, String> priceCol = new TableColumn<>("Price");
		priceCol.setCellValueFactory(cell -> cell.getValue().priceProperty());
		TableColumn<Menu, String> stockCol = new TableColumn<>("Stock");
		stockCol.setCellValueFactory(cell -> cell.getValue().stockProperty());
		
		menuTable = new TableView<>();
		menuTable.setItems(listMenus);
		menuTable.getColumns().addAll(idCol, nameCol, priceCol, stockCol);
		
		backButton.setOnAction(event -> {
			HomePage homePage = new HomePage(stage);
			stage.setScene(homePage.getScene());
			stage.show();
		});
	}
	
	
	public void setLayout() {
		gridPane.add(viewLabel, 0, 0);
		gridPane.add(menuTable, 0, 1);
		gridPane.add(backButton, 0, 2);
		borderPane.setCenter(gridPane);
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
}
