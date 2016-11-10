package py.pol.una;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@ManagedBean(name = "listaController", eager = true)
@SessionScoped
public class ContactosListadoMB {

    private List<Contacto> contactos = new ArrayList<>();

    @PostConstruct
    public void init() {

        this.contactos = new ArrayList<Contacto>();
        setContactos(listaContactos());
    }

    public void editarContacto() {

    }

    public void eliminarContacto() {

    }

    public void verContacto() {

    }

    public List<Contacto> listaContactos() {

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
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                Gson gson = new GsonBuilder().create();
                Contacto lista = gson.fromJson(output, null);

            }

            conn.disconnect();

            return null;

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return null;
    }

    public List<Contacto> getContactos() {

        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {

        this.contactos = contactos;
    }

}
