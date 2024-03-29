package restsofa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import restsofa.modelo.entities.SofaMaterial;

public interface SofaMaterialRepository extends JpaRepository<SofaMaterial, Integer>{
	
	@Query("select sm from SofaMaterial sm where sm.sofa.idSofa =?1")
	public SofaMaterial buscarPorIdSofa(int idSofa);

}
