package com.gestion.empresarial.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.gestion.empresarial.enums.TipoDireccion;

@Entity
@Table(name = "direccion")
@SequenceGenerator(name = "seq_direccion", sequenceName = "seq_direccion", allocationSize = 1, initialValue = 1)
public class Direccion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_direccion")
	private Long id;
	
	@NotBlank(message = "Por favor informe la direccion!")
	@Size(min = 3, max = 150, message = "La direccion debe estar entre 3 y 150 caracteres!")
	@Column(nullable = false)
	private String desDireccion;
	
	private String complemento;
	
	@NotBlank(message = "Por favor informe el barrio!")
	@Size(min = 3, max = 150, message = "El barrio debe estar entre 3 y 150 caracteres!")
	@Column(nullable = false)
	private String barrio;
	
	@NotNull(message = "El departamento no puede ser Null!")
	@ManyToOne
	@JoinColumn(name = "departamento_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "departamento_fk"))
	private Departamento departamento;
	
	@NotNull(message = "El municipio no puede ser Null!")
	@ManyToOne
	@JoinColumn(name = "municipio_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "municipio_fk"))
	private Municipio municipio;
	
	@NotBlank(message = "Por favor informe la ciudad!")
	@Size(min = 3, max = 150, message = "La ciudad debe estar entre 3 y 150 caracteres!")
	@Column(nullable = false)
	private String ciudad;
	
	@NotNull(message = "La persona no puede ser Null!")
	//@JsonIgnore
	@ManyToOne(targetEntity = PersonaFisica.class)
	@JoinColumn(name = "persona_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "persona_fk"))
	private PersonaFisica persona;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoDireccion tipoDireccion;
	
	@NotNull(message = "La empresa no puede ser Null!")
	//@JsonIgnore
	@ManyToOne(targetEntity = PersonaJuridica.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_fk"))
	private PersonaJuridica empresa;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesDireccion() {
		return desDireccion;
	}

	public void setDesDireccion(String desDireccion) {
		this.desDireccion = desDireccion;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public PersonaFisica getPersona() {
		return persona;
	}

	public void setPersona(PersonaFisica persona) {
		this.persona = persona;
	}

	public TipoDireccion getTipoDireccion() {
		return tipoDireccion;
	}

	public void setTipoDireccion(TipoDireccion tipoDireccion) {
		this.tipoDireccion = tipoDireccion;
	}

	public PersonaJuridica getEmpresa() {
		return empresa;
	}

	public void setEmpresa(PersonaJuridica empresa) {
		this.empresa = empresa;
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
		Direccion other = (Direccion) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
