package com.gestion.empresarial.model.dto;

import java.io.Serializable;

public class CategoriaProductoDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String descripcionCategoria;
	
	private String empresa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcionCategoria() {
		return descripcionCategoria;
	}

	public void setDescripcionCategoria(String descripcionCategoria) {
		this.descripcionCategoria = descripcionCategoria;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	

}
