package edesur.demo;

import org.apache.cxf.jaxrs.utils.JAXRSUtils;
import org.apache.cxf.validation.ResponseConstraintViolationException;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MyValidationExceptionMapper implements ExceptionMapper<ValidationException> {
    private static final Logger logger = LoggerFactory.getLogger(MyValidationExceptionMapper.class);

    private boolean addMessageToResponse;

    @Override
    public Response toResponse(ValidationException exception) {
        Response.Status errorStatus = Response.Status.INTERNAL_SERVER_ERROR;
        if (exception instanceof ConstraintViolationException) {

            StringBuilder responseBody = addMessageToResponse ? new StringBuilder() : null;

            final ConstraintViolationException constraint = (ConstraintViolationException) exception;

            for (final ConstraintViolation< ? > violation: constraint.getConstraintViolations()) {
                String message = buildErrorMessage(violation);
                if (responseBody != null) {
                    responseBody.append(message).append("\n");
                }
                logger.warn(message);
            }

            if (!(constraint instanceof ResponseConstraintViolationException)) {
                errorStatus = Response.Status.BAD_REQUEST;
            }
            return buildResponse(errorStatus, responseBody != null ? responseBody.toString() : null);
        }
        return buildResponse(errorStatus, addMessageToResponse ? exception.getMessage() : null);
    }

    protected String buildErrorMessage(ConstraintViolation<?> violation) {
        /*
        logger.warn("XXX {}", ((PathImpl)violation.getPropertyPath()).getLeafNode().getName());
        logger.warn("XXX {}", violation.getPropertyPath());
        logger.warn("XXX {}", violation.getRootBean());
        logger.warn("XXX {}", violation.getRootBeanClass());
        logger.warn("XXX {}", violation.getRootBeanClass().getCanonicalName());
        for (Path.Node n: violation.getPropertyPath()) {
            logger.warn("XXX {}", n);
            logger.warn("XXX {}", n.getName());
        }
        */
        String name = null;
        for (Path.Node n: violation.getPropertyPath()) {
            name = n.getName();
        }

        /*
        return "Value "
                + (violation.getInvalidValue() != null ? "'" + violation.getInvalidValue().toString() + "'" : "(null)")
                + " of " //+ name //violation.getRootBeanClass().getSimpleName()
                //+ "." + violation.getPropertyPath()
                + name
                + ": " + violation.getMessage();
                */
        return name
               + ": " + violation.getMessage()
               + (violation.getInvalidValue() != null ? ", value: '" + violation.getInvalidValue().toString() + "'" : "");
    }

    protected Response buildResponse(Response.Status errorStatus, String responseText) {
        Response.ResponseBuilder rb = JAXRSUtils.toResponseBuilder(errorStatus);
        if (responseText != null) {
            rb.type(MediaType.TEXT_PLAIN).entity(responseText);
        }
        return rb.build();
    }

    /**
     * Controls whether to add a constraint validation message to Response or not
     * @param addMessageToResponse add a constraint validation message to Response
     */
    public void setAddMessageToResponse(boolean addMessageToResponse) {
        this.addMessageToResponse = addMessageToResponse;
    }

}