
package rivera_jose_examen1;

import javax.swing.JOptionPane;

public class BarcoPasajero extends Barco {
    
    private String[] nombrePasajeros;
    private double precioBoleto;
    private int contador;
    
    public BarcoPasajero(String nombre, int cantidad, double precioBoleto) {
        super(nombre);
        nombrePasajeros = new String[cantidad];
        this.precioBoleto = precioBoleto;
        contador = 0;
    }
    
    @Override
    public void agregarElemento() {
        for (String nombrePasajero : nombrePasajeros) {
            if (nombrePasajero == null) {
                String elemento = JOptionPane.showInputDialog("Agregar nombre de pasajero: ");
                nombrePasajeros[contador] = elemento;
            }
        }
        contador += 1;
    }       
    
    @Override
    public double vaciarCobrar() {
        double total = contador * precioBoleto;
        contador = 0;
        return total;
    }
    
    @Override
    public double precioElemento() {
        return precioBoleto;
    }
    
    @Override
    public String toString() {
        return super.toString() + "Cantidad de Pasajeros que compraron boleto: #" 
                + contador;
    }
    
    private void listarPasajeros(int index) {
        String msj = "";
        if (index < contador) {
            msj += nombrePasajeros[index] + "\n";
            listarPasajeros(index + 1);
        }
        JOptionPane.showMessageDialog(null, msj);
    }
    
    public void listarPasajeros() {
        listarPasajeros(0);
    }
}
