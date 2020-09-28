package br.com.psg.utils;

import java.io.IOException;
import java.util.function.Consumer;

import br.com.psg.javafx.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

public class ViewUltils {
	
	public synchronized <T> void loadView(String absoluteName, Consumer<T> initialinzAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(absoluteName));
			AnchorPane newAnchorPane = loader.load();

			Scene mainScene = Main.getMainScene();
			AnchorPane mainAchorPane = (AnchorPane) ((ScrollPane) mainScene.getRoot()).getContent();

			Node mainMenu = mainAchorPane.getChildren().get(0);

			mainAchorPane.getChildren().clear();
			mainAchorPane.getChildren().add(mainMenu);
			mainAchorPane.getChildren().addAll(newAnchorPane.getChildren());
			
			initialinzAction.accept(loader.getController());

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
