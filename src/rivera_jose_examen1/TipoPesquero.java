
package rivera_jose_examen1;

public enum TipoPesquero {
    
    PEZ(25), CAMARON(15), LANGOSTA(35);
    
    public final double precio;
    
    TipoPesquero(double price) {
        precio = price;
    }
    
    public double getPrecio() {
        return precio;
    }
}
