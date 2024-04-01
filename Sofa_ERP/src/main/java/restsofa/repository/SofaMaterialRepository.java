package restsofa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restsofa.modelo.entities.SofaMaterial;

public interface SofaMaterialRepository extends JpaRepository<SofaMaterial, Integer>{
	
	@Query("select sm from SofaMaterial sm where sm.sofa.idSofa =?1")
	public List<SofaMaterial> buscarPorIdSofa(int idSofa);
	
	@Query("select sm from SofaMaterial sm where sm.sofa.idSofa =?1 and sm.material.idMaterial=?2")
	public SofaMaterial buscarPorSofaYMaterial(int idSofa, int idMaterial);
	
	

}
