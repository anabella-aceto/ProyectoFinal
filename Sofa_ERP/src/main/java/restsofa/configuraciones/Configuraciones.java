package restsofa.configuraciones;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @authors Alberto Saboya Ocaña, Anabella Aceto, David Rodriguez Moral
 * 
 * @version 1.0
 * 
 * Clase de configuración para los beans de la aplicación.
 */
@Configuration
public class Configuraciones {

	/**
     * Configura y devuelve una instancia de ModelMapper.
     * 
     * @return Una instancia de ModelMapper configurada.
     */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();

	}
}
