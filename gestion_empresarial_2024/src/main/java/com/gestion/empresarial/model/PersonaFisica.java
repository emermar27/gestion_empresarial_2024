package com.gestion.empresarial.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.gestion.empresarial.enums.TipoIdentificacion;

@Entity
@Table(name = "persona_fisica")
@PrimaryKeyJoinColumn(name = "id") //Este id es de la clase Persona
public class PersonaFisica extends Persona{

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private String nroIdentificacion;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	@Column(name = "primer_apellido", nullable = false)
	private String primerApellido;
	
	@Column(name = "segundoApellido")
	private String segundoApellido;
	
	@Column(name = "primer_nombre", nullable = false)
	private String primerNombre;
	
	@Column(name = "segundoNombre")
	private String segundoNombre;
	
	@Column(name = "tipo_identificacion", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoIdentificacion tipoIdentificacion;

	public String getNroIdentificacion() {
		return nroIdentificacion;
	}

	public void setNroIdentificacion(String nroIdentificacion) {
		this.nroIdentificacion = nroIdentificacion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public TipoIdentificacion getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	
	
}
