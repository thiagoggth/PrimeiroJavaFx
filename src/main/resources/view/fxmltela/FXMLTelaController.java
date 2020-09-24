package view.fxmltela;

import java.awt.Button;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.psg.dao.DaoGeneric;
import br.com.psg.entities.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class FXMLTelaController implements Initializable {

	@FXML
	private TextField name;

	@FXML
	private TextField email;

	@FXML
	private TextField senha;

	@FXML
	private Button btCadastrar;

	@FXML
	private Button btCancelar;

	public void onBtCadastrar() {
		try {
			new DaoGeneric<Usuario>().save(new Usuario(name.getText(), email.getText(), senha.getText()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void onBtCancelar() {
		System.out.println("Cancelando cadastro!");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
