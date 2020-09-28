package view.listusers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.psg.dao.DaoGeneric;
import br.com.psg.entities.Usuario;
import br.com.psg.utils.ViewUltils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.UsuarioService;
import view.fxmltela.FXMLTelaController;

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

	@FXML
	private TableColumn<Usuario, Usuario> tableColumnEdit;

	@FXML
	private TableColumn<Usuario, Usuario> tableColumnDelete;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		btNew.setOnAction(event -> {
			new ViewUltils().loadView("view/fxmltela/FXMLTela.fxml", x -> {
			});
		});
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

		// Stage stage = (Stage) Main.getMainScene().getWindow();
		// tableViewUsuarios.prefHeightProperty().bind(stage.heightProperty());
	}

	private void initEditButtons() {
		tableColumnEdit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEdit.setCellFactory(param -> new TableCell<Usuario, Usuario>() {
			private final Button button = new Button("Editar");

			@Override
			protected void updateItem(Usuario obj, boolean empty) {
				super.updateItem(obj, empty);

				if (obj == null) {
					setGraphic(null);
					return;
				}

				setGraphic(button);
				button.setOnAction(event -> {
					new ViewUltils().loadView("view/fxmltela/FXMLTela.fxml", (FXMLTelaController controller) -> {
						controller.setUsuario(obj);
						controller.updateFormData();
					});
				});
			}
		});
	}

	private void initDeleteButtons() {
		tableColumnDelete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnDelete.setCellFactory(param -> new TableCell<Usuario, Usuario>() {
			private final Button button = new Button("Deletar");

			@Override
			protected void updateItem(Usuario obj, boolean empty) {
				super.updateItem(obj, empty);

				if (obj == null) {
					setGraphic(null);
					return;
				}

				setGraphic(button);
				button.setOnAction(event -> {
					new DaoGeneric<Usuario>().deleteById(obj);
					new ViewUltils().loadView("view/listusers/ListUsers.fxml", (ListUsersController controller) -> {
						controller.setUsuarioService(new UsuarioService());
						controller.updateTableView();
					});
				});
			}
		});
	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Usuario> list = service.getAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewUsuarios.setItems(obsList);
		initEditButtons();
		initDeleteButtons();
	}

}
