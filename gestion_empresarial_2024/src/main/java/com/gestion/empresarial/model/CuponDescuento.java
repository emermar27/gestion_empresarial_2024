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


@Entity
@Table(name = "cupon_descuento")
@SequenceGenerator(name = "seq_cupon_descuento", sequenceName = "seq_cupon_descuento", allocationSize = 1, initialValue = 1)
public class CuponDescuento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cupon_descuento")
	private Long id;
	
	@Column(name = "descripcion_cupon", nullable = false)
	private String descripcionCupon;

	private BigDecimal valorDescuento;
	
	private BigDecimal valorPorcentaje;
	
	@Temporal(TemporalType.DATE)
	private Date validoHasta;
	
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

	public String getDescripcionCupon() {
		return descripcionCupon;
	}

	public void setDescripcionCupon(String descripcionCupon) {
		this.descripcionCupon = descripcionCupon;
	}

	public BigDecimal getValorDescuento() {
		return valorDescuento;
	}

	public void setValorDescuento(BigDecimal valorDescuento) {
		this.valorDescuento = valorDescuento;
	}

	public BigDecimal getValorPorcentaje() {
		return valorPorcentaje;
	}

	public void setValorPorcentaje(BigDecimal valorPorcentaje) {
		this.valorPorcentaje = valorPorcentaje;
	}

	public Date getValidoHasta() {
		return validoHasta;
	}

	public void setValidoHasta(Date validoHasta) {
		this.validoHasta = validoHasta;
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
		CuponDescuento other = (CuponDescuento) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
