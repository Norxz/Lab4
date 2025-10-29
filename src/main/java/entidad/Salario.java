package entidad;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Clase auxiliar para empaquetar respuestas de cálculos (suma, promedio)
 * y poder enviarlas como XML o JSON.
 */
@XmlRootElement(name = "calculo")
public class Salario {
    
    private double valor;
    
    // Constructor vacío para JAXB/JSON-B
    public Salario() {
    }
    
    public Salario(double valor) {
        this.valor = valor;
    }
    
    @XmlElement
    public double getValor() {
        return valor;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
}