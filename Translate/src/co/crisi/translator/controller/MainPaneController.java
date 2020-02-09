package co.crisi.translator.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.crisi.translator.application.Main;
import co.crisi.translator.view.Finder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainPaneController {

	private Main main;
	private Stage primaryStage;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private BorderPane mainPane;

	@FXML
	private ImageView imageView;

	public VBox menuPane;
	public MenuPaneController menuPaneController;

	@FXML
	void initialize() {
		assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'MainPane.fxml'.";
		assert imageView != null : "fx:id=\"imageView\" was not injected: check your FXML file 'MainPane.fxml'.";
		imageView.setImage(new Image("file:resources\\Biblioteca-setup-icon.png"));
		loadMenu();
	}

	public void loadMenu() {
		if (menuPane == null) {
			try {
				FXMLLoader loader = new FXMLLoader(Finder.class.getResource("MenuPane.fxml"));
				menuPane = (VBox) loader.load();
				menuPaneController = loader.getController();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		menuPaneController.setMainController(this);
		mainPane.setCenter(menuPane);
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void showAlert(String contentText, String title, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.initOwner(primaryStage);
		alert.setContentText(contentText);
		alert.setTitle(title);
		alert.setHeaderText("");
		alert.showAndWait();
	}

}
