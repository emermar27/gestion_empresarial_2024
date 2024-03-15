package com.gestion.empresarial.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "persona_juridica")
@PrimaryKeyJoinColumn(name = "id") //Este id es de la clase Persona
public class PersonaJuridica extends Persona{

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private String nit;
	
	@Column(nullable = false, name = "nro_verificacion")
	private String nroVerificacion;
	
	@Column(nullable = false, name = "razon_social")
	private String razonSocial;
	
	@Column(name = "nombre_fantacia")
	private String nombreFantacia;
	
	private String categoria;

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getNroVerificacion() {
		return nroVerificacion;
	}

	public void setNroVerificacion(String nroVerificacion) {
		this.nroVerificacion = nroVerificacion;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getNombreFantacia() {
		return nombreFantacia;
	}

	public void setNombreFantacia(String nombreFantacia) {
		this.nombreFantacia = nombreFantacia;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	

}
