package com.gestion.empresarial.model.dto;

import java.io.Serializable;
import java.util.Objects;

public class ImagenProductoDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String imagenOriginal;
	private String imagenMiniatura;
	private Long producto;
	private Long empresa;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImagenOriginal() {
		return imagenOriginal;
	}
	public void setImagenOriginal(String imagenOriginal) {
		this.imagenOriginal = imagenOriginal;
	}
	public String getImagenMiniatura() {
		return imagenMiniatura;
	}
	public void setImagenMiniatura(String imagenMiniatura) {
		this.imagenMiniatura = imagenMiniatura;
	}
	public Long getProducto() {
		return producto;
	}
	public void setProducto(Long producto) {
		this.producto = producto;
	}
	public Long getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Long empresa) {
		this.empresa = empresa;
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
		ImagenProductoDto other = (ImagenProductoDto) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
