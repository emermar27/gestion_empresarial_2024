package com.gestion.empresarial.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "categoria_producto")
@SequenceGenerator(name = "seq_categoria_producto", sequenceName = "seq_categoria_producto", allocationSize = 1, initialValue = 1)
public class CategoriaProducto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_categoria_producto")
	private Long id;
	
	@NotBlank(message = "Por favor informe el Nombre de la Categoria!")
	@Size(min = 3, max = 150, message = "El nombre de la categoria debe estar entre 3 y 150 caracteres!")
	@Column(name = "descripcion_categoria", nullable = false)
	private String descripcionCategoria;
	
	@ManyToOne(targetEntity = Persona.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_fk"))
	private PersonaJuridica empresa = new PersonaJuridica();
	
	public Persona getEmpresa() {
		return empresa;
	}

	public void setEmpresa(PersonaJuridica empresa) {
		this.empresa = empresa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcionCategoria() {
		return descripcionCategoria;
	}

	public void setDescripcionCategoria(String descripcionCategoria) {
		this.descripcionCategoria = descripcionCategoria;
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
		CategoriaProducto other = (CategoriaProducto) obj;
		return Objects.equals(id, other.id);
	}
	
		
	

}
