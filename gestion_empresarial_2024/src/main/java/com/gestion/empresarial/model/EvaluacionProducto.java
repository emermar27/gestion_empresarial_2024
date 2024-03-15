package com.gestion.empresarial.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "evaluacion_producto")
@SequenceGenerator(name = "seq_evaluacion_producto", sequenceName = "seq_evaluacion_producto", allocationSize = 1, initialValue = 1)
public class EvaluacionProducto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_evaluacion_producto")
	private Long id;
	
	@NotNull(message = "La nota de la evaluacion no puede ser Null!")
	@Positive(message = "La nota de evaluacion debe ser mayor a cero!")
	@Min(value = 1, message = "La nota de evaluacion debe estar entre 1 y 5")
	@Max(value = 5, message = "La nota de evaluacion debe estar entre 1 y 5")
	@Column(nullable = false)
	private Integer nota;
	
	@NotBlank(message = "Por favor informe la evaluacion del Producto!")
	@Column(nullable = false)
	private String desEvaluacion;
	
	@ManyToOne
	@JoinColumn(name = "producto_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "producto_fk"))
	private Producto producto;

	@ManyToOne(targetEntity = PersonaFisica.class)
	@JoinColumn(name = "persona_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "persona_fk"))
	private PersonaFisica persona;
	
	@ManyToOne(targetEntity = PersonaJuridica.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_fk"))
	private PersonaJuridica empresa;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public PersonaFisica getPersona() {
		return persona;
	}

	public void setPersona(PersonaFisica persona) {
		this.persona = persona;
	}

	public PersonaJuridica getEmpresa() {
		return empresa;
	}

	public void setEmpresa(PersonaJuridica empresa) {
		this.empresa = empresa;
	}

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

	public String getDesEvaluacion() {
		return desEvaluacion;
	}

	public void setDesEvaluacion(String desEvaluacion) {
		this.desEvaluacion = desEvaluacion;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
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
		EvaluacionProducto other = (EvaluacionProducto) obj;
		return Objects.equals(id, other.id);
	}

	
						
}
