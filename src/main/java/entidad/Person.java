package entidad;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "persona")
// 1. Añadimos "salario" al orden de propiedades XML
@XmlType(propOrder = {"id", "fullName", "age", "salario"})
public class Person {
    
    // 2. Definimos la constante del salario mínimo
    public static final double SALARIO_MINIMO = 1423500;
    
    private int id;
    private String fullName;
    private int age;
    private double salario; // 3. Nuevo atributo
    
    public Person() {
    }
    
    // --- Getters y Setters ---
    
    @XmlElement
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    @XmlElement
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    @XmlElement
    public int getAge() {
        return age;
    }
    
    // 4. Lógica de negocio: El salario se calcula al asignar la edad
    public void setAge(int age) {
        this.age = age;
        // Aplica la fórmula automáticamente
        this.salario = calcularSalario(age);
    }
    
    // 5. Getter y Setter para el nuevo atributo
    @XmlElement
    public double getSalario() {
        return salario;
    }
    
    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    // 6. Método privado con la fórmula
    private double calcularSalario(int edad) {
        return (double) edad * SALARIO_MINIMO / 3.0;
    }
}