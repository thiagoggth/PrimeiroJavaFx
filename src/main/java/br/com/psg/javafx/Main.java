package br.com.psg.javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {

	private static Scene mainScene;

	@Override
	public void start(Stage primaryStage) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/main/Main.fxml"));

			ScrollPane scroolPane = loader.load();
			
			scroolPane.setFitToHeight(true);
			scroolPane.setFitToWidth(true);

			mainScene = new Scene(scroolPane);
			
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("System undefined");
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Scene getMainScene() {
		return mainScene;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
