package com.gestion.empresarial.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "factura_venta")
@SequenceGenerator(name = "seq_factura_venta", sequenceName = "seq_factura_venta", allocationSize = 1, initialValue = 1)
public class FacturaVenta implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_factura_venta")
	private Long id;
	
	@Column(name = "nro_factura_venta", nullable = false)
	private String nroFacturaCompra;
	
	@Column(name = "serie_factura_venta", nullable = false)
	private String serieFacturaCompra;
	
	@Column(name = "tipo", nullable = false)
	private String tipo;
	
	@Column(name = "xml", columnDefinition = "text", nullable = false)
	private String xml;
	
	@Column(name = "pdf", columnDefinition = "text", nullable = false)
	private String pdf;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_factura_venta", nullable = false)
	private Date fechaFacturaVenta;
	
	@OneToOne
	@JoinColumn(name = "vnt_cmpr_tnda_vrtl_id", nullable = true, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "vnt_cmpr_tnda_vrtl_fk"))
	private VentaCompraTiendaVirtual ventaCompraTiendaVirtual;
	
	@ManyToOne(targetEntity = PersonaJuridica.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_fk"))
	private PersonaJuridica empresa;

	public VentaCompraTiendaVirtual getVentaCompraTiendaVirtual() {
		return ventaCompraTiendaVirtual;
	}

	public void setVentaCompraTiendaVirtual(VentaCompraTiendaVirtual ventaCompraTiendaVirtual) {
		this.ventaCompraTiendaVirtual = ventaCompraTiendaVirtual;
	}

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

	public String getNroFacturaCompra() {
		return nroFacturaCompra;
	}

	public void setNroFacturaCompra(String nroFacturaCompra) {
		this.nroFacturaCompra = nroFacturaCompra;
	}

	public String getSerieFacturaCompra() {
		return serieFacturaCompra;
	}

	public void setSerieFacturaCompra(String serieFacturaCompra) {
		this.serieFacturaCompra = serieFacturaCompra;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getPdf() {
		return pdf;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

	public Date getFechaFacturaVenta() {
		return fechaFacturaVenta;
	}

	public void setFechaFacturaVenta(Date fechaFacturaVenta) {
		this.fechaFacturaVenta = fechaFacturaVenta;
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
		FacturaVenta other = (FacturaVenta) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	//Muchas cuentas a recibir para una persona
	//@ManyToOne
	//@JoinColumn(name = "cuenta_pagar_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "cuenta_pagar_fk"))
	//private CuentaPagar cuentaPagar;

		
		
}
