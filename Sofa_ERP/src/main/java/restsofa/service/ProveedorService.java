package restsofa.service;

import java.util.List;

import restsofa.modelo.entities.Proveedor;

public interface ProveedorService {
	
	Proveedor insertOne (Proveedor proveedor);
<<<<<<< HEAD
	List<Proveedor> mostrarTodos ();
	Proveedor modificarUno (Proveedor proveedor);
	Proveedor buscarUno (int idProveedor);
	int eliminarUno (int idProveedor);
=======
	Proveedor buscarUno(int idProveedor);
>>>>>>> 43a49da23f32ecab9e7cdcab686487398cfbda61

}
