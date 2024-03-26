package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Proveedor;
import restsofa.repository.ProveedorRepository;

@Service
public class ProveedorServiceImplMy8 implements ProveedorService{
	
	@Autowired
	private ProveedorRepository proveedorRepository; 

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

	@Override
	public List<Proveedor> mostrarTodos() {
		
		return  proveedorRepository.findAll();
	}

	@Override
	public Proveedor modificarUno (Proveedor proveedor) {
		
			if (buscarUno ( proveedor.getIdProveedor() ) != null) {
				proveedorRepository.save(proveedor);
				return proveedor;
			}else {
				return null;
			}
	}

	@Override
	public Proveedor buscarUno(int idProveedor) {
			
			try {
				return proveedorRepository.findById(idProveedor).orElse(null);
			
			}catch (Exception e){
				
				e.printStackTrace();				
			}
		return null;
	}

	@Override
	public int eliminarUno(int idProveedor) {
	
		if ( buscarUno(idProveedor) !=null) {
			 proveedorRepository.deleteById(idProveedor);
				return 1;
		}
		return 0;
	}
	
}










