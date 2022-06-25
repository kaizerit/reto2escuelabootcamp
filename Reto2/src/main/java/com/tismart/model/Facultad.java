package com.tismart.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="facultad")
public class Facultad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "facultad_id_seq")
    @SequenceGenerator(name = "facultad_id_seq",sequenceName = "facultad_id_sequence",allocationSize = 1)
	@Column(name="id_facultad")
	private Integer idFacultad;
	
	@Column(name="desc_facultad")
	private String descFacultad;
	
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	public Integer getIdFacultad() {
		return idFacultad;
	}

	public void setIdFacultad(Integer idFacultad) {
		this.idFacultad = idFacultad;
	}

	public String getDescFacultad() {
		return descFacultad;
	}

	public void setDescFacultad(String descFacultad) {
		this.descFacultad = descFacultad;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	
	

}
