package co.crisi.translator.application;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import co.crisi.translator.controller.MainPaneController;
import co.crisi.translator.exceptions.NullWordException;
import co.crisi.translator.exceptions.RepeatedWordException;
import co.crisi.translator.model.Translator;
import co.crisi.translator.persistence.Persistence;
import co.crisi.translator.view.Finder;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application implements ITranslatorControl {
	private static Translator translator;
	final EventHandler<WindowEvent> closer = new EventHandler<WindowEvent>() {

		@Override
		public void handle(WindowEvent event) {
			persist();
		}
	};

	@Override
	public void start(Stage primaryStage) {
		loadData();
		init(primaryStage);
	}

	public void init(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(Finder.class.getResource("MainPane.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			MainPaneController controller = loader.getController();
			controller.setMain(this);
			controller.setPrimaryStage(primaryStage);
			controller.menuPaneController.initCounters();
			primaryStage.setScene(scene);
			primaryStage.setOnCloseRequest(closer);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void persist() {
		try {
			Persistence.serializeObject(translator);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadData() {
		File file = new File("resources\\Translator.dat");
		if (file.exists()) {
			try {
				setTranslator((Translator) Persistence.deserializeObject());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			setTranslator(new Translator());
		}

	}

	public Translator getTranslator() {
		return translator;
	}

	public void setTranslator(Translator translator) {
		Main.translator = translator;
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public HashMap<String, String> getSpanish_english() {
		return translator.getSpanish_english();
	}

	@Override
	public HashMap<String, String> getSpanish_french() {
		return translator.getSpanish_french();
	}

	@Override
	public HashMap<String, String> getSpanish_italian() {
		return translator.getSpanish_italian();
	}

	@Override
	public void addSpanishEnglishWord(String spanishWord, String englishTranslate) throws RepeatedWordException {
		translator.addSpanishEnglishWord(spanishWord, englishTranslate);
	}

	@Override
	public void addSpanishFrenchWord(String spanishWord, String frenchTranslate) throws RepeatedWordException {
		translator.addSpanishFrenchWord(spanishWord, frenchTranslate);
	}

	@Override
	public void addSpanishItalianWord(String spanishWord, String italianTranslate) throws RepeatedWordException {
		translator.addSpanishItalianWord(spanishWord, italianTranslate);
	}

	@Override
	public String getTranslate(String word) throws NullWordException {
		return translator.getTranslate(word);
	}
}
