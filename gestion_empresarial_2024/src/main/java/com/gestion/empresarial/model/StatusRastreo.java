package com.gestion.empresarial.model;

import java.io.Serializable;
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


@Entity
@Table(name = "status_rastreo")
@SequenceGenerator(name = "seq_status_rastreo", sequenceName = "seq_status_rastreo", allocationSize = 1, initialValue = 1)
public class StatusRastreo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_status_rastreo")
	private Long id;
	
	@Column(nullable = false)
	private String empresaDistribucion;
	
	@Column(nullable = false)
	private String ciudad;
	
	@Column(nullable = false)
	private String municipio;
	
	@Column(nullable = false)
	private String departamento;
	
	@ManyToOne
	@JoinColumn(name = "vnt_cmpr_tnda_vrtl_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "vnt_cmpr_tnda_vrtl_fk"))
	private VentaCompraTiendaVirtual ventaCompraTiendaVirtual;
	
	@ManyToOne(targetEntity = Persona.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_fk"))
	private Persona empresa;

	public VentaCompraTiendaVirtual getVentaCompraTiendaVirtual() {
		return ventaCompraTiendaVirtual;
	}

	public void setVentaCompraTiendaVirtual(VentaCompraTiendaVirtual ventaCompraTiendaVirtual) {
		this.ventaCompraTiendaVirtual = ventaCompraTiendaVirtual;
	}

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

	public String getEmpresaDistribucion() {
		return empresaDistribucion;
	}

	public void setEmpresaDistribucion(String empresaDistribucion) {
		this.empresaDistribucion = empresaDistribucion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
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
		StatusRastreo other = (StatusRastreo) obj;
		return Objects.equals(id, other.id);
	}
	
	
				
}
