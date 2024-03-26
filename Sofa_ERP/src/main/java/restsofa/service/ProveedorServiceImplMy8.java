package restsofa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Proveedor;
import restsofa.repository.ProveedorRepository;

@Service
public class ProveedorServiceImplMy8 implements ProveedorService{
	
	@Autowired
	private ProveedorRepository prepo; 

	@Override
	public Proveedor insertOne(Proveedor proveedor) {
		try {
			prepo.save(proveedor);
			return proveedor;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Proveedor buscarUno(int idProveedor) {
		
		return prepo.findById(idProveedor).orElse(null);
	}

}
