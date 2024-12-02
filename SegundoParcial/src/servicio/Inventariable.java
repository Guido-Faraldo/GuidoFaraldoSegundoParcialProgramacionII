package servicio;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Inventariable<T extends CSVSerializable> {
    
    void agregar(T item);
    
    T obtener(int indice);
    
    void eliminar(int indice);
    
    List<T> filtrar(Predicate<T> predicado);
    
    void ordenar();
    
    void ordenar(Comparator<T> comparador);
    
    void guardarEnArchivo(String path);
    
    void cargarDesdeArchivo(String path);
    
    void guardarEnCSV(String path);
    
    void cargarDesdeCSV(String path, Function<String, T> funcion);
    
    void paraCadaElemento(Consumer<T> tarea);
}
