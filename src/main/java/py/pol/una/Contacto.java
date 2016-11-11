package py.pol.una;

public class Contacto {
	
	private String id;
	private String nombre;
	private String apellido;
	private String alias;
	private String email;
	private String direccion;
	private String telefono;
	private String fechacreacion;
	private String fechamodificacion;
	

	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFechacreacion() {
		return fechacreacion;
	}
	public void setFechacreacion(String fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
	public String getFechamodificacion() {
		return fechamodificacion;
	}
	public void setFechamodificacion(String fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
	
	public String request() {
		return "{\"nombre\":\"" + nombre + "\",\"apellido\":\"" + apellido
				+ "\",\"alias\":\"" + alias + "\",\"telefono\":\"" + telefono
				+ "\",\"email\":\"" + email + "\",\"direccion\":\"" + direccion
				+ "\"}";
}
}
