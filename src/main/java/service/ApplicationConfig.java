package service;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Esta clase activa JAX-RS (Servicios REST).
 * Reemplaza por completo la necesidad de un web.xml.
 */
@ApplicationPath("webresources") // Esta es la URL base que el tutorial configura en el web.xml
public class ApplicationConfig extends Application {
    // No necesitas escribir nada más aquí.
    // El servidor escaneará automáticamente tus clases con @Path.
}
