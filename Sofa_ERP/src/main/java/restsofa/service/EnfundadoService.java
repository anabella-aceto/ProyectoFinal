package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Enfundado;

public interface EnfundadoService {
	
	List<Enfundado> listarTodos();
	Enfundado insertOne(Enfundado enfundado);
	Enfundado updateOne(Enfundado enfundado);
	boolean deleteOne (int idEnfundado);
	Enfundado buscarUno( int idEnfundado);
	List<Enfundado> buscarPorPedido(int idPedido); 
	List<Enfundado> buscarPorEstado(int idEstado);

}
