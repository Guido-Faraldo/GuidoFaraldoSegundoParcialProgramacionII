package servicio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import modelo.Animal;

public class Zoologico<T extends CSVSerializable> implements Inventariable<T>{
    
    List<T> animales = new ArrayList<>();

    @Override
    public void agregar(T item) {
        if (item == null){
            throw new NullPointerException("No se deben agregar nulos en las listas");
        }
        animales.add(item);
    }
    
    private void validarIndice(int indice){
        if (indice < 0 || indice >= animales.size()){
            throw new IndexOutOfBoundsException("Indice ivalido");
        }
    }
    
    @Override
    public T obtener(int indice) {
        validarIndice(indice);
        return animales.get(indice);
    }

    @Override
    public void eliminar(int indice) {
        validarIndice(indice);
        animales.remove(indice);
    }
    
    @Override
    public List<T> filtrar(Predicate<T> predicado) {
        List<T> toReturn = new ArrayList<>();
        for (T a : animales){
            if (predicado.test(a)){
                toReturn.add(a);
            }
        }
        return toReturn;
    }

    @Override
    public void ordenar() {
        ordenar((Comparator<T>) Comparator.naturalOrder());
    }

    @Override
    public void ordenar(Comparator<T> comparador) {
        animales.sort(comparador);
    }

    @Override
    public void guardarEnArchivo(String path) {
        try (FileOutputStream archivo = new FileOutputStream(path);
            ObjectOutputStream salida = new ObjectOutputStream(archivo){
            }){
            
            salida.writeObject(animales);

        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void cargarDesdeArchivo(String path) {
        
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(path)){
            }){
            
            animales = (List<T>)input.readObject();
            
        } catch (IOException | ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void guardarEnCSV(String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
            bw.write("id,nombre,especie,alimentacion\n");
            for (T a : animales){
                bw.write(a.toCSV() + "\n");
            }
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void cargarDesdeCSV(String path, Function<String, T> funcion) {
        animales.clear();
        
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            
            bf.readLine();
            String linea;
            while ((linea = bf.readLine()) != null){
                animales.add(funcion.apply(linea));
            }
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void paraCadaElemento(Consumer<T> tarea){
        for (T a: animales){
            tarea.accept(a);
        }
    }
    
}
