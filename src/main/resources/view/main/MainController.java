package view.main;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.psg.utils.ViewUltils;
import javafx.fxml.Initializable;
import services.UsuarioService;
import view.listusers.ListUsersController;

public class MainController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void onMenuItemCadastroUsuario() {
		new ViewUltils().loadView("view/fxmltela/FXMLTela.fxml", x->{});
	}
	
	public void onMenuItemListaUsuarios() {
		new ViewUltils().loadView("view/listusers/ListUsers.fxml", (ListUsersController controller)-> {
			controller.setUsuarioService(new UsuarioService());
			controller.updateTableView();
		});
	}
	
	

}
