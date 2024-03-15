package com.gestion.empresarial.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "seq_persona", sequenceName = "seq_persona", allocationSize = 1, initialValue = 1)
public abstract class Persona implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_persona")
	private Long id;
	
	@NotEmpty(message = "Por favor informe un Email valido")
	@NotBlank(message = "Por favor informe un Email valido")
	@Email(message = "Por favor informe un Email valido")
	@Column(nullable = false)
	private String email;
	
	@NotEmpty(message = "Por favor informe un Telefono/Celular valido")
	@NotBlank(message = "Por favor informe un Telefono/Celular valido")
	@Size(min = 10, max = 50, message = "Por favor informe un Telefono/Celular valido")
	@Column(nullable = false)
	private String telefono;
	
	@Column(nullable = false)
	private String tipoPersona;
	
	//Esta relacion es opcional porque aqui son una o tres direcciones maximo
	//Si son muchos registros esta no se debe hacer porque pone lento las consultas OJo Pelao
	@OneToMany(mappedBy = "persona", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Direccion> direccion = new ArrayList<Direccion>();
	
	@ManyToOne(targetEntity = Persona.class)
	@JoinColumn(name = "empresa_id", nullable = true, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_fk"))
	private Persona empresa;

	public List<Direccion> getDireccion() {
		return direccion;
	}

	public void setDireccion(List<Direccion> direccion) {
		this.direccion = direccion;
	}

	public Persona getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Persona empresa) {
		this.empresa = empresa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getTipoPersona() {
		return tipoPersona;
	}
	
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
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
		Persona other = (Persona) obj;
		return Objects.equals(id, other.id);
	}
	
	
	

}
