package com.gestion.empresarial.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gestion.empresarial.model.PersonaFisica;
import com.gestion.empresarial.model.PersonaJuridica;
import com.gestion.empresarial.model.Usuario;
import com.gestion.empresarial.repository.PersonaFisicaRepository;
import com.gestion.empresarial.repository.PersonaRepository;
import com.gestion.empresarial.repository.UsuarioRepository;

@Service
public class PersonaUserService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PersonaRepository personaRepository;
	
	@Autowired
	private PersonaFisicaRepository personaFisicaRepository;
	
	@Autowired
	private SendEmailService sendEmailService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public PersonaJuridica salvarPersonaJuridica(PersonaJuridica personaJuridica) {
		
		for(int i=0; i< personaJuridica.getDireccion().size(); i++) {
			//personaJuridica.getDireccion().get(i).setPersona(personaJuridica);
			personaJuridica.getDireccion().get(i).setEmpresa(personaJuridica);
		}
		
		personaJuridica = personaRepository.save(personaJuridica);

		Usuario usuarioPj = usuarioRepository.buscarPorIdEmail(personaJuridica.getId(), personaJuridica.getEmail());

		if (usuarioPj == null) {

			String constraint = usuarioRepository.consultaConstraintAcceso();

			if (constraint != null) {
				jdbcTemplate.execute("begin; alter table usuarios_acceso drop constraint " + constraint +"; commit;");
			}

			usuarioPj = new Usuario();
			usuarioPj.setFechaActualPassword(Calendar.getInstance().getTime());
			usuarioPj.setEmpresa(personaJuridica);
			usuarioPj.setPersona(personaJuridica);
			usuarioPj.setLogin(personaJuridica.getEmail());

			String contrasena = "" + Calendar.getInstance().getTimeInMillis();
			String contrasenaCripto = new BCryptPasswordEncoder().encode(contrasena);

			usuarioPj.setPassword(contrasenaCripto);

			usuarioPj = usuarioRepository.save(usuarioPj);

			usuarioRepository.insertarAccesoUser(usuarioPj.getId());
			
			StringBuilder mensajeHtml = new StringBuilder();
			
			mensajeHtml.append("<b>Informacion para ingresar a la tienda virtual</b><br/>");
			mensajeHtml.append("<br/>");
			mensajeHtml.append("<b>Login: </b>" + personaJuridica.getEmail()+"<br/>");
			mensajeHtml.append("<b>Contraseña: </b>").append(contrasena).append("<br/><br/>");
			mensajeHtml.append("Gracias!");
			
			try {
				sendEmailService.enviarEmailHtml("Acceso generado para Tienda Virtual GE", mensajeHtml.toString(), personaJuridica.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		}

		return personaJuridica;
		
	}
	
	public PersonaFisica salvarPersonaFisica(PersonaFisica personaFisica) {
		
		for(int i=0; i< personaFisica.getDireccion().size(); i++) {
			personaFisica.getDireccion().get(i).setPersona(personaFisica);
			//personaFisica.getDireccion().get(i).setPersona(null);
		}
		
		personaFisica = personaFisicaRepository.save(personaFisica);

		Usuario usuarioPj = usuarioRepository.buscarPorIdEmail(personaFisica.getId(), personaFisica.getEmail());

		if (usuarioPj == null) {

			String constraint = usuarioRepository.consultaConstraintAcceso();

			if (constraint != null) {
				jdbcTemplate.execute("begin; alter table usuarios_acceso drop constraint " + constraint +"; commit;");
			}

			usuarioPj = new Usuario();
			usuarioPj.setFechaActualPassword(Calendar.getInstance().getTime());
			usuarioPj.setEmpresa(personaFisica.getEmpresa());
			usuarioPj.setPersona(personaFisica);
			usuarioPj.setLogin(personaFisica.getEmail());

			String contrasena = "" + Calendar.getInstance().getTimeInMillis();
			String contrasenaCripto = new BCryptPasswordEncoder().encode(contrasena);

			usuarioPj.setPassword(contrasenaCripto);

			usuarioPj = usuarioRepository.save(usuarioPj);

			usuarioRepository.insertarAccesoUser(usuarioPj.getId());
			
			StringBuilder mensajeHtml = new StringBuilder();
			
			mensajeHtml.append("<b>Informacion para ingresar a la tienda virtual</b><br/>");
			mensajeHtml.append("<br/>");
			mensajeHtml.append("<b>Login: </b>" + personaFisica.getEmail()+"<br/>");
			mensajeHtml.append("<b>Contraseña: </b>").append(contrasena).append("<br/><br/>");
			mensajeHtml.append("Gracias!");
			
			try {
				sendEmailService.enviarEmailHtml("Acceso generado para Tienda Virtual GE", mensajeHtml.toString(), personaFisica.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		}

		return personaFisica;
		
	}

}
