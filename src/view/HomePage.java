package view;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HomePage {
	protected Stage stage;
	protected Scene scene;
	protected BorderPane borderPane;
	protected GridPane gridPane;
	protected Label welcomeLabel;
	
	
	protected Button insertButton;
	protected Button viewButton;
	protected Button updateButton;
	protected Button deleteButton;

	public HomePage(Stage stage) {
		this.stage = stage;
		initialize();
		setLayout();
		this.scene = new Scene(borderPane, 600, 600);
	}

	private void initialize() {
		
		
		borderPane = new BorderPane();
		gridPane = new GridPane();
		welcomeLabel = new Label("Welcome");
		
		
		insertButton = new Button("Insert new Menu");
		viewButton = new Button("View all Menus");
		updateButton = new Button("Update Menu");
		deleteButton = new Button("Delete Menu");
		
		insertButton.setOnAction(event -> {
			InsertPage insertPage = new InsertPage(stage);
			stage.setScene(insertPage.getScene());
			stage.show();
		});
		
		viewButton.setOnAction(event -> {
			ViewPage viewPage = new ViewPage(stage);
			stage.setScene(viewPage.getScene());
			stage.show();
		});
		
		updateButton.setOnAction(event -> {
			UpdatePage updatePage = new UpdatePage(stage);
			stage.setScene(updatePage.getScene());
			stage.show();
		});
		
		deleteButton.setOnAction(event -> {
			DeletePage deletePage = new DeletePage(stage);
			stage.setScene(deletePage.getScene());
			stage.show();
		});
	}

	
	public void setLayout() {
		gridPane.add(welcomeLabel, 0, 0);
		gridPane.add(insertButton, 0, 1);
		gridPane.add(viewButton, 0, 2);
		gridPane.add(updateButton, 1, 1);
		gridPane.add(deleteButton, 1, 2);
		borderPane.setCenter(gridPane);
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
}
