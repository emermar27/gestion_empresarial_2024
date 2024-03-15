package com.gestion.empresarial.enums;

public enum TipoIdentificacion {
	
	NIT("Nit"),
	CEDULA("Cedula");
	
	private String descripcion;

	private TipoIdentificacion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.descripcion;
	}

}
