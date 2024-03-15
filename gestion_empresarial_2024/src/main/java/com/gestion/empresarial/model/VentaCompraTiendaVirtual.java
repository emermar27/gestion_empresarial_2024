package com.gestion.empresarial.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "vnt_cmpr_tnda_vrtl") //venta_compra_tienda_virtual
@SequenceGenerator(name = "seq_vnt_cmpr_tnda_vrtl", sequenceName = "seq_vnt_cmpr_tnda_vrtl", allocationSize = 1, initialValue = 1)
public class VentaCompraTiendaVirtual implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vnt_cmpr_tnda_vrtl")
	private Long id;
	
	//Muchas VentaCompraTiendaVirtual para una persona
	@NotNull(message = "La Persona debe ser informada!")
	@ManyToOne(targetEntity = PersonaFisica.class)
	@JoinColumn(name = "persona_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "persona_fk"))
	private PersonaFisica persona;
	
	//Muchas VentaCompraTiendaVirtual para una Direccio de entrega
	//@NotNull(message = "La direccion de entrega debe ser informada!")
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "direccion_entrega_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "direccion_entrega_fk"))
	private Direccion direccionEntrega;
	
	//Muchas VentaCompraTiendaVirtual para una Direccio de cobro
	//@NotNull(message = "La direccion de cobro debe ser informada!")
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "direccion_cobro_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "direccion_cobro_fk"))
	private Direccion direccionCobro;
	
	@NotNull(message = "El valor total no puede ser Null!")
	@Positive(message = "El valor total debe ser mayor a cero!")
	@Column(name = "valor_total", nullable = false)
	private BigDecimal valorTotal;
	
	@NotNull(message = "El valor descuento no puede ser Null!")
	@PositiveOrZero(message = "El valor descuento debe ser cero o mayor!")
	@Column(name = "valor_descuento")
	private BigDecimal valorDescuento;
	
	//Muchas VentaCompraTiendaVirtual para una Forma de Pago
	//@NotNull(message = "La forma de pago debe ser informada!")
	@ManyToOne
	@JoinColumn(name = "forma_pago_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "forma_pago_fk"))
	private FormaPago formaPago;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "factura_venta_id", nullable = true, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "factura_venta_fk"))
	private FacturaVenta facturaVenta;
	
	//Muchas VentaCompraTiendaVirtual para una Direccio de cobro
	//@NotNull(message = "Cupon descuento debe ser informada!")
	@ManyToOne
	@JoinColumn(name = "cupon_descuento_id", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "cupon_descuento_fk"))
	private CuponDescuento cuponDescuento;
	
	@NotNull(message = "El valor del flete no puede ser Null!")
	@Positive(message = "El valor del flete debe ser mayor a cero!")
	@Column(nullable = false)
	private BigDecimal valorFlete;
	
	@NotNull(message = "Los dias de entrega no puede ser Null!")
	@Positive(message = "Los dias de entrega debe ser mayor a cero!")
	@Column(nullable = false)
	private Integer diasEntrega;
	
	@NotNull(message = "La fecha de venta no puede ser Null!")
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_venta", nullable = false)
	private Date fechaVenta;

	@NotNull(message = "La fecha de entrega no puede ser Null!")
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_entrega")
	private Date fechaEntrega;
	
	//@NotNull(message = "La empresa debe ser informada!")
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

	public Direccion getDireccionEntrega() {
		return direccionEntrega;
	}

	public void setDireccionEntrega(Direccion direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
	}

	public Direccion getDireccionCobro() {
		return direccionCobro;
	}

	public void setDireccionCobro(Direccion direccionCobro) {
		this.direccionCobro = direccionCobro;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorDescuento() {
		return valorDescuento;
	}

	public void setValorDescuento(BigDecimal valorDescuento) {
		this.valorDescuento = valorDescuento;
	}

	public FormaPago getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}

	public FacturaVenta getFacturaVenta() {
		return facturaVenta;
	}

	public void setFacturaVenta(FacturaVenta facturaVenta) {
		this.facturaVenta = facturaVenta;
	}

	public CuponDescuento getCuponDescuento() {
		return cuponDescuento;
	}

	public void setCuponDescuento(CuponDescuento cuponDescuento) {
		this.cuponDescuento = cuponDescuento;
	}

	public BigDecimal getValorFlete() {
		return valorFlete;
	}

	public void setValorFlete(BigDecimal valorFlete) {
		this.valorFlete = valorFlete;
	}

	public Integer getDiasEntrega() {
		return diasEntrega;
	}

	public void setDiasEntrega(Integer diasEntrega) {
		this.diasEntrega = diasEntrega;
	}

	public Date getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
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
		VentaCompraTiendaVirtual other = (VentaCompraTiendaVirtual) obj;
		return Objects.equals(id, other.id);
	}	
	
		
		
}
