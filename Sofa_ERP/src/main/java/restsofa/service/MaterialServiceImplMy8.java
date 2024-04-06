package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Material;
import restsofa.modelo.entities.Pedido;
import restsofa.modelo.entities.SofaMaterial;
import restsofa.repository.MaterialRepository;

/**
 * Implementación de la Interface Service para la entidad Material utilizando Spring Data JPA.
 */

@Service
public class MaterialServiceImplMy8 implements MaterialService{
	
	@Autowired	
	private MaterialRepository mrepo;	
	
	@Autowired
	private SofaMaterialService sofaMaterialService;
	
	@Autowired
	private PedidoService pedidoService;
	

	
	/**
     * Método que permite crear un material
     * @param material (el material a crear).
     * @return El material creado. En caso contrario, null.
     */
	@Override
	public Material insertOne(Material material) {
		try {
			mrepo.save(material);
			return material;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
     * Método que permite modificar un material
     * @param material (el material a modificar).
     * @return El material modificado. En caso contrario, null.
     */
	@Override
	public Material updateOne(Material material) {
		
		try {
			if( findById(material.getIdMaterial()) !=null) 
				return mrepo.save(material);
			else 
				return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}			
		
	}
	
	/**
     * Método que permite borrar un material existente.
     * @param idMaterial. El identificador del material a borrar.
     * @return true si el material se borra correctamente. En caso contrario, false.
     */

	@Override
	public boolean deleteOne(int idMaterial) {
		try {
			if(findById(idMaterial)!=null) {
				mrepo.deleteById(idMaterial);
				return true;
			}	
			else return false;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	
	/**
     * Método que permite buscar un material según su identificador.
     * @param idMaterial. El identificador único del material.
     * @return material si este existe.  Caso contrario, null.
     */	
	
	}
	@Override
	public Material findById(int idMaterial) {
		
		return mrepo.findById(idMaterial).orElse(null);
	}

	
	/**
     * Método que permite listar todos los materiales existentes.
     * @return lista de los materiales almacenados.
     */
	@Override
	public List<Material> findAll() {
		
		return mrepo.findAll();
	}

	
	/**
     * Método que permite buscar un material por su nombre.
     * @param nombre. El nombre del material.
     * @return material que se corresponde con el nombre pasado por parámetro..
     */
	@Override
	public List<Material> buscarPorNombre(String nombre) {
		
		return mrepo.findByName(nombre);
	}

	
	/**
     * Método que permite buscar un material por la referencia del proveedor.
     * @param idProveedor. El identificador único de referencia del proveedor.
     * @return material que se corresponde con el parámetro ingresado.
     */
	@Override
	public Material findByProveedor(String refMaterialProveedor) {
		
		return mrepo.buscarPorProvedor(refMaterialProveedor);
	}

	/**
     * Método que permite buscar un material por su identificador único.
     * @param idMaterial. Identificador único del material.
     * @return material que se corresponde con el identificador pasado por parámetro..
     */
	@Override
	public Material buscarUno(int idMaterial) {
		// TODO Auto-generated method stub
		return mrepo.findById(idMaterial).orElse(null);
	}

	
	/**
     * Método que permite listar los materiales de un proveedor.
     * @param idProveedor. El identificador único del proveedor.
     * @return lista de materiales que se asocia al provedor.
     */
	@Override
	public List<Material> buscarPorProveedor(int idProveedor) {
		
		return mrepo.buscarMaterialPorProveedor(idProveedor);
	}

	/**
     * Método que permite listar materiales por su categoría.
     * @param categoria. La categoría del material.
     * @return lista de materiales que pertenecen a esa categoría.
     */
	@Override
	public List<Material> buscarPorCategoria(String categoria) {
		
		return mrepo.findBycategoria(categoria);
	}

	
	@Override
	public int restaurarMateriales(int idPedido, int idMaterial, int idSofa) {
		
		Pedido pedido = pedidoService.buscarPedido(idPedido);
		
		if( pedido!=null && pedido.getEstado().getIdEstado()==4) {
			SofaMaterial sofaMaterial = sofaMaterialService.buscarUno(idSofa);
			
			int sm = sofaMaterial.getMaterial().getIdMaterial();
			
			Material material = buscarUno(idMaterial);
			
			if(material.getIdMaterial() == sm) {
				
				int cantidad = sofaMaterial.getCantidadUtilizada()+material.getCantidad();
				material.setCantidad(cantidad);				
				mrepo.save(material);
			}
				
			return 1;			
		}
			
		else	
			return 0;
	}

	
	


}
