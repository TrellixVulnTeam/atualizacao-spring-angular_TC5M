package com.sanches.alertamed.clock.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
//import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ClassPathResource;

import com.sanches.alertamed.clock.api.config.property.AlertaMedClockApiProperty;
import com.sanches.alertamed.clock.api.model.Imagem;
import com.sanches.alertamed.clock.api.repository.ImagemRepository;
//import com.sanches.alertamed.clock.api.repository.InteracaoRepository;
//import com.sanches.alertamed.clock.api.repository.MedicamentoRepository;

@SpringBootApplication
@EnableConfigurationProperties(AlertaMedClockApiProperty.class)
public class AlertamedClockApiApplication extends SpringBootServletInitializer implements CommandLineRunner {

	private static ApplicationContext APPLICATION_CONTEXT;

	@Autowired
	private ImagemRepository imagemRepository;

//	@Autowired
//	private InteracaoRepository interacaoRepository;
//
//	@Autowired
//	private MedicamentoRepository medicamentoRepository;

	public static void main(String[] args) {
		APPLICATION_CONTEXT = SpringApplication.run(AlertamedClockApiApplication.class, args);

		//System.out.println("running");
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AlertamedClockApiApplication.class);
    }

	public static <T> T getBean(Class<T> type) {
		return APPLICATION_CONTEXT.getBean(type);
	}

	@Override
	public void run(String... args) throws Exception {
		//System.out.println("before start");

//		List<Medicamento> medicamentos = this.medicamentoRepository.findAll();
//		List<Interacao> lista = new ArrayList<>();
//
//		medicamentos.forEach(m1 -> {
//			medicamentos.forEach(m2 -> {
//				if (m2.getCodigo() != m1.getCodigo()) {
//					Interacao i = this.interacaoRepository.findByMedicamentoaCodigoAndMedicamentobCodigo(m1.getCodigo(),
//							m2.getCodigo());
//					
//					if (i != null)
//						lista.add(i);
//					else
//						i = this.interacaoRepository.findByMedicamentobCodigoAndMedicamentobCodigo(m1.getCodigo(),
//								m2.getCodigo());
//				}
//			});
//		});
//
//		lista.forEach(i -> {
//			System.out.println(i.getCodigo());
//		});

//		interacoes.forEach(i -> {
//			System.out.println(i.getCodigo());
//		});

		if (this.imagemRepository.findByName("logo") == null) {
			// imagem padrao
			ClassPathResource alertaImagemFile = new ClassPathResource("imagem/logo.jpg");
			byte[] arrayPic = new byte[(int) alertaImagemFile.contentLength()];
			alertaImagemFile.getInputStream().read(arrayPic);
			Imagem imagem = new Imagem(Integer.toUnsignedLong(1), "logo", "jpg", arrayPic);

			this.imagemRepository.save(imagem);
		}
	}

}
