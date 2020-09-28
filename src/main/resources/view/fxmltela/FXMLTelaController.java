package view.fxmltela;

import java.awt.Button;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.psg.entities.Usuario;
import br.com.psg.utils.ViewUltils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import services.UsuarioService;
import view.listusers.ListUsersController;

public class FXMLTelaController implements Initializable {

	private Usuario entity;

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
		Usuario usuario = new Usuario(name.getText(), email.getText(), senha.getText());

		UsuarioService usuarioService = new UsuarioService();

		if (entity != null) {
			usuario.setId(entity.getId());
		}

		usuarioService.createOrUpdateUser(usuario);
		new ViewUltils().loadView("view/listusers/ListUsers.fxml", (ListUsersController controller) -> {
			controller.setUsuarioService(new UsuarioService());
			controller.updateTableView();
		});
	}

	public void setUsuario(Usuario entity) {
		this.entity = entity;
	}

	public void updateFormData() {
		if (entity != null) {
			name.setText(entity.getNome());
			email.setText(entity.getEmail());
			senha.setText(entity.getSenha());
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
