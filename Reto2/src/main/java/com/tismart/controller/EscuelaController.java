package com.tismart.controller;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tismart.model.Escuela;
import com.tismart.model.Facultad;
import com.tismart.service.IEscuelaService;
import com.tismart.service.IFacultadService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/escuelas")
public class EscuelaController {

	@Autowired
	private IFacultadService fS;

	@Autowired
	private IEscuelaService eS;

	@GetMapping("/index")
	public String mostrarIndex(Model model) {

		return "menu";
	}

	@GetMapping("/create")
	public String crear(Model model) {
		Escuela escuela = new Escuela();
		List<Facultad> listFacultades = fS.list();
		model.addAttribute("escuela", escuela);
		model.addAttribute("listFacultades", listFacultades);

		return "escuela/escuela";
	}

	@PostMapping("/save")
	public String guardar(Escuela escuela, BindingResult result, RedirectAttributes atributtes) {
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
			}
			return "escuela/escuela";
		}

		eS.guardar(escuela);
		atributtes.addFlashAttribute("msg", "Registró correctamente");
		// System.out.println("Escuela: " + escuela);
		return "redirect:/escuelas/list";
	}

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idEscuela, Model model) {
		Escuela escuela = eS.listarId(idEscuela);
		model.addAttribute("escuela", escuela);
		List<Facultad> listFacultades = fS.list();
		model.addAttribute("listFacultades", listFacultades);

		return "escuela/escuela";
	}

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idEscuela, Model model) {
		// System.out.println("Borrando vacante con id:" + idEscuela);
		eS.eliminar(idEscuela);
		return "redirect:/escuelas/list";

	}

	@GetMapping("/list")
	public String listEscuela(Model model, Escuela escuela) {
		try {

			List<Escuela> escuelas = eS.list();
			// model.addAttribute("Escuela", new Escuela());
			model.addAttribute("escuelas", escuelas);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "escuela/listEscuelas";
	}

	
	@GetMapping("/pdf")
	public ResponseEntity<byte[]> exportPdf() throws Exception, JRException {
		
		List<Escuela> lista = eS.list();
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(lista);
	    JasperReport  compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/reporte/Blank_A4.jrxml"));
		HashMap<String, Object> map = new HashMap<>();
	    JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
	    byte[] data = JasperExportManager.exportReportToPdf(report);
		
	    HttpHeaders headers=new HttpHeaders();
	    headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=ReporteEscuela.pdf");
	    return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	    
	}


}
