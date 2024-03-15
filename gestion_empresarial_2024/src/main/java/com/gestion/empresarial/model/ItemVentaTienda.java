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

@Entity
@Table(name = "item_venta_tienda")
@SequenceGenerator(name = "seq_item_venta_tienda", sequenceName = "seq_item_venta_tienda", allocationSize = 1, initialValue = 1)
public class ItemVentaTienda implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item_venta_tienda")
	private Long id;
	
	@Column(nullable = false)
	private Double cantidad;
	
	@ManyToOne
	@JoinColumn(name = "producto_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "producto_fk"))
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "vnt_cmpr_tnda_vrtl_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "vnt_cmpr_tnda_vrtl_fk"))
	private VentaCompraTiendaVirtual ventaCompraTiendaVirtual;
	
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

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public VentaCompraTiendaVirtual getVentaCompraTiendaVirtual() {
		return ventaCompraTiendaVirtual;
	}

	public void setVentaCompraTiendaVirtual(VentaCompraTiendaVirtual ventaCompraTiendaVirtual) {
		this.ventaCompraTiendaVirtual = ventaCompraTiendaVirtual;
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
		ItemVentaTienda other = (ItemVentaTienda) obj;
		return Objects.equals(id, other.id);
	}
	
	
					
}
