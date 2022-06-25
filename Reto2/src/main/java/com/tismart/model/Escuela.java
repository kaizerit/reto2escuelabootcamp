package com.tismart.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="escuela")
public class Escuela {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "escuela_id_seq")
    @SequenceGenerator(name = "escuela_id_seq",sequenceName = "escuela_id_sequence",allocationSize = 1)
	@Column(name="id_escuela")
	private Integer idEscuela;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="cant_alumnos")
	private int cantAlumnos;
	
	@Column(name="recurso_fiscal")
	private Double recursoFiscal;
	
	@Column(name="licenciada")
	private Integer licenciada;
	
	@Column(name="clasificacion")
	private int clasificacion;
	
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro")
	private Date fechaRegistro;
	
	@ManyToOne
	@JoinColumn(name="id_facultad")
	private Facultad facultad;

	public Integer getIdEscuela() {
		return idEscuela;
	}

	public void setIdEscuela(Integer idEscuela) {
		this.idEscuela = idEscuela;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantAlumnos() {
		return cantAlumnos;
	}

	public void setCantAlumnos(int cantAlumnos) {
		this.cantAlumnos = cantAlumnos;
	}

	public Double getRecursoFiscal() {
		return recursoFiscal;
	}

	public void setRecursoFiscal(Double recursoFiscal) {
		this.recursoFiscal = recursoFiscal;
	}

	public Integer getLicenciada() {
		return licenciada;
	}

	public void setLicenciada(Integer licenciada) {
		this.licenciada = licenciada;
	}

	public int getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(int clasificacion) {
		this.clasificacion = clasificacion;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Facultad getFacultad() {
		return facultad;
	}

	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	}
	
	

}
