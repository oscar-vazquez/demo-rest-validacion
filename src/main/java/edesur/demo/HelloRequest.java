package edesur.demo;

import com.wordnik.swagger.annotations.ApiModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@ApiModel(value = "HelloRequest")
public class HelloRequest {
    @NotNull
    private String nombre;

    @NotNull
    private String apellido;

    @Size(max = 2)
    private String otroString;

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

    public String getOtroString() {
        return otroString;
    }

    public void setOtroString(String otroString) {
        this.otroString = otroString;
    }
}
