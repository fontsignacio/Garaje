package modelo;

public class Autos {
    private String patente;
    private int tipo;
    private int hora;
    private int precio;
    private int total;

    public Autos(){
        
    }
    
    public Autos(String patente, int tipo, int hora, int precio, int total) {
        this.patente = patente;
        this.tipo = tipo;
        this.hora = hora;
        this.precio = precio;
        this.total = total;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    @Override
    public String toString() {
        return "Autos{" + "patente=" + patente + ", tipo=" + tipo + ", hora=" + hora + ", precio=" + precio + ", total=" + total + '}';
    }
    
    
}
