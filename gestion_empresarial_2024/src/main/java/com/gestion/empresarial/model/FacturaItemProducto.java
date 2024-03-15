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
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "factura_item_producto")
@SequenceGenerator(name = "seq_factura_item_producto", sequenceName = "seq_factura_item_producto", allocationSize = 1, initialValue = 1)
public class FacturaItemProducto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_factura_item_producto")
	private Long id;
	
	@PositiveOrZero(message = "La cantidad debe ser mayor a cero!")
	@Column(nullable = false)
	private Double cantidad;
	
	@ManyToOne
	@JoinColumn(name = "factura_compra_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "factura_compra_fk"))
	private FacturaCompra facturaCompra;
	
	@ManyToOne
	@JoinColumn(name = "producto_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "producto_fk"))
	private Producto producto;
	
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

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public FacturaCompra getFacturaCompra() {
		return facturaCompra;
	}

	public void setFacturaCompra(FacturaCompra facturaCompra) {
		this.facturaCompra = facturaCompra;
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
		FacturaItemProducto other = (FacturaItemProducto) obj;
		return Objects.equals(id, other.id);
	}

	
			
}
