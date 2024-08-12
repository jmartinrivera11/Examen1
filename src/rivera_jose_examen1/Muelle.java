
package rivera_jose_examen1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

public class Muelle extends JFrame {
    
    private ArrayList<Barco> Barcos;
    
    public Muelle() {
        Barcos = new ArrayList<>();
        crearFrame();
    }
    
    private void crearFrame() {
        setTitle("Muelle");
        setSize(500, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton agregarBarco = new JButton("Agregar barco");
        agregarBarco.addActionListener(e -> agregarBarco());
        JButton agregarElemento = new JButton("Agregar elemento");
        agregarElemento.addActionListener(e -> agregarElemento());
        
        JButton vaciarBarco = new JButton("Vaciar y cobrar total");
        vaciarBarco.addActionListener(e -> vaciarBarco());
        
        JButton barcosDesde = new JButton("Listar barcos");
        barcosDesde.addActionListener(e -> listarBarcos());
        
        panel.add(agregarBarco);
        panel.add(agregarElemento);
        panel.add(vaciarBarco);
        panel.add(barcosDesde);
        add(panel, BorderLayout.CENTER);
    }
    
    private void agregarBarco() {
        String tipo = JOptionPane.showInputDialog(this, "Tipo de barco (PESQUERO o PASAJERO):");
        String nombre = JOptionPane.showInputDialog(this, "Nombre del barco:");
        
        if (buscarBarco(nombre).isPresent()) {
            JOptionPane.showMessageDialog(this, "El nombre de barco ya existe");
            return;
        }
        
        if ("PESQUERO".equalsIgnoreCase(tipo)) {
            TipoPesquero tipoPesquero = (TipoPesquero) JOptionPane.showInputDialog(this,
                    "Seleccione el tipo:",
                    "Tipo de barco",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    TipoPesquero.values(),
                    TipoPesquero.PEZ);
            Barcos.add(new BarcoPesquero(nombre, tipoPesquero));
            
        } else if ("PASAJERO".equalsIgnoreCase(tipo)) {
            int cantidadPasajeros = Integer.parseInt(JOptionPane.showInputDialog(this, "Capacidad maxima del barco:"));
            double precio = Double.parseDouble(JOptionPane.showInputDialog(this, "Precio de boleto:"));
            Barcos.add(new BarcoPasajero(nombre, cantidadPasajeros, precio));
            
        } else {
            JOptionPane.showMessageDialog(this, "No existe dicho tipo de barco");
        }
    }
    
    private void agregarElemento() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del barco:");
        Optional<Barco> barco = buscarBarco(nombre);
        
        barco.ifPresentOrElse(
                Barco::agregarElemento,
                () -> JOptionPane.showMessageDialog(this, "El barco no fue encontrado")
        );
    }
    
    private void vaciarBarco() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del barco:");
        Optional<Barco> barco = buscarBarco(nombre);

        barco.ifPresentOrElse(b -> {
            double totalCobrado = b.vaciarCobrar();
            JOptionPane.showMessageDialog(this, "Total: $" + totalCobrado);
            if (b instanceof BarcoPasajero barcoPasajero) {
                barcoPasajero.listarPasajeros();
            }
        }, () -> JOptionPane.showMessageDialog(this, "El barco no fue encontrado"));
    }
    
    private void listarBarcos() {
        int year = Integer.parseInt(JOptionPane.showInputDialog(this, "Año:"));
        listarBarcos(0, year);
    }
    
    private void listarBarcos(int index, int año) {
        if (index < Barcos.size()) {
            Barco barco = Barcos.get(index);
            
            if (barco.fecha.get(Calendar.YEAR) >= año) {
                String msj = barco.getNombre() + " - " + barco.getFecha();
                JOptionPane.showMessageDialog(this, msj);
            }
            listarBarcos(index + 1, año);
        }
    }
    
    private Optional<Barco> buscarBarco(String nombre) {
        for (Barco barco : Barcos) {
            if (barco.getNombre().equalsIgnoreCase(nombre)) {
                return Optional.of(barco);
            }
        }
        return Optional.empty();
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Muelle muelle = new Muelle();
            muelle.setVisible(true);
        });
    }
}
