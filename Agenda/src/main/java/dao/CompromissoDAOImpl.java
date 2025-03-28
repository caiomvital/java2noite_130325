package dao;

import java.util.List;

import controller.CompromissoCRUD;
import model.Compromisso;
import model.Contato;

public class CompromissoDAOImpl implements CompromissoDAO {

	ContatoDAOImpl impl = new ContatoDAOImpl();
	
	@Override
	public void inserir(Compromisso compromisso) {
	

	}

	@Override
	public Compromisso localizarPorId(int id) {
		return CompromissoCRUD.localizarCompromissoPorId(id);
	}

	@Override
	public List<Compromisso> listarPorContato(int contatoId) {
		Contato contato = impl.localizarPorId(contatoId);
		
		if(contato != null) {
			
			List<Compromisso> compromissos = CompromissoCRUD.listarCompromissosPorContato(contato);
			
			 if(!compromissos.isEmpty()) {
				 return compromissos;
			 } else {
				 System.out.println("Contato não tem compromissos associados");
				 return null;
			 }
					
		} else {
			System.out.println("Contato não encontrado.");
			return null;
		}
		
	}

	@Override
	public void atualizar(Compromisso compromisso) {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(Compromisso compromisso) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Compromisso> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
