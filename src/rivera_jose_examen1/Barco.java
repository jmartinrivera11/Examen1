
package rivera_jose_examen1;

import java.util.Calendar;

public abstract class Barco {
    
    protected String nombre;
    protected Calendar fecha;
    
    public Barco(String name) {
        nombre = name;
        fecha = Calendar.getInstance();
    }
    
    public final String getNombre() {
        return nombre;
    }
    
    public final Calendar getFecha() {
        return fecha;
    }
    
    @Override
    public String toString() {
        return "Nombre del barco: " + nombre;
    }
    
    public abstract void agregarElemento();
    public abstract double vaciarCobrar();
    public abstract double precioElemento();
}
