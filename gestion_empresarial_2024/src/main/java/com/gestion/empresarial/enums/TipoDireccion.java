package com.gestion.empresarial.enums;

public enum TipoDireccion {
	
	COBRANZA("Cobranza"), //COBRANZA se guarda en Base de Datos y Cobranza se muestra para cliente
	ENTREGA("Entrega");
	
	private String descripcion;

	private TipoDireccion(String descripcion) {
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
