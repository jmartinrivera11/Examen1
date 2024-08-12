
package rivera_jose_examen1;

public class BarcoPesquero extends Barco {
    
    private int pecesCapturados;
    private TipoPesquero tipoBarco;

    public BarcoPesquero(String nombre, TipoPesquero tipoBarco) {
        super(nombre);
        pecesCapturados = 0;
        this.tipoBarco = tipoBarco;
    }
    
    @Override
    public void agregarElemento() {
        pecesCapturados += 1;
    }

    @Override
    public double vaciarCobrar() {
        double total = tipoBarco.getPrecio() * pecesCapturados;
        pecesCapturados = 0;
        return total;
    }
    
    @Override
    public double precioElemento() {
        return tipoBarco.getPrecio();
    }
    
    @Override
    public String toString() {
        return super.toString() + " - Tipo de barco: " + tipoBarco 
                + " - Peces capturados: " + pecesCapturados;
    }
}
