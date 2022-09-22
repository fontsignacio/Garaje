package modelo;
import modelo.Autos;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DB {

    private String url = "jdbc:mariadb://127.0.0.1/garaje";
    private String usr = "root";
    private String clave = "root";
    
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,usr,clave);
    }
    
    private void ejecutar(String sql){
        try {
            Connection c = getConnection();
            Statement s = c.createStatement();
            s.execute(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(sql);               
    }  
    
    public void agregarAuto(String patente, int tipo){
        String sql = "INSERT INTO auto(patente,tipo) values ('%1','%2')";      
        sql = sql.replace("%1", patente);
        sql = sql.replace("%2", Integer.toString(tipo));
        ejecutar(sql);
    }
    
    public void agregarAuto(Autos p){
        agregarAuto(p.getPatente(),p.getTipo());
    }
    
    public void modificarAuto(String patente, int tipo, int hora, int precio){
        String sql = "UPDATE auto set hora= '%1', precio='%2' where patente='%3'";
        sql = sql.replace("%1", Integer.toString(hora));
        sql = sql.replace("%3", patente);
                
        if (tipo == 1){ //motos
            sql = sql.replace("%2", Integer.toString(300*hora));
        }
        if (tipo == 2){ //autos
            sql = sql.replace("%2", Integer.toString(500*hora));
        }
        if (tipo == 3){ //camionetas
            sql = sql.replace("%2", Integer.toString(700*hora));
        }       
        ejecutar(sql);
    }
    public void modificarAuto(Autos p){
        modificarAuto(p.getPatente(),p.getTipo(),p.getHora(),p.getPrecio());    
    }
    
    public void eliminarAuto(String patente){
        String sql = "DELETE FROM auto WHERE patente= '%1'";
        sql = sql.replace("%1", patente);
        ejecutar(sql);    
    }
    
    public Autos buscar(String patente){
        Autos p = new Autos();
        try{
            Connection c = getConnection();
            String sql = "SELECT * from auto where patente= '%1'";
            sql = sql.replace("%1", patente);
            ResultSet res = c.createStatement().executeQuery(sql);            
            if (res.next()){
                p.setPatente(patente);
                p.setTipo(res.getInt("tipo"));
                p.setHora(res.getInt("hora"));
                p.setPrecio(res.getInt("precio"));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return p;
    }
    
    public int total(){ 
        int total = 0;
        try{
            Connection c = getConnection();
            String sql = "SELECT sum(precio) as total from auto ";
            ResultSet res = c.createStatement().executeQuery(sql);
            if (res.next()){
                if (res.wasNull()){
                    total = 0;
                }
                else{
                    total = res.getInt("total");
                }                
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return total;
    }
     
    
    public ArrayList<Autos> getAutos(){
        ArrayList<Autos> autos = new ArrayList<>();
        try{
            Connection c = getConnection();
            ResultSet res = 
                    c.createStatement()
                            .executeQuery("select * from auto");
            while (res.next()){
                Autos p = new Autos();
                p.setPatente(res.getString("patente"));
                p.setTipo(res.getInt("tipo"));
                p.setHora(res.getInt("hora"));
                p.setPrecio(res.getInt("precio"));
                autos.add(p);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return autos;
    }
            
}
