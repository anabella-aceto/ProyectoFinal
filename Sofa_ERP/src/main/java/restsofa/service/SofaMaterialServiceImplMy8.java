package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.SofaMaterial;
import restsofa.repository.SofaMaterialRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Implementación de la interfaz SofaMaterialService.
 */
@Service
public class SofaMaterialServiceImplMy8 implements SofaMaterialService {

	@Autowired
	private SofaMaterialRepository sofaMaterialRepository;

	/**
	 * Método que lista todos los registros de SofaMaterial.
	 *
	 * @return Una lista de todos los registros de SofaMaterial.
	 */
	@Override
	public List<SofaMaterial> todos() {
		return sofaMaterialRepository.findAll();
	}

	/**
	 * Métood que busca un registro de SofaMaterial por su identificador.
	 *
	 * @param idSofaMaterial El identificador único del registro de SofaMaterial a
	 *                       buscar.
	 * @return El registro de SofaMaterial encontrado, o null si no se encuentra.
	 */
	@Override
	public SofaMaterial buscarUno(int idSofaMaterial) {
		return sofaMaterialRepository.findById(idSofaMaterial).orElse(null);
	}

	/**
	 * Métood que inserta un nuevo registro de SofaMaterial.
	 *
	 * @param sofaMaterial El registro de SofaMaterial a insertar.
	 * @return El registro de SofaMaterial insertado.
	 */
	@Override
	public SofaMaterial insertOne(SofaMaterial sofaMaterial) {
		return sofaMaterialRepository.save(sofaMaterial);
	}

	/**
	 * Método que actualiza un registro existente de SofaMaterial.
	 *
	 * @param sofaMaterial El registro de SofaMaterial a actualizar.
	 * @return El registro de SofaMaterial actualizado, o null si hay un error.
	 */
	@Override
	public SofaMaterial updateOne(SofaMaterial sofaMaterial) {
		try {
			if (buscarUno(sofaMaterial.getIdSofaMateriales()) != null) {
				sofaMaterialRepository.save(sofaMaterial);
				return sofaMaterial;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Método que elimina un registro de SofaMaterial.
	 *
	 * @param sofaMaterial El registro de SofaMaterial a eliminar.
	 * @return true si se eliminó correctamente, false si no se pudo eliminar.
	 */
	@Override
	public boolean deleteOne(SofaMaterial sofaMaterial) {
		if (buscarUno(sofaMaterial.getIdSofaMateriales()) != null) {
			sofaMaterialRepository.delete(sofaMaterial);
			return true;
		}
		return false;
	}

	/**
	 * Método que busca registros de SofaMaterial por el identificador de sofá.
	 *
	 * @param idSofa El identificador único del sofá para buscar registros de
	 *               SofaMaterial.
	 * @return Una lista de registros de SofaMaterial encontrados para el ID de sofá
	 *         dado, o null si no se encuentra ninguno.
	 */
	@Override
	public List<SofaMaterial> buscarPorSofa(int idSofa) {
		List<SofaMaterial> sofaMaterial = sofaMaterialRepository.buscarPorIdSofa(idSofa);
		if (sofaMaterial != null) {
			return sofaMaterial;
		} else {
			return null;
		}
	}

	/**
	 * Método que busca un registro de SofaMaterial por identificador de sofá y
	 * identificador de material.
	 *
	 * @param idSofa     El identificador único del sofá.
	 * @param idMaterial El identificador único del material.
	 * @return El registro de SofaMaterial encontrado, o null si no se encuentra.
	 */
	@Override
	public SofaMaterial buscarPorSofaAndmaterial(int idSofa, int idMaterial) {
		return sofaMaterialRepository.buscarPorSofaYMaterial(idSofa, idMaterial);
	}
}
