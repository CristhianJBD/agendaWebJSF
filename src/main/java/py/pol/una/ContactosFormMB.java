package py.pol.una;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ContactosFormMB {

	private Contacto contacto = new Contacto();
	
	


	public void agregarContacto(){
		
	}
	
	public void limpiar(){
		
	}
	
	
	
	public Contacto getContacto() {
		return contacto;
	}


	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

}
