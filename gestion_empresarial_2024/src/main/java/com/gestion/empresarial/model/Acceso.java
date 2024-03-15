package com.gestion.empresarial.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;

@Entity
@Table(name = "acceso")
@SequenceGenerator(name = "seq_acceso", sequenceName = "seq_acceso", allocationSize = 1, initialValue = 1)
public class Acceso implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_acceso")
	private Long id;
	
	@Column(name = "descripcion_acceso", nullable = false)
	private String descripcionAcceso;  /*Acceso ejemplo: ROLE_ADMIN o ROLE_SECRETARIO*/

	@JsonIgnore
	@Override
	public String getAuthority() {
		return this.descripcionAcceso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcionAcceso() {
		return descripcionAcceso;
	}

	public void setDescripcionAcceso(String descripcionAcceso) {
		this.descripcionAcceso = descripcionAcceso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Acceso other = (Acceso) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
		
}
