package com.gestion.empresarial.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.gestion.empresarial.ApplicationContextLoad;
import com.gestion.empresarial.model.Usuario;
import com.gestion.empresarial.repository.UsuarioRepository;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;



/*Crear la autenticacion y retornar la autenticacion JWT*/
@Service
@Component
public class JWTTokenAutenticacionService {
	
	/*Token de validacion de 11 dias*/
	private static final long EXPIRATION_TIME = 959990000;
	
	/*Chave de senha para juntar com o JWT*/
	private static final String SECRET = "em/@*-!jamlsdt9610Pop-Cet/conc-s?an60p";
	
	private static final String TOKEN_PREFIX = "Bearer";
	
	private static final String HEADER_STRING = "Authorization";
	
	/*Genera o token e da a responsta para o cliente o com JWT*/
	public void addAuthentication(HttpServletResponse response, String username) throws Exception {
		
		/*Montando el Token*/
		String JWT = Jwts.builder()./*Llama al o generador de token*/
				setSubject(username) /*Adiciona o user*/
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) /*Temp de expiração*/
				.signWith(SignatureAlgorithm.HS512, SECRET).compact(); 
		
		/*Exe: Bearer *-/a*dad9s5d6as5d4s5d4s45dsd54s.sd4s4d45s45d4sd54d45s4d5s.ds5d5s5d5s65d6s6d*/
		String token = TOKEN_PREFIX + " " + JWT;
		
		/*Dá a resposta pra tela e para o cliente, outra API, navegador, aplicativo, javascript, outra chamadajava*/
		response.addHeader(HEADER_STRING, token);
		
		liberarCors(response);
		
		/*Usado para ver no Postman para teste*/
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
		
	}
		
	/* Retorna o usuário validado com token ou caso nao seja valido retona null */
	public Authentication getAuthetication(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String token = request.getHeader(HEADER_STRING);

		try {
			if (token != null) {

				String tokenLimpo = token.replace(TOKEN_PREFIX, "").trim();

				/* Faz a validacao do token do usuário na requisicao e obtem o USER */
				String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(tokenLimpo).getBody()
						.getSubject(); /* ADMIN ou Alex */

				if (user != null) {

					Usuario usuario = ApplicationContextLoad.getApplicationContext().getBean(UsuarioRepository.class)
							.buscarPorLogin(user);

					if (usuario != null) {
						return new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getPassword(),
								usuario.getAuthorities());
					}
				}
			}
		} catch (SignatureException e) {
			
			response.getWriter().write("Token invalido ");
			
		} catch (ExpiredJwtException e) {
			
			response.getWriter().write("Token esta expirado loguearse nuevamente");
			
		} 
		finally {
			liberarCors(response);
		}
		return null;
	}
	
	
	/*Fazendo liberação contra erro de COrs no navegador*/
	private void liberarCors(HttpServletResponse response) {
		
		if (response.getHeader("Access-Control-Allow-Origin") == null) {
			response.addHeader("Access-Control-Allow-Origin", "*");
		}
		
		if (response.getHeader("Access-Control-Allow-Headers") == null) {
			response.addHeader("Access-Control-Allow-Headers", "*");
		}
				
		if (response.getHeader("Access-Control-Request-Headers") == null) {
			response.addHeader("Access-Control-Request-Headers", "*");
		}
		
		if (response.getHeader("Access-Control-Allow-Methods") == null) {
			response.addHeader("Access-Control-Allow-Methods", "*");
		}
		
	}

}



















