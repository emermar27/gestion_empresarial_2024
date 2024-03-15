package com.gestion.empresarial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.gestion.empresarial.model.Producto;

@Transactional
public interface ProductoRepository extends JpaRepository<Producto, Long>{
	
	@Query(nativeQuery = true, value = "select count(1) > 0 from producto where upper(trim(nombre_producto)) = upper(trim(?1)) and empresa_id = ?2")
	public boolean existeProductoPorNombre(String dato, Long empresaId);
	
	@Query("select tbl from Producto tbl where tbl.cantidadInventario = ?1 and tbl.empresa.id = ?2")
	public List<Producto> buscarProductoPorCantidadInventario(Integer cantidad, Long empresaId);
	
	@Query("select tbl from Producto tbl where upper(trim(tbl.nombreProducto)) like %?1% and tbl.empresa.id = ?2")
	public List<Producto> buscarPorNombres(String dato, Long empresaId);
	
	@Query("select tbl from Producto tbl where tbl.nombreProducto = ?1 and tbl.empresa.id = ?2")
	public List<Producto> buscarPorNombre(String dato, Long empresaId);
	
	@Query("select tbl from Producto tbl where tbl.marca.id = ?1 and tbl.empresa.id = ?2")
	public List<Producto> buscarProductoPorMarca(Long marcaId, Long empresaId);
	
	@Query("select tbl from Producto tbl where tbl.categoria.id = ?1 and tbl.empresa.id = ?2")
	public List<Producto> buscarProductoPorCategoria(Long categoriaId, Long empresaId);
	
}
