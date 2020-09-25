package services;

import java.util.List;

import br.com.psg.dao.DaoGeneric;
import br.com.psg.entities.Usuario;

public class UsuarioService {

	public List<Usuario> getAll() {
		return new DaoGeneric<Usuario>().getAll(Usuario.class);
	}

}
