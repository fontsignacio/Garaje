package controladores;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import vista.VentanaAutos;

public class ControladorAutos {
    static DB db = new DB();
    static VentanaAutos ventana = new VentanaAutos();
    
    public static void mostrar(){
        ventana.setVisible(true);
        cargarTabla();
    }
    public static void cargarTabla(){
        DefaultTableModel datos = (DefaultTableModel) ventana.getjTable1().getModel();
        datos.setNumRows(0);
        for (Autos p : db.getAutos() ) {
            Object[] fila = new Object[4];
            fila[0] = p.getPatente();
            fila[1] = p.getTipo();
            fila[2] = p.getHora();
            fila[3] = p.getPrecio();
            datos.addRow(fila);
        }
    }
    
    public static void botonAgregar() {        
        String patente = ventana.getPatente().getText();
        int tipo = Integer.parseInt(ventana.getTipo().getText());
        db.agregarAuto(patente, tipo);
        cargarTabla();
    }
    
    
    public static void botonBuscar(){
        String patente = ventana.getPatente().getText();
        Autos p = db.buscar(patente);        
        ventana.getTipo().setText( Integer.toString(p.getTipo()) );
        ventana.getHora().setText( Integer.toString(p.getHora()) );
        ventana.getPrecio().setText( Integer.toString(p.getPrecio()) );
    }
    
    public static void botonModificar() {
        String patente = ventana.getPatente().getText();
        int tipo = Integer.parseInt(ventana.getTipo().getText());
        int hora = Integer.parseInt(ventana.getHora().getText());
        int precio = Integer.parseInt(ventana.getPrecio().getText());
        db.modificarAuto(patente,tipo, hora, precio);
        cargarTabla();
    }
      
    public static void botonEliminar() {
        String patente = ventana.getPatente().getText();    
        db.eliminarAuto(patente);
        cargarTabla(); 
    }
       
    public static void botonTotal(){
        //Autos p = new Autos();
        int total = db.total();
        ventana.getTotal().setText( Integer.toString(total) );
        
    }
}
