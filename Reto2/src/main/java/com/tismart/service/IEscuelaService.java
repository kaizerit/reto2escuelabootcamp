package com.tismart.service;

import java.util.List;

import com.tismart.model.Escuela;

public interface IEscuelaService {
	
	public void guardar(Escuela escuela);

	List<Escuela> list();

	Escuela listarId(Integer idEscuela);

	public void eliminar(Integer idEscuela);
	
	

}
