package com.empresa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.empresa.entity.Deporte;
import com.empresa.entity.Modalidad;
import com.empresa.service.DeporteService;
import com.empresa.service.ModalidadService;
import com.empresa.util.Constantes;

@Controller
public class ModalidadController {

	@Autowired
	private DeporteService deporteService;

	@Autowired
	private ModalidadService modalidadService;

	@RequestMapping("/verCrudModalidad")
	public String verInicio() {
		return "crudModalidad";
	}
	
	@ResponseBody
	@RequestMapping("/consultaCrudModalidad")
	public List<Modalidad> listaModalidad(String filtro){
		return modalidadService.listaModalidadPorNombreLike("%" + filtro + "%");
	}
	
	@ResponseBody
	@RequestMapping("/listaDeporte")
	public List<Deporte> listaDeporte(){
		return deporteService.listaDeporte();
	}

	@ResponseBody
	@RequestMapping("/registraCrudModalidad")
	public Map<String, Object> registra(Modalidad obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			Modalidad objSalida = modalidadService.insertaActualizaModalidad(obj);
			if (objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
			}else {
				salida.put("mensaje", Constantes.MENSAJE_REG_EXITOSO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		} finally {
			List<Modalidad> lista = modalidadService.listaModalidad();
			salida.put("lista", lista);
		}
		return salida;
	}
	
	@ResponseBody
	@RequestMapping("/actualizaCrudModalidad")
	public Map<String, Object> actualiza(Modalidad obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			Modalidad objSalida = modalidadService.insertaActualizaModalidad(obj);
			if (objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_ACT_ERROR);
			}else {
				salida.put("mensaje", Constantes.MENSAJE_ACT_EXITOSO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_ACT_ERROR);
		} finally {
			List<Modalidad> lista = modalidadService.listaModalidad();
			salida.put("lista", lista);
		}
		return salida;
	}
	
	@ResponseBody
	@RequestMapping("/eliminaCrudModalidad")
	public Map<String, Object> elimina(int id){
		Map<String, Object> salida = new HashMap<>();
		try {
			Optional<Modalidad> objSalida = modalidadService.obtienePorId(id);
			if (objSalida.isPresent()) {
				modalidadService.eliminaModalidad(id);
				salida.put("mensaje", Constantes.MENSAJE_ELI_EXITOSO);
			}else {
				salida.put("mensaje", Constantes.MENSAJE_ELI_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_ELI_ERROR);
		} finally {
			List<Modalidad> lista = modalidadService.listaModalidad();
			salida.put("lista", lista);
		}
		return salida;
	}
}




