package restsofa.service;

import restsofa.modelo.entities.Proveedor;

public interface ProveedorService {
	
	Proveedor insertOne (Proveedor proveedor);
	Proveedor buscarUno(int idProveedor);

}
