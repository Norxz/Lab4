package service;

import entidad.Person;
import entidad.Salario; // 1. Importar la nueva clase
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Path("service")
public class Service {
    
    private static Map<Integer, Person> persons = new HashMap<>();
    
    static {
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            int id = i + 1;
            person.setId(id);
            person.setFullName("My wonderfull Person " + id);
            
            // 2. Al llamar a setAge(), el salario se calculará automáticamente
            person.setAge(new Random().nextInt(40) + 1);
            
            persons.put(id, person);
        }
    }
    
    @GET
    @Path("/getPersonByIdXML/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Person getPersonByIdXML(@PathParam("id") int id) {
        return persons.get(id);
    }
    
    @GET
    @Path("/getPersonByIdJson/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPersonByIdJson(@PathParam("id") int id) {
        return persons.get(id);
    }
    
    @GET
    @Path("/getAllPersonsInXML")
    @Produces(MediaType.APPLICATION_XML)
    public List<Person> getAllPersonsInXML() {
        return new ArrayList<>(persons.values());
    }
    
    @GET
    @Path("/getAllPersonsInJson")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAllPersonsInJson() {
        return new ArrayList<>(persons.values());
    }
    
    /**
     * 4. MODIFICADO: Servicio para adicionar una persona.
     * Ahora recalcula el salario en el servidor.
     */
    @POST
    @Path("/addPersonInJson")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Person addPersonInJson(Person person) {
        System.out.println("Agregando persona con id: " + person.getId());
        
        // 5. REGLa DE NEGOCIO:
        // Recalculamos el salario en el servidor para asegurar la fórmula,
        // ignorando cualquier valor de salario que el cliente haya enviado.
        person.setAge(person.getAge()); // Esto dispara la lógica en el setter
        
        persons.put(person.getId(), person);
        return person;
    }
    
    
    /**
     * 6. NUEVO: Servicio para el salario promedio en XML.
     */
    @GET
    @Path("/getPromedioSalariosXML")
    @Produces(MediaType.APPLICATION_XML)
    public Salario getPromedioSalariosXML() {
        if (persons.isEmpty()) {
            return new Salario(0);
        }
        
        double suma = 0;
        for (Person p : persons.values()) {
            suma += p.getSalario();
        }
        
        double promedio = suma / persons.size();
        return new Salario(promedio);
    }
    
    /**
     * 7. NUEVO: Servicio para la suma de salarios en JSON.
     */
    @GET
    @Path("/getSumaSalariosJSON")
    @Produces(MediaType.APPLICATION_JSON)
    public Salario getSumaSalariosJSON() {
        double suma = 0;
        for (Person p : persons.values()) {
            suma += p.getSalario();
        }
        return new Salario(suma);
    }
}