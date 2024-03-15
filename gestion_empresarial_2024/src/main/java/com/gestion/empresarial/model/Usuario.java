package com.gestion.empresarial.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "usuario")
@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", initialValue = 1, allocationSize = 1)
public class Usuario implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "seq_usuario", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String login;
	
	@Column(nullable = false)
	private String password;
	
	@Column(name = "fecha_actual_password", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaActualPassword;
	
	@ManyToOne(targetEntity = Persona.class)
	@JoinColumn(name = "persona_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "persona_fk"))
	private Persona persona;
	
	@ManyToOne(targetEntity = Persona.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_fk"))
	private Persona empresa;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuarios_acceso", 
		uniqueConstraints = @UniqueConstraint (columnNames = {"usuario_id", "acceso_id"} ,
		name = "unique_acceso_user"),
	
	   joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id", table = "usuario", 
	   unique = false, foreignKey = @ForeignKey(name = "usuario_fk", value = ConstraintMode.CONSTRAINT)), 
	   
	inverseJoinColumns = @JoinColumn(name = "acceso_id", 
						unique = false, referencedColumnName = "id", table = "acceso",
						foreignKey = @ForeignKey(name = "acceso_fk", value = ConstraintMode.CONSTRAINT)))
	private List<Acceso> accesos;
	
	/*Son los accesos, o sea ROLE_ADMIN, ROLE_SECRETARIO, ROLE_CONSULTA, ROLE_INVITADO*/
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.accesos;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.login;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Date getFechaActualPassword() {
		return fechaActualPassword;
	}

	public void setFechaActualPassword(Date fechaActualPassword) {
		this.fechaActualPassword = fechaActualPassword;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Persona getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Persona empresa) {
		this.empresa = empresa;
	}

	public List<Acceso> getAccesos() {
		return accesos;
	}

	public void setAccesos(List<Acceso> accesos) {
		this.accesos = accesos;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
