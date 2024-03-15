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

import com.gestion.empresarial.enums.StatusCuentaRecibir;

@Entity
@Table(name = "cuenta_recibir")
@SequenceGenerator(name = "seq_cuenta_recibir", sequenceName = "seq_cuenta_recibir", allocationSize = 1, initialValue = 1)
public class CuentaRecibir implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cuenta_recibir")
	private Long id;
	
	@Column(name = "des_cuenta_recibir", nullable = false)
	private String desCuentaRecibir;
	
	@Column(name = "status_cuenta_recibir", nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusCuentaRecibir statusCuentaRecibir;
	
	@Column(name = "fecha_vencimiento", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaVencimiento;
	
	@Column(name = "fecha_pago")
	@Temporal(TemporalType.DATE)
	private Date fechaPago;
	
	@Column(nullable = false)
	private BigDecimal valorTotal;
	
	private BigDecimal valorDescuento;
	
	//Muchas cuentas a recibir para una persona
	@ManyToOne(targetEntity = Persona.class)
	@JoinColumn(name = "persona_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "persona_fk"))
	private Persona persona;
	
	@ManyToOne(targetEntity = Persona.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_fk"))
	private Persona empresa;
	
	
	public Persona getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Persona empresa) {
		this.empresa = empresa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesCuentaRecibir() {
		return desCuentaRecibir;
	}

	public void setDesCuentaRecibir(String desCuentaRecibir) {
		this.desCuentaRecibir = desCuentaRecibir;
	}

	public StatusCuentaRecibir getStatusCuentaRecibir() {
		return statusCuentaRecibir;
	}

	public void setStatusCuentaRecibir(StatusCuentaRecibir statusCuentaRecibir) {
		this.statusCuentaRecibir = statusCuentaRecibir;
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

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
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
		CuentaRecibir other = (CuentaRecibir) obj;
		return Objects.equals(id, other.id);
	}
	
	
		
	
}
