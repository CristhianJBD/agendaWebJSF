package py.pol.una;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.omg.CORBA.portable.OutputStream;

@ManagedBean(name = "FormController", eager = true)
@RequestScoped
public class ContactosFormMB {

    private Contacto contacto = new Contacto();

    public void agregarContacto() {

        try {

            URL url = new URL("https://desa03.konecta.com.py/pwf/rest/agenda");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = "{\"qty\":100,\"name\":\"iPad 4\"}";

            OutputStream os = (OutputStream) conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
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

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void limpiar() {

    }

    public Contacto getContacto() {

        return contacto;
    }

    public void setContacto(Contacto contacto) {

        this.contacto = contacto;
    }

}
