package restsofa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restsofa.modelo.entities.Departamento;
import restsofa.repository.DepartamentoRepository;

@Service
public class DepartamentoServiceImplMy8 implements DepartamentoService {
	
	@Autowired
	private DepartamentoRepository deptorepo;

	@Override
	public List<Departamento> listarTodos() {
		
		return deptorepo.findAll();
	}

	@Override
	public Departamento buscarUno(int idDepartamento) {
		
		return deptorepo.findById(idDepartamento).orElse(null);
	}

	@Override
	public Departamento insertOne(Departamento departamento) {
		// TODO Auto-generated method stub
		return deptorepo.save(departamento);
	}

	@Override
	public Departamento updateOne(Departamento departamento) {
		try {
			if(buscarUno(departamento.getIdDepartamento()) != null) {
				deptorepo.save(departamento);
				return departamento;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteOne(int idDepartamento) {
		try {
			if(buscarUno(idDepartamento) != null) {
				deptorepo.deleteById(idDepartamento);
				return true;
		} 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
