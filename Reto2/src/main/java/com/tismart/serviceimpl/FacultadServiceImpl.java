package com.tismart.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tismart.model.Facultad;
import com.tismart.repository.FacultadRepository;
import com.tismart.service.IFacultadService;

@Service
public class FacultadServiceImpl implements IFacultadService{

	@Autowired
	private FacultadRepository fR;
	
	@Override
	public List<Facultad> list() {
		
		return fR.findAll();
	}

}
