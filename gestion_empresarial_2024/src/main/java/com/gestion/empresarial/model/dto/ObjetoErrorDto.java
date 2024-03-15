package com.gestion.empresarial.model.dto;

import java.io.Serializable;

public class ObjetoErrorDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String error;
	private String codigo;
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
