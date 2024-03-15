package com.gestion.empresarial.enums;

public enum StatusCuentaRecibir {
	
	COBRANZA("Cobranza"), //COBRANZA se guarda en Base de Datos y Cobranza se muestra para cliente
	VENCIDA("Vencida"),
	ABIERTA("Abierta"),
	CERRADA("Cerrada");
	
	private String descripcion;

	private StatusCuentaRecibir(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	@Override
	public String toString() {
		return this.descripcion;
	}

}
