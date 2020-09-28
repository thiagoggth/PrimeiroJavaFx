package services;

import java.util.List;

import br.com.psg.dao.DaoGeneric;
import br.com.psg.entities.Usuario;

public class UsuarioService {

	public List<Usuario> getAll() {
		return new DaoGeneric<Usuario>().getAll(Usuario.class);
	}

	public void createOrUpdateUser(Usuario usuario) {
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();

		if (usuario.getId() == null) {
			daoGeneric.save(usuario);
			return;
		}
		daoGeneric.updateMerge(usuario);
	}

}
