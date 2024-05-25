package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Proveedor;

/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * 
 * @version 1.0
 * 
 * Interfaz que define los servicios relacionados con la entidad Proveedor.
 */

public interface ProveedorService {
	
	Proveedor insertOne (Proveedor proveedor);

	List<Proveedor> mostrarTodos ();
	Proveedor modificarUno (Proveedor proveedor);
	Proveedor buscarUno (int idProveedor);
	int eliminarUno (int idProveedor);


}
