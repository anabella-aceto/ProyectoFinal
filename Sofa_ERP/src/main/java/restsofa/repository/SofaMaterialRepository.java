package restsofa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restsofa.modelo.entities.SofaMaterial;

/**
 * Interfaz que define un repositorio para la entidad SofaMaterial.
 * Extiende de JpaRepository para obtener métodos de acceso a datos comunes.
 *
 * @param <SofaMaterial> El tipo de entidad gestionada por el repositorio.
 * @param <Integer>      El tipo del identificador de la entidad SofaMaterial.
 */

public interface SofaMaterialRepository extends JpaRepository<SofaMaterial, Integer> {

    /**
     * Busca una lista de SofaMaterial por el identificador del sofá.
     *
     * @param idSofa El identificador del sofá.
     * @return Una lista de SofaMaterial asociados al sofá con el ID dado.
     */
    @Query("select sm from SofaMaterial sm where sm.sofa.idSofa =?1")
    public List<SofaMaterial> buscarPorIdSofa(int idSofa);

    /**
     * Busca un SofaMaterial por el identificador del sofá y el identificador del material.
     *
     * @param idSofa    El identificador del sofá.
     * @param idMaterial El identificador del material.
     * @return El SofaMaterial encontrado que corresponde al sofá y al material con los IDs dados.
     */
    @Query("select sm from SofaMaterial sm where sm.sofa.idSofa =?1 and sm.material.idMaterial=?2")
    public SofaMaterial buscarPorSofaYMaterial(int idSofa, int idMaterial);
}