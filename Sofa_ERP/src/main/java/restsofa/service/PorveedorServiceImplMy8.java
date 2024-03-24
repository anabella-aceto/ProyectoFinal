package restsofa.service;

import org.springframework.beans.factory.annotation.Autowired;

import restsofa.modelo.entities.Proveedor;
import restsofa.repository.ProveedorRepository;

public class PorveedorServiceImplMy8 implements ProveedorService{
	
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

}
