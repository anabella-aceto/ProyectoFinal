package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Proveedor;
import restsofa.repository.ProveedorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * 
 * @version 1.0
 * 
 * Implementación de la interfaz ProveedorService.
 */
@Service
public class ProveedorServiceImplMy8 implements ProveedorService {

	@Autowired
	private ProveedorRepository proveedorRepository;

	/**
	 * Métood que inserta un nuevo proveedor.
	 *
	 * @param proveedor El proveedor a insertar.
	 * @return El proveedor insertado.
	 */
	@Override
	public Proveedor insertOne(Proveedor proveedor) {
		try {
			proveedorRepository.save(proveedor);
			return proveedor;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Método que lista todos los proveedores.
	 *
	 * @return Una lista de todos los proveedores.
	 */
	@Override
	public List<Proveedor> mostrarTodos() {
		return proveedorRepository.findAll();
	}

	/**
	 * Métood que modifica los datos de un proveedor existente.
	 *
	 * @param proveedor El proveedor a modificar.
	 * @return El proveedor modificado, o null si hay un error.
	 */
	@Override
	public Proveedor modificarUno(Proveedor proveedor) {
		if (buscarUno(proveedor.getIdProveedor()) != null) {
			proveedorRepository.save(proveedor);
			return proveedor;
		} else {
			return null;
		}
	}

	/**
	 * Método que busca un proveedor por su identificador.
	 *
	 * @param idProveedor El identificador único del proveedor a buscar.
	 * @return El proveedor encontrado, o null si no se encuentra.
	 */
	@Override
	public Proveedor buscarUno(int idProveedor) {
		try {
			return proveedorRepository.findById(idProveedor).orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Método que elimina un proveedor por su identificador.
	 *
	 * @param idProveedor El identificador único del proveedor a eliminar.
	 * @return 1 si se eliminó correctamente, 0 si no se pudo eliminar.
	 */
	@Override
	public int eliminarUno(int idProveedor) {
		if (buscarUno(idProveedor) != null) {
			proveedorRepository.deleteById(idProveedor);
			return 1;
		}
		return 0;
	}
}
