package com.security.project.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="autorizar")

public class Autorizar {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	
	@Column
	private String nombre;

	@OneToOne
	@JoinColumn(name="usuarios_id")
	private Users usuarios_id;

	/**
	 * 
	 */
	public Autorizar() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param nombre
	 * @param usuarios_id
	 */
	public Autorizar( String nombre, Users usuarios_id) {
		super();
	
		this.nombre = nombre;
		this.usuarios_id = usuarios_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Users getUsuarios_id() {
		return usuarios_id;
	}

	public void setUsuarios_id(Users usuarios_id) {
		this.usuarios_id = usuarios_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((usuarios_id == null) ? 0 : usuarios_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autorizar other = (Autorizar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (usuarios_id == null) {
			if (other.usuarios_id != null)
				return false;
		} else if (!usuarios_id.equals(other.usuarios_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Autorizar [id=" + id + ", nombre=" + nombre + ", usuarios_id=" + usuarios_id + "]";
	}

	

}
