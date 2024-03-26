package restsofa.configuraciones;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuraciones {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
