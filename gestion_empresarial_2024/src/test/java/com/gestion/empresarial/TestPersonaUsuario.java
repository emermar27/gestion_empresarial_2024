package com.gestion.empresarial;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gestion.empresarial.controller.PersonaController;
import com.gestion.empresarial.enums.TipoDireccion;
import com.gestion.empresarial.enums.TipoIdentificacion;
import com.gestion.empresarial.model.Departamento;
import com.gestion.empresarial.model.Direccion;
import com.gestion.empresarial.model.Municipio;
import com.gestion.empresarial.model.PersonaFisica;
import com.gestion.empresarial.model.PersonaJuridica;

import junit.framework.TestCase;

@SpringBootTest(classes = GestionEmpresarial2024Application.class)
public class TestPersonaUsuario extends TestCase{
	
	@Autowired
	private PersonaController personaController;
	
	@Test
	public void TestRegistroPersonaJuridica() throws ParseException, ExeptionApi {
		
		PersonaJuridica personaJuridica = new PersonaJuridica();
		
		personaJuridica.setNit("" + Calendar.getInstance().getTimeInMillis());
		personaJuridica.setNroVerificacion("7");
		personaJuridica.setRazonSocial("FamiliSoft");
		personaJuridica.setEmail("emermar27@hotmail.com");
		personaJuridica.setTelefono("3113797474");
		
		Departamento departamento = new Departamento();
		departamento.setId((long) 1);
		
		Municipio municipio = new Municipio();
		municipio.setId((long) 1);
		
		Direccion direccion1 = new Direccion();
		direccion1.setBarrio("La Esmeralda");
		direccion1.setCiudad("Popayan");
		direccion1.setDepartamento(departamento);
		direccion1.setComplemento("El Cruceros");
		direccion1.setMunicipio(municipio);
		direccion1.setTipoDireccion(TipoDireccion.ENTREGA);
		direccion1.setEmpresa(personaJuridica);
		//direccion1.setPersona(personaJuridica);
		direccion1.setDesDireccion("Por la esquina");
		
		Direccion direccion2 = new Direccion();
		direccion2.setBarrio("Crucero");
		direccion2.setCiudad("Popayan");
		direccion2.setDepartamento(departamento);
		direccion2.setComplemento("El Cruceros");
		direccion2.setMunicipio(municipio);
		direccion2.setTipoDireccion(TipoDireccion.COBRANZA);
		direccion2.setEmpresa(personaJuridica);
		//direccion2.setPersona(personaJuridica);
		direccion2.setDesDireccion("Por la esquina1");
		
		personaJuridica.getDireccion().add(direccion1);
		personaJuridica.getDireccion().add(direccion2);
		
		personaController.salvarPersonaJurica(personaJuridica);
		
		assertEquals(true, personaJuridica.getId()>0);
		
		for(Direccion direccion : personaJuridica.getDireccion()) {
			assertEquals(true, direccion.getId()>0);
		}
		
		
		
	}
	
	@Test
	public void TestRegistroPersonaFisica() throws ParseException, ExeptionApi {
		
		PersonaFisica personaFisica = new PersonaFisica();
		personaFisica.setPrimerNombre("Ermelson");
		personaFisica.setPrimerApellido("Martinez Viveros");
		personaFisica.setNroIdentificacion("" + Calendar.getInstance().getTimeInMillis());
		personaFisica.setEmail("emermar27@hotmail.com");
		personaFisica.setTelefono("3117397474");
		personaFisica.setTipoPersona("12");
		personaFisica.setTipoIdentificacion(TipoIdentificacion.CEDULA);
		
		PersonaJuridica empresa = new PersonaJuridica();
		empresa.setId((long) 70);
		
		personaFisica.setEmpresa(empresa);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		personaFisica.setFechaNacimiento(simpleDateFormat.parse("1999-07-17"));
		
		Departamento departamento = new Departamento();
		departamento.setId((long) 1);
		
		Municipio municipio = new Municipio();
		municipio.setId((long) 1);
		
		Direccion direccion1 = new Direccion();
		direccion1.setBarrio("La Esmeralda");
		direccion1.setCiudad("Popayan");
		direccion1.setDepartamento(departamento);
		direccion1.setDesDireccion("El Cruceros");
		direccion1.setMunicipio(municipio);
		direccion1.setTipoDireccion(TipoDireccion.ENTREGA);
		direccion1.setEmpresa(empresa);
		direccion1.setPersona(personaFisica);
		
		personaFisica.getDireccion().add(direccion1);
		
		personaFisica = personaController.salvarPersonaFisica(personaFisica).getBody();
		
		assertEquals(true, personaFisica.getId()>0);
		
		for(Direccion direccion : personaFisica.getDireccion()) {
			assertEquals(true, direccion.getId()>0);
		}
		
		assertEquals(1, personaFisica.getDireccion().size());
		
		//personaController.salvar(null);
		
	
	}


}
