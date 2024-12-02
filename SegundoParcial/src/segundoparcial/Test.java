package segundoparcial;


import modelo.Animal;
import modelo.TipoAlimentacion;
import servicio.Zoologico;

public class Test {

    public static void main(String[] args) {
        
       
            // Crear un zoológico de animales
            Zoologico<Animal> zoologico = new Zoologico<>();
            
            zoologico.agregar(new Animal(1, "León", "Panthera leo", TipoAlimentacion.CARNIVORO));
            zoologico.agregar(new Animal(2, "Elefante", "Loxodonta", TipoAlimentacion.HERBIVORO));
            zoologico.agregar(new Animal(3, "Oso", "Ursus arctos", TipoAlimentacion.OMNIVORO));
            zoologico.agregar(new Animal(4, "Zorro", "Vulpes vulpes", TipoAlimentacion.CARNIVORO));
            zoologico.agregar(new Animal(5, "Gorila", "Gorilla gorilla", TipoAlimentacion.OMNIVORO));
            
            
            // Mostrar todos los animales en el zoológico
            System.out.println("Inventario de animales:");
            zoologico.paraCadaElemento(animal -> System.out.println(animal));
            
            
            
            
            // Filtrar animales por tipo de alimentación CARNIVORO
            System.out.println("\nAnimales CARNIVOROS:");
            zoologico.filtrar(a -> a.getTipoAlimentacion().equals(TipoAlimentacion.CARNIVORO)).forEach(animal -> System.out.println(animal));
            
            
            
            // Filtrar animales cuyo nombre contiene "León"
            System.out.println("\nAnimales cuyo nombre contiene 'León':");
            zoologico.filtrar(a -> a.getNombre().equals("León")).forEach(animal -> System.out.println(animal));
            
            
            
            
            // Ordenar animales de manera natural (por id)
            System.out.println("\nAnimales ordenados de manera natural (por id):");
            zoologico.ordenar();
            zoologico.paraCadaElemento(animal -> System.out.println(animal));
            
            
            
            
            
            // Ordenar animales por nombre utilizando un Comparator
            System.out.println("\nAnimales ordenados por nombre:");
            zoologico.ordenar((a1, a2) -> a1.getNombre().compareTo(a2.getNombre()));
            zoologico.paraCadaElemento(animal -> System.out.println(animal));
            
            
            
            // Guardar el zoológico en un archivo binario
            zoologico.guardarEnArchivo("src/data/animales.dat");
            
            
            // Cargar el zoológico desde el archivo binario
            Zoologico<Animal> zoologicoCargado = new Zoologico<>();
            zoologicoCargado.cargarDesdeArchivo("src/data/animales.dat");
            System.out.println("\nAnimales cargados desde archivo binario:");
            zoologicoCargado.paraCadaElemento(animal -> System.out.println(animal));
            
            
            
            // Guardar el zoológico en un archivo CSV
            zoologico.guardarEnCSV("src/data/animales.csv");
            
            
            
            // Cargar el zoológico desde el archivo CSV
            zoologicoCargado.cargarDesdeCSV("src/data/animales.csv", linea -> Animal.fromCSV(linea));
            System.out.println("\nAnimales cargados desde archivo CSV:");
            zoologicoCargado.paraCadaElemento(animal -> System.out.println(animal));
            
       
    
        
    }
    
}
