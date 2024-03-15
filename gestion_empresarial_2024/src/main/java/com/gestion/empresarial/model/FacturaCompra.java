package com.gestion.empresarial.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;


@Entity
@Table(name = "factura_compra")
@SequenceGenerator(name = "seq_factura_compra", sequenceName = "seq_factura_compra", allocationSize = 1, initialValue = 1)
public class FacturaCompra implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_factura_compra")
	private Long id;
	
	@NotBlank(message = "Por favor informe el Numero de Factura de compra!")
	@Size(min = 1, max = 150, message = "El numero de factura debe estar entre 1 y 150 caracteres!")
	@Column(name = "nro_factura_compra", nullable = false)
	private String nroFacturaCompra;
	
	@NotBlank(message = "Por favor informe la seria de Factura de compra!")
	@Size(min = 1, max = 150, message = "La seria de factura debe estar entre 1 y 150 caracteres!")
	@Column(name = "serie_factura_compra", nullable = false)
	private String serieFacturaCompra;
	
	@NotBlank(message = "Por favor informe la descripcio de Factura de compra!")
	@Size(min = 10, max = 150, message = "La descripcion de factura debe estar entre 10 y 150 caracteres!")
	@Column(name = "descripcion_factura_compra")
	private String descripcionFacturaCompra;
	
	@PositiveOrZero(message = "El valor total debe ser mayor a cero o mayor!")
	@Column(name = "valor_total", nullable = false)
	private BigDecimal valorTotal;
	
	@PositiveOrZero(message = "El valor total debe ser mayor a cero o mayor!")
	@Column(name = "valor_descuento")
	private BigDecimal valorDescuento;
	
	@PositiveOrZero(message = "El valor total debe ser mayor a cero o mayor!")
	@Column(name = "valor_impuesto")
	private BigDecimal valorImpuesto;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_compra", nullable = false)
	private Date fechaCompra;
	
	//Muchas cuentas a recibir para una persona
	@ManyToOne(targetEntity = PersonaJuridica.class)
	@JoinColumn(name = "persona_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "persona_fk"))
	private PersonaJuridica persona;
	
	//Muchas cuentas a recibir para una persona
	@ManyToOne
	@JoinColumn(name = "cuenta_pagar_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "cuenta_pagar_fk"))
	private CuentaPagar cuentaPagar;
	
	@ManyToOne(targetEntity = PersonaJuridica.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_fk"))
	private PersonaJuridica empresa;

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

	public String getDescripcionFacturaCompra() {
		return descripcionFacturaCompra;
	}

	public void setDescripcionFacturaCompra(String descripcionFacturaCompra) {
		this.descripcionFacturaCompra = descripcionFacturaCompra;
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

	public BigDecimal getValorImpuesto() {
		return valorImpuesto;
	}

	public void setValorImpuesto(BigDecimal valorImpuesto) {
		this.valorImpuesto = valorImpuesto;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public PersonaJuridica getPersona() {
		return persona;
	}

	public void setPersona(PersonaJuridica persona) {
		this.persona = persona;
	}

	public CuentaPagar getCuentaPagar() {
		return cuentaPagar;
	}

	public void setCuentaPagar(CuentaPagar cuentaPagar) {
		this.cuentaPagar = cuentaPagar;
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
		FacturaCompra other = (FacturaCompra) obj;
		return Objects.equals(id, other.id);
	}

	
		
}
