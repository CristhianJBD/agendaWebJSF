package py.pol.una;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@ManagedBean(name = "listaController", eager = true)
@SessionScoped
public class ContactosListadoMB {

    private List<Contacto> contactos = new ArrayList<>();
    private Contacto contacto ;


    @PostConstruct
    public void init() {

        this.contactos = new ArrayList<Contacto>();
        setContactos(listaContactos());
    }
    
  
    public void initForm() {

        this.contacto = new Contacto();
    }
    
    
    
    public void agregarContacto() {

        try {

            URL url = new URL("https://desa03.konecta.com.py/pwf/rest/agenda");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = this.contacto.request();

            OutputStream os = (OutputStream) conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();
            this.contacto = null;

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void limpiar() {

    }

    public void editarContacto(RowEditEvent event) {
    	
    	Contacto c = (Contacto) event.getObject();
    	 try {
    		 
             URL url = new URL("https://desa03.konecta.com.py/pwf/rest/agenda/" + c.getId());
             HttpURLConnection conn = (HttpURLConnection) url.openConnection();
             conn.setDoOutput(true);
             conn.setRequestMethod("PUT");
             conn.setRequestProperty("Content-Type", "application/json");
             
   //          c.setFechamodificacion(new Timestamp(System.currentTimeMillis()).toString());
             
             String input = c.request() ;

             OutputStream os = conn.getOutputStream();
             os.write(input.getBytes());
             os.flush();

             if (conn.getResponseCode() != 200) {
                 throw new RuntimeException("Failed : HTTP error code : "
                         + conn.getResponseCode());
             }

             BufferedReader br = new BufferedReader(new InputStreamReader(
                     (conn.getInputStream())));

             String output;
             System.out.println("Output from Server .... \n");
             while ((output = br.readLine()) != null) {
                 System.out.println(output);
             }

             conn.disconnect();
             FacesMessage msg = new FacesMessage("Se edito correctamente el contacto con ID:", (c.getId()));
             FacesContext.getCurrentInstance().addMessage(null, msg);

         } catch (MalformedURLException e) {

             e.printStackTrace();

         } catch (IOException e) {

             e.printStackTrace();

         }
    }


    public void cancelar(RowEditEvent event) {
    	Contacto c = (Contacto) event.getObject();
    	 FacesMessage msg = new FacesMessage("Se cancelo la edicion del contacto:", (c.getId()));
         FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    
    public void eliminarContacto() {

    }

//    public Contacto verContacto() {
//    	Contacto c;
//    	  try {
//
//              URL url = new URL("https://desa03.konecta.com.py/pwf/rest/agenda/"+);
//              HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//              conn.setRequestMethod("GET");
//              conn.setRequestProperty("Accept", "application/json");
//
//              if (conn.getResponseCode() != 200) {
//                  throw new RuntimeException("Failed : HTTP error code : "
//                          + conn.getResponseCode());
//              }
//
//              BufferedReader br = new BufferedReader(new InputStreamReader(
//                      (conn.getInputStream())));
//
//              String output;
//              while ((output = br.readLine()) != null) {
//                  Gson gson = new GsonBuilder().create();
//                  c = gson.fromJson(lista,new TypeToken<Contacto>(){
//                  }.getType());
//
//              }
//
//              conn.disconnect();
//
//          } catch (MalformedURLException e) {
//
//              e.printStackTrace();
//
//          } catch (IOException e) {
//
//              e.printStackTrace();
//
//          }
//          return lisCont;
//    }

    public List<Contacto> listaContactos() {
    	int inicio,fin;
    	List<Contacto> lisCont = null;
    	String lista;
        try {

            URL url = new URL("https://desa03.konecta.com.py/pwf/rest/agenda");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                Gson gson = new GsonBuilder().create();
            	inicio=output.indexOf("[");
            	fin=output.length()-1;
            	lista = output.substring(inicio,fin);
                lisCont = gson.fromJson(lista,new TypeToken<ArrayList<Contacto>>(){
                }.getType());

            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return lisCont;
    }

    public List<Contacto> getContactos() {

        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {

        this.contactos = contactos;
    }

    
    public Contacto getContacto() {

        return contacto;
    }

    public void setContacto(Contacto contacto) {

        this.contacto = contacto;
    }
    
	public String formatDate(String date) throws ParseException
	{	if (date!=""){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			Date temp= sdf.parse(date);
			sdf=new SimpleDateFormat("dd-MM-YYYY hh:mm");
			String dateString=sdf.format(temp);
			return dateString ;
		}
		else return date;
}
}
