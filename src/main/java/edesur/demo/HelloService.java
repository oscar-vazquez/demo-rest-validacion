package edesur.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*
 * La anotación indica el path de este servicio relativo
 * al path declarado en el blueprint.
 */
@Path("/")
public class HelloService {
    private static final Logger logger = LoggerFactory.getLogger(HelloService.class);

    public HelloService() {
    }

    /*
     * Este método acepta el verbo GET
     * en el path, relativo a la clase, viene la variable "name"
     * la salida es json, indicado por la anotación @Produce
     * Las variablee que recibe el método se asocian a las del path
     * por medio de la anotación @PAthParam
     */
    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Valid @NotNull
    public Response helloYou(@NotNull @PathParam("name")String nombre) {
        logger.info("GET Hello {}", nombre);
        HelloResponse response = new HelloResponse();
        response.setMensaje("Hola " + nombre);
        return Response.ok(response).build();
    }

    /*
     * Este método acepta el verbo POST
     * Consume y produce json.
     * Lo tanto lo que recibe como lo que consume son POJOs
     * El retorno del método es del tipo Response. Esta clase
     * tiene métodos estáticos para crear respuestas con los códigos
     * standard http, en este caso el 200
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Valid @NotNull
    public Response helloPost(@Valid HelloRequest request) {
        logger.info("POST Hello {} {}", request.getNombre(), request.getApellido());
        HelloResponse response = new HelloResponse();
        response.setMensaje("Hola " + request.getNombre() + " " + request.getApellido());
        response.setInEnglish("Hello Mr. " + request.getNombre() + " " + request.getApellido());
        return Response.ok(response).build();
    }
}
