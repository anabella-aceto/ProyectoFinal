package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Departamento;

/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * 
 * @version 1.0
 * 
 * Interfaz que define los servicios relacionados con la entidad Departamento.
 */

public interface DepartamentoService {
	
	List<Departamento> listarTodos();
	Departamento buscarUno(int idDepartamento);
	Departamento insertOne(Departamento departamento);
	Departamento updateOne(Departamento departamento);
	boolean deleteOne(int idDepartamento);

}
