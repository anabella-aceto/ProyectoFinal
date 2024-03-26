package restsofa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restsofa.modelo.entities.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{

}
