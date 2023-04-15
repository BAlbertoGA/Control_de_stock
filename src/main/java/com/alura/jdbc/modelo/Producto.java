package com.alura.jdbc.modelo;

public class Producto {
	
	private Integer id;
	
	private String nombre;
	
	private String descripcion;
	
	private Integer cantidad;

	private Integer categoriaId;
	
	
	
	
	public Producto(String nombre, String descripcion, Integer cantidad) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		
	}

	public Producto(int id, String nombre, String descripcion, int cantidad) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
	}

	public Producto(Integer id, String nombre, String descripcion, Integer cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
	}
	
    public Producto(int id, String nombre, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
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

	public void setnombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("{id: %s, nombre: %s, descripcion, %s, cantidad: %d}", this.id,this.nombre,this.descripcion,this.cantidad);
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
		// TODO Auto-generated method stub
		
	}

	public Integer getCategoriaID() {
		return this.categoriaId;
	}
	
}
