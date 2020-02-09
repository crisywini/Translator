package co.crisi.translator.controller;

import java.net.URL;
import java.util.ResourceBundle;

import co.crisi.translator.exceptions.NullWordException;
import co.crisi.translator.exceptions.RepeatedWordException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class MenuPaneController {
	private MainPaneController mainController;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField wordToBeTranslateField;

	@FXML
	private Label translateWordLabel;

	@FXML
	private TextField spanishWordField;

	@FXML
	private TextField translateWordField;

	@FXML
	private ComboBox<String> comboBox;
	@FXML
	private Label englishCounterLabel;

	@FXML
	private Label frenchCounterLabel;

	@FXML
	private Label italianCounterLabel;

	@FXML
	void handleAddButton(ActionEvent event) {
		if (isInputAddValid()) {
			String spanishWord = spanishWordField.getText();
			String translateWord = translateWordField.getText();
			String selectedDictionary = comboBox.getSelectionModel().getSelectedItem();
			if (selectedDictionary.contains("Italian")) {
				try {
					mainController.getMain().addSpanishItalianWord(spanishWord, translateWord);
					spanishWordField.setText("");
					translateWordField.setText("");
					comboBox.setValue(null);
					mainController.showAlert("Palabra agregada con exito al diccionario: \nEspañol-Italiano",
							"INFORMACIÓN", AlertType.INFORMATION);
				} catch (RepeatedWordException e) {
					mainController.showAlert(e.getMessage(), "ERROR", AlertType.ERROR);
				}
			} else if (selectedDictionary.contains("Ingles")) {
				try {
					mainController.getMain().addSpanishEnglishWord(spanishWord, translateWord);
					spanishWordField.setText("");
					translateWordField.setText("");
					comboBox.setValue(null);
					mainController.showAlert("Palabra agregada con exito al diccionario: \nEspañol-Ingles",
							"INFORMACIÓN", AlertType.INFORMATION);
				} catch (RepeatedWordException e) {
					mainController.showAlert(e.getMessage(), "ERROR", AlertType.ERROR);
				}
			} else {
				try {
					mainController.getMain().addSpanishFrenchWord(spanishWord, translateWord);
					spanishWordField.setText("");
					translateWordField.setText("");
					comboBox.setValue(null);
					mainController.showAlert("Palabra agregada con exito al diccionario: \nEspañol-Frances",
							"INFORMACIÓN", AlertType.INFORMATION);
				} catch (RepeatedWordException e) {
					mainController.showAlert(e.getMessage(), "ERROR", AlertType.ERROR);
				}
			}
			initCounters();
		}
	}

	@FXML
	void handleTranslateButton(ActionEvent event) {
		if (isInputTranslateValid()) {
			String wordToBeTranslate = wordToBeTranslateField.getText();
			try {
				translateWordLabel.setText(mainController.getMain().getTranslate(wordToBeTranslate));
			} catch (NullWordException e) {
				mainController.showAlert(e.getMessage(), "ERROR", AlertType.ERROR);
				wordToBeTranslateField.setText("");
			}
		}
	}

	@FXML
	void handleCleanButton(ActionEvent event) {
		translateWordLabel.setText("...");
		wordToBeTranslateField.setText("");
	}

	public boolean isInputAddValid() {
		boolean isValid = false;
		String errorMessage = "";
		if (spanishWordField.getText().isEmpty())
			errorMessage += "Debe ingresar la palabra en español\n";
		if (translateWordField.getText().isEmpty())
			errorMessage += "Debe ingresar la traducción de la palabra\n";
		if (comboBox.getSelectionModel().isEmpty())
			errorMessage += "Debe seleccionar a que diccionario agregar la palabra\n";
		if (errorMessage.length() == 0)
			isValid = true;
		else
			mainController.showAlert(errorMessage, "ADVERTENCIA", AlertType.WARNING);
		return isValid;
	}

	public boolean isInputTranslateValid() {
		boolean isValid = false;
		String errorMessage = "";
		if (wordToBeTranslateField.getText().isEmpty())
			errorMessage += "Debe ingresar la palabra a ser traducida";
		if (errorMessage.length() == 0)
			isValid = true;
		else
			mainController.showAlert(errorMessage, "ADVERTENCIA", AlertType.WARNING);
		return isValid;
	}

	@FXML
	void initialize() {
		assert wordToBeTranslateField != null : "fx:id=\"wordToBeTranslateField\" was not injected: check your FXML file 'MenuPane.fxml'.";
		assert translateWordLabel != null : "fx:id=\"translateWordLabel\" was not injected: check your FXML file 'MenuPane.fxml'.";
		assert spanishWordField != null : "fx:id=\"spanishWordField\" was not injected: check your FXML file 'MenuPane.fxml'.";
		assert translateWordField != null : "fx:id=\"translateWordField\" was not injected: check your FXML file 'MenuPane.fxml'.";
		assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'MenuPane.fxml'.";
		init();
	}

	public void init() {
		ObservableList<String> translatorsType = FXCollections.observableArrayList("Español-Ingles", "Español-Frances",
				"Español-Italiano");
		comboBox.setItems(translatorsType);
		translateWordLabel.setText("...");
	}

	public void setMainController(MainPaneController mainController) {
		this.mainController = mainController;
	}

	public void initCounters() {
		englishCounterLabel.setText(mainController.getMain().getSpanish_english().size() + "");
		frenchCounterLabel.setText(mainController.getMain().getSpanish_french().size() + "");
		italianCounterLabel.setText(mainController.getMain().getSpanish_italian().size() + "");
	}
}
