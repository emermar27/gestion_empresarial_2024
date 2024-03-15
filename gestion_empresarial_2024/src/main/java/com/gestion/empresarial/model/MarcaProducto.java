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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "marca_producto")
@SequenceGenerator(name = "seq_marca_producto", sequenceName = "seq_marca_producto", allocationSize = 1, initialValue = 1)
public class MarcaProducto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_marca_producto")
	private Long id;
	
	@NotBlank(message = "Por favor informe la Marca!")
	@Size(min = 3, max = 150, message = "La Marca debe estar entre 3 y 150 caracteres!")
	@Column(name = "descripcion_marca", nullable = false)
	private String descripcionMarca;
	
	@ManyToOne(targetEntity = PersonaJuridica.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_fk"))
	private PersonaJuridica empresa;

	public PersonaJuridica getEmpresa() {
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

	public String getDescripcionMarca() {
		return descripcionMarca;
	}

	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
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
		MarcaProducto other = (MarcaProducto) obj;
		return Objects.equals(id, other.id);
	}
	
		
	

}
