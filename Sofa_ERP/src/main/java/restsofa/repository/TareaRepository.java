package restsofa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restsofa.modelo.entities.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Integer>{

}
