package com.gestion.empresarial.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
@Table(name = "producto")
@SequenceGenerator(name = "seq_producto", sequenceName = "seq_producto", allocationSize = 1, initialValue = 1)
public class Producto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_producto")
	private Long id;
	
	@NotBlank(message = "Por favor informe el nombre del Producto!")
	@Size(min = 3, max = 150, message = "El nombre del Producto debe estar entre 3 y 150 caracteres!")
	@Column(name = "nombre_producto", nullable = false)
	private String nombreProducto;
	
	@NotBlank(message = "Por favor informe la descripcion del Producto!")
	@Size(min = 3, max = 150, message = "La descripcion del Producto debe estar entre 3 y 150 caracteres!")
	@Column(name = "descripcion_producto", nullable = false, columnDefinition = "text", length = 2000)
	private String descripcionProducto;
	
	@NotBlank(message = "Por favor informe el tipo de unidad del Producto!")
	@Size(min = 1, max = 15, message = "El tipo de unidad del Producto debe estar entre 1 y 15 caracteres!")
	@Column(name = "tipo_unidad", nullable = false)
	private String tipoUnidad;
	
	@Column(nullable = false)
	private Boolean activo = Boolean.TRUE;

	@NotNull(message = "El peso no puede ser Null!")
	@Positive(message = "El peso debe ser mayor a cero!")
	@Column(nullable = false)
	private Double peso;
	
	@NotNull(message = "El largo no puede ser Null!")
	@Positive(message = "El largo debe ser mayor a cero!")
	@Column(nullable = false)
	private Double largo;
	
	@NotNull(message = "La altura no puede ser Null!")
	@Positive(message = "La altura debe ser mayor a cero!")
	@Column(nullable = false)
	private Double altura;
	
	@NotNull(message = "La profundidad no puede ser Null!")
	@Positive(message = "La profundidad debe ser mayor a cero!")
	@Column(nullable = false)
	private Double profundidad;
	
	@NotNull(message = "El precion de venta no puede ser Null!")
	@PositiveOrZero(message = "El precion de venta debe ser mayor a cero!")
	@Column(nullable = false)
	private BigDecimal precioVenta = BigDecimal.ZERO;
	
	@NotNull(message = "La cantidad inventario no puede ser Null!")
	@Positive(message = "La cantidad inventario ser mayor a cero!")
	@Column(nullable = false)
	private Integer cantidadInventario;
	
	@NotNull(message = "La alerta de cantidad maxima no puede ser Null!")
	@Positive(message = "La alerta de cantidad maxima debe ser mayor a cero!")
	@Column(nullable = false)
	private Integer alertaCantidadMaximoInventario;
	
	@NotNull(message = "La alerta de cantidad minima no puede ser Null!")
	@Positive(message = "La alerta de cantidad minima debe ser mayor a cero!")
	@Column(nullable = false)
	private Integer alertaCantidadMinimoInventario;
	
	private String linkYoutube;
	
	private Boolean alertaCantidadInventario = Boolean.FALSE;
	
	private Integer cantidadClique = 0;
	
	@ManyToOne(targetEntity = PersonaJuridica.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_fk"))
	private PersonaJuridica empresa;
	
	@ManyToOne(targetEntity = CategoriaProducto.class)
	@JoinColumn(name = "categoria_producto_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "categoria_fk"))
	private CategoriaProducto categoria;
	
	@ManyToOne(targetEntity = MarcaProducto.class)
	@JoinColumn(name = "marca_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "marca_fk"))
	private MarcaProducto marca;
	
	@OneToMany(mappedBy = "producto", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ImagenProducto> imagenes;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<ImagenProducto> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<ImagenProducto> imagenes) {
		this.imagenes = imagenes;
	}

	public CategoriaProducto getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaProducto categoria) {
		this.categoria = categoria;
	}

	public MarcaProducto getMarca() {
		return marca;
	}

	public void setMarca(MarcaProducto marca) {
		this.marca = marca;
	}

	public PersonaJuridica getEmpresa() {
		return empresa;
	}

	public void setEmpresa(PersonaJuridica empresa) {
		this.empresa = empresa;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public String getTipoUnidad() {
		return tipoUnidad;
	}

	public void setTipoUnidad(String tipoUnidad) {
		this.tipoUnidad = tipoUnidad;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getLargo() {
		return largo;
	}

	public void setLargo(Double largo) {
		this.largo = largo;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Double getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(Double profundidad) {
		this.profundidad = profundidad;
	}

	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Integer getCantidadInventario() {
		return cantidadInventario;
	}

	public void setCantidadInventario(Integer cantidadInventario) {
		this.cantidadInventario = cantidadInventario;
	}

	public Integer getAlertaCantidadMaximoInventario() {
		return alertaCantidadMaximoInventario;
	}

	public void setAlertaCantidadMaximoInventario(Integer alertaCantidadMaximoInventario) {
		this.alertaCantidadMaximoInventario = alertaCantidadMaximoInventario;
	}

	public Integer getAlertaCantidadMinimoInventario() {
		return alertaCantidadMinimoInventario;
	}

	public void setAlertaCantidadMinimoInventario(Integer alertaCantidadMinimoInventario) {
		this.alertaCantidadMinimoInventario = alertaCantidadMinimoInventario;
	}

	public String getLinkYoutube() {
		return linkYoutube;
	}

	public void setLinkYoutube(String linkYoutube) {
		this.linkYoutube = linkYoutube;
	}

	public Boolean getAlertaCantidadInventario() {
		return alertaCantidadInventario;
	}

	public void setAlertaCantidadInventario(Boolean alertaCantidadInventario) {
		this.alertaCantidadInventario = alertaCantidadInventario;
	}

	public Integer getCantidadClique() {
		return cantidadClique;
	}

	public void setCantidadClique(Integer cantidadClique) {
		this.cantidadClique = cantidadClique;
	}
	
	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
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
		Producto other = (Producto) obj;
		return Objects.equals(id, other.id);
	}

	
	
}
