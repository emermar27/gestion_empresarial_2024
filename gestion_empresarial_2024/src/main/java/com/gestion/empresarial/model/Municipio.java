package com.gestion.empresarial.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "municipio")
@SequenceGenerator(name = "seq_municipio", sequenceName = "seq_municipio", allocationSize = 1, initialValue = 1)
public class Municipio implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_municipio")
	private Long id;
	
	@Column(name = "codigo_municipio", nullable = false)
	private String codigoMunicipio;
	
	@Column(name = "descripcion_municipio", nullable = false)
	private String descripcionMunicipio;
	
//	@ManyToOne(targetEntity = Persona.class)
//	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_fk"))
//	private Persona empresa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public void setCodigoMunicipio(String codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	public String getDescripcionMunicipio() {
		return descripcionMunicipio;
	}

	public void setDescripcionMunicipio(String descripcionMunicipio) {
		this.descripcionMunicipio = descripcionMunicipio;
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
		Municipio other = (Municipio) obj;
		return Objects.equals(id, other.id);
	}
	
		
	

}
