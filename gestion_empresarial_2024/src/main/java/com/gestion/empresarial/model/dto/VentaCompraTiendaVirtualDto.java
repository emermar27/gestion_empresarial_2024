package com.gestion.empresarial.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class VentaCompraTiendaVirtualDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private BigDecimal valorTotal;
	
	private BigDecimal valorDescuento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	
	
	

}
