package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restsofa.modelo.entities.Carpinteria;
import restsofa.repository.CarpinteriaRepository;


@Service
public class CarpinteriaServiceImplMy8 implements CarpinteriaService{
	@Autowired
	private CarpinteriaRepository crepo;
	
	@Override
	public Carpinteria insertOne(int idPedido) {
				return null;
	}

	@Override
	public Carpinteria addEstado(int idEstado) {
		
		return null;
	}

	@Override
	public List<Carpinteria> buscarPorIdPedido(int idPedido) {
		// TODO Auto-generated method stub
		return crepo.buscarPorIdPedido(idPedido);
	}

}
