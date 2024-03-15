package com.gestion.empresarial.enums;

public enum TipoPersona {
	
	NATURAL("Natural"), //NATURAL se guarda en Base de Datos y Natural se muestra para cliente
	JURIDICA("Juridica"),
	PROVEEDOR("Proveedor");
	
	private String descripcion;

	private TipoPersona(String descripcion) {
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
