package modelo;

import java.io.Serializable;
import servicio.CSVSerializable;

public class Animal implements Comparable<Animal>, Serializable, CSVSerializable{
    
    private static final long SerialVersionUID = 1L;
    private int id;
    private String nombre;
    private String especie;
    private TipoAlimentacion tipoAlimentacion;

    public Animal(int id, String nombre, String especie, TipoAlimentacion tipoAlimentacion) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.tipoAlimentacion = tipoAlimentacion;
    }

    public TipoAlimentacion getTipoAlimentacion() {
        return tipoAlimentacion;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", nombre=" + nombre + ", especie=" + especie + ", tipoAlimentacion=" + tipoAlimentacion + '}';
    }

    @Override
    public int compareTo(Animal a) {
        return Integer.compare(this.id, a.id);
    }
    
    @Override
    public String toCSV(){
        return id + "," + nombre + "," +especie + "," + tipoAlimentacion.toString();
    }
    
    public static Animal fromCSV(String animalCSV){
        Animal a = null;
        String[] valores = animalCSV.split(",");
            if (valores.length == 4){
                int id = Integer.parseInt(valores[0]);
                String nombre = valores[1];
                String especie = valores[2];
                TipoAlimentacion sector = TipoAlimentacion.valueOf(valores[3]);
                a = new Animal(id, nombre, especie, sector);
            }
        return a;
    }
}
