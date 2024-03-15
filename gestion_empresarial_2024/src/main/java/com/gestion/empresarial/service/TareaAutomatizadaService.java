package com.gestion.empresarial.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.gestion.empresarial.model.Usuario;
import com.gestion.empresarial.repository.UsuarioRepository;

@Component
@Service
public class TareaAutomatizadaService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private SendEmailService serviceSendEmail;
	
	
	@Scheduled(initialDelay = 2000, fixedDelay = 86400000) /*Se ejecuta cada 24 horas*/
	//@Scheduled(cron = "0 0 11 * * *", zone = "America/Bogota") /*Se ejecuta todos los dia as 11 horas da manhã horario de Sao paulo*/
	public void notificarUserTrocaSenha() throws UnsupportedEncodingException, MessagingException, InterruptedException {
		
		List<Usuario> usuarios = usuarioRepository.usuariosPasswordVencido();
		
		for (Usuario usuario : usuarios) {
			
			
			StringBuilder msg = new StringBuilder();
			msg.append("Olá, ").append(usuario.getLogin()).append("<br/>");
			msg.append("Su contraseña esta vencida, tiene mas de 90 dias de valides.").append("<br/>");
			msg.append("Por favor cambie su contraseña de la Tienda Virtual - Gestion Empresarial");
			
			serviceSendEmail.enviarEmailHtml("Cambiar de contraseña", msg.toString(), usuario.getLogin());
			
			Thread.sleep(3000);
			
		}
	}
}

















