package view.listusers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.psg.entities.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.UsuarioService;

public class ListUsersController implements Initializable {

	private UsuarioService service;
	private ObservableList<Usuario> obsList;

	public void setUsuarioService(UsuarioService service) {
		this.service = service;
	}
	
	@FXML
	private Button btNew;

	@FXML
	private TableView<Usuario> tableViewUsuarios;

	@FXML
	private TableColumn<Usuario, Long> tableColumnId;

	@FXML
	private TableColumn<Usuario, String> tableColumnName;

	@FXML
	private TableColumn<Usuario, String> tableColumnEmail;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

		//Stage stage = (Stage) Main.getMainScene().getWindow();
		//tableViewUsuarios.prefHeightProperty().bind(stage.heightProperty());
	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Usuario> list = service.getAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewUsuarios.setItems(obsList);
	}

}
