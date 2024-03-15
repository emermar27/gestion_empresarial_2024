package com.gestion.empresarial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gestion.empresarial.model.Usuario;
import com.gestion.empresarial.repository.UsuarioRepository;

@Service
public class ImplementacionUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.buscarPorLogin(username);
		
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		
		return new User(usuario.getLogin(), usuario.getPassword(), usuario.getAuthorities());
		
	}

}
