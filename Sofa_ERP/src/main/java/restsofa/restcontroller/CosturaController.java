package restsofa.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restsofa.modelo.entities.Costura;
import restsofa.service.CosturaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/costura")

public class CosturaController {

	@Autowired
	private CosturaService costuraService;

	/*
	 * Método que devuelve todas las costuras
	 */

	@GetMapping({ "", "/" })
	public List<Costura> todas() {
		return costuraService.buscarTodasCostura();
	}

	/*
	 * Método que devuelve una costura
	 */

	@GetMapping("/{idCostura}")
	public Costura uno(@PathVariable int idCostura) {
		return costuraService.buscarCostura(idCostura);
	}

	/*
	 * Método que da de alta una costura
	 */

	@PostMapping("/alta")
	public Costura alta(@RequestBody Costura costura) {
		return costuraService.altaCostura(costura);
	}

	/*
	 * Método que modifica una costura
	 */

	@PutMapping("/modificar")
	public Costura modificar(@RequestBody Costura costura) {
		return costuraService.modifCostura(costura);
	}

	/*
	 * Método que borra una costura
	 */

	@DeleteMapping("/borrar/{idCostura}")
	public String borrar(@PathVariable int idCostura) {
		if (costuraService.borrarCostura(idCostura))
			return "Costura eliminada correctamente";
		else
			return "Costura no se ha podido eliminar";
	}

}
