package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Departamento;

public interface DepartamentoService {
	
	List<Departamento> listarTodos();
	Departamento buscarUno(int idDepartamento);
	Departamento insertOne(Departamento departamento);
	Departamento updateOne(Departamento departamento);
	boolean deleteOne(int idDepartamento);

}
