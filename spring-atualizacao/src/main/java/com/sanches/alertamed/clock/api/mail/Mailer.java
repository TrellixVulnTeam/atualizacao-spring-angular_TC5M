package com.sanches.alertamed.clock.api.mail;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.sanches.alertamed.clock.api.model.Paciente;

@Component
public class Mailer {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TemplateEngine thymeleaf;
	
//	envio de e-mail simples - teste
//	@EventListener
//	private void teste(ApplicationReadyEvent event) {// dispara o evento quando a aplicacao estivre pronta para ser utilizada
//		this.enviarEmail("danielsanches.teste@gmail.com",
//				Arrays.asList("das.29@outlook.com"),
//				"Testando", "Olá!<br/>Teste");
//		
//		System.out.println("Terminado o envio de e-mail");
//	}
	
//	envio de e-mail simples - com template
//	@EventListener
//	private void teste(ApplicationReadyEvent event) {
//		String template = "mail/envio-de-senha";
//		
//		List<Usuario> lista = this.repo.findAll();
//		
//		Map<String, Object> variaveis = new HashMap<>();
//		variaveis.put("usuarios", lista);
//		
//		this.enviarEmail("danielsanches.teste@gmail.com",
//				Arrays.asList("das.29@outlook.com"),
//				"Testando", template, variaveis);
//		
//		System.out.println("Terminado o envio de e-mail");
//	}
	
	public void enviarAcesso(Paciente paciente, String senha) {
		String template = "Olá " + paciente.getNome() + ", para acessar o sistema é necessário informar o CPF "
				+ "e a senha abaixo:<br/></br>" + senha;
		
		this.enviarEmail("danielsanches.teste@gmail.com",
				Arrays.asList(paciente.getEmail()), "Confirmação de acesso", template);
	}

	public void enviarEmail(String remetente, List<String> destinatarios, String assunto, String template,
			Map<String, Object> variaveis) {
		Context context = new Context(new Locale("pt", "BR"));

		variaveis.entrySet().forEach(e -> context.setVariable(e.getKey(), e.getValue()));

		String mensagem = this.thymeleaf.process(template, context);

		this.enviarEmail(remetente, destinatarios, assunto, mensagem);

	}
	

	public void enviarEmail(String remetente, List<String> destinatarios, String assunto, String mensagem) {
		try {
			MimeMessage mimeMessage = this.mailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom(remetente);
			helper.setTo(destinatarios.toArray(new String[destinatarios.size()]));
			helper.setSubject(assunto);
			helper.setText(mensagem, true);

			this.mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new RuntimeException("Problemas com o envio de e-mail!", e);
		}
	}

}
