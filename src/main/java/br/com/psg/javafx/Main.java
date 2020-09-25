package br.com.psg.javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.UsuarioService;
import view.listusers.ListUsersController;

public class Main extends Application {

	private static Scene mainScene;

	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/listusers/ListUsers.fxml"));
			
			Pane root = loader.load();
			Scene scene = new Scene(root, 1280, 720);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			ListUsersController controller = loader.getController();
			controller.setUsuarioService(new UsuarioService());
			controller.updateTableView();

		} catch (IOException e) {
			// TODO Auto-generated catch block
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
