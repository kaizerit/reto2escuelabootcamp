package com.tismart.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tismart.model.Escuela;
import com.tismart.repository.EscuelaRepository;
import com.tismart.service.IEscuelaService;

@Service
public class EscuelaServiceImpl implements IEscuelaService{

	@Autowired
	private EscuelaRepository eR;
	
	@Override
	public void guardar(Escuela escuela) {
		eR.save(escuela);
		
	}

	@Override
	public List<Escuela> list() {
		
		return eR.findAll(Sort.by(Sort.Direction.ASC, "idEscuela"));
	}

	@Override
	public Escuela listarId(Integer idEscuela) {
		
		Optional<Escuela> optional = eR.findById(idEscuela);
		if (optional.isPresent())
		{
			return optional.get();			
		}
		
		return null;
	}

	@Override
	public void eliminar(Integer idEscuela) {
		eR.deleteById(idEscuela);
		
	}

}
