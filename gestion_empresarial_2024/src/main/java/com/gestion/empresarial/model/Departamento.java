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
@Table(name = "departamento")
@SequenceGenerator(name = "seq_departamento", sequenceName = "seq_departamento", allocationSize = 1, initialValue = 1)
public class Departamento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_departamento")
	private Long id;
	
	@Column(name = "codigo_departamento", nullable = false)
	private String codigoDepartamento;
	
	@Column(name = "descripcion_departamento", nullable = false)
	private String descripcionDepartamento;
	
//	@ManyToOne(targetEntity = Persona.class)
//	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_fk"))
//	private Persona empresa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoDepartamento() {
		return codigoDepartamento;
	}

	public void setCodigoDepartamento(String codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}

	public String getDescripcionDepartamento() {
		return descripcionDepartamento;
	}

	public void setDescripcionDepartamento(String descripcionDepartamento) {
		this.descripcionDepartamento = descripcionDepartamento;
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
		Departamento other = (Departamento) obj;
		return Objects.equals(id, other.id);
	}
	
		
	

}
