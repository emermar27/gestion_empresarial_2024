package com.gestion.empresarial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ControlAccesoEndPointService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void actualizarEndPointSalvarPersonaJuridica() {
		jdbcTemplate.execute("begin; update control_acceso_end_point set cantidad_acceso = cantidad_acceso + 1 where des_end_point = 'salvarPersonaJuridica'; commit;");
	}

}
