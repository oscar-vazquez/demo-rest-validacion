package edesur.demo;

import javax.validation.constraints.NotNull;

public class HelloRequest {
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

    @NotNull private String nombre;
    @NotNull private String apellido;
}
