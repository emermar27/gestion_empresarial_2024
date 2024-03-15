package com.gestion.empresarial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.empresarial.model.Acceso;
import com.gestion.empresarial.repository.AccesoRepository;

@Service
public class AccesoService {
	
	@Autowired
	private AccesoRepository accesoRepository;
	
	public Acceso salvarAcceso(Acceso acceso) {
		
		return accesoRepository.save(acceso);
	}
	
	public void eliminarAcceso(Acceso acceso) {
		accesoRepository.deleteById(acceso.getId());
	}
	
	public void eliminarAccesoPorId(Long id) {
		accesoRepository.deleteById(id);
	}
	
	public Acceso buscarAccesoPorId(Long id) {
		return accesoRepository.findById(id).orElse(null);
	}
	
	public List<Acceso> buscarAccesoPorDescripcion(String dato){
		
		List<Acceso> buscarPorDes = accesoRepository.buscarAccesoPorDescripcion(dato);
		
		return buscarPorDes;
		
	}

}
