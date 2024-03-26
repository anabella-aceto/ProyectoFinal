package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Proveedor;

public interface ProveedorService {
	
	Proveedor insertOne (Proveedor proveedor);

	List<Proveedor> mostrarTodos ();
	Proveedor modificarUno (Proveedor proveedor);
	Proveedor buscarUno (int idProveedor);
	int eliminarUno (int idProveedor);


}
