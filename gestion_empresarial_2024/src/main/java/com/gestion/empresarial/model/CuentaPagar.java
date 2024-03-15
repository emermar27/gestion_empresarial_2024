package com.gestion.empresarial.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import com.gestion.empresarial.enums.StatusCuentaPagar;

@Entity
@Table(name = "cuenta_pagar")
@SequenceGenerator(name = "seq_cuenta_pagar", sequenceName = "seq_cuenta_pagar", allocationSize = 1, initialValue = 1)
public class CuentaPagar implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cuenta_pagar")
	private Long id;
	
	@NotBlank(message = "Por favor informe la descripcion de la Cuenta por Pagar!")
	@Size(min = 3, max = 150, message = "La descripcion de la Cuenta por Pagar debe estar entre 3 y 150 caracteres!")
	@Column(name = "des_cuenta_pagar", nullable = false)
	private String desCuentaPagar;
	
	@Column(name = "status_cuenta_pagar", nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusCuentaPagar statusCuentaPagar;
	
	@Column(name = "fecha_vencimiento", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaVencimiento;
	
	@Column(name = "fecha_pago")
	@Temporal(TemporalType.DATE)
	private Date fechaPago;
	
	@PositiveOrZero(message = "El valor total debe ser mayor a cero o mayor!")
	@Column(nullable = false)
	private BigDecimal valorTotal;
	
	@PositiveOrZero(message = "El valor del descuento deb ser cero (0) o mayor!")
	private BigDecimal valorDescuento;
	
	//Muchas cuentas a pagar para una persona
	@ManyToOne(targetEntity = PersonaFisica.class)
	@JoinColumn(name = "persona_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "persona_fk"))
	private PersonaFisica persona;
	
	//Muchas cuentas a pagar para un proveedor
	@ManyToOne(targetEntity = PersonaJuridica.class)
	@JoinColumn(name = "persona_proveedor_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "persona_proveedor_fk"))
	private PersonaJuridica proveedor;
	
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

	public PersonaJuridica getProveedor() {
		return proveedor;
	}

	public void setProveedor(PersonaJuridica proveedor) {
		this.proveedor = proveedor;
	}

	public PersonaJuridica getEmpresa() {
		return empresa;
	}

	public void setEmpresa(PersonaJuridica empresa) {
		this.empresa = empresa;
	}

	public String getDesCuentaPagar() {
		return desCuentaPagar;
	}

	public void setDesCuentaPagar(String desCuentaPagar) {
		this.desCuentaPagar = desCuentaPagar;
	}

	public StatusCuentaPagar getStatusCuentaPagar() {
		return statusCuentaPagar;
	}

	public void setStatusCuentaPagar(StatusCuentaPagar statusCuentaPagar) {
		this.statusCuentaPagar = statusCuentaPagar;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
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
		CuentaPagar other = (CuentaPagar) obj;
		return Objects.equals(id, other.id);
	}

			
	
}
