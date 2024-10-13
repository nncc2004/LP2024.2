
import java.util.Scanner;

public class NoJavaSky{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        MapaGalactico mapa = new MapaGalactico();
        mapa.generadorPlaneta();
        Jugador jugador = new Jugador();
        Nave nave = new Nave();

        boolean Ganar = false;
        int opcion = -1;
        String linea = "\n\n-";
        for (int i = 0; i < 100; i++) {
            linea = linea+"-";
        }
        while(!Ganar && opcion != 0){
            
            
            System.out.println(linea);
            if(jugador.getEstado().equals("orbita")){
                System.out.println("Actualmente te encuentras orbitando el planeta ("+ mapa.getPosicion()+") de tipo " + mapa.getNombrePlanetaActual());
                System.out.println("Tus opciones son:");
                System.out.println("1. Obtener infomacion del planeta orbitado");
                System.out.println("2. Visitar planeta");
                System.out.println("3. Ver mapa Galactico");
                System.out.println("4. Viajar a otro planeta");
                System.out.println("5. Descubrir nuevo planeta");
                System.out.println("6. Recargar combustible");
                System.out.println("7. Recargar energia traje");
                System.out.println("8. Ver informacion personal y de la nave");
                System.err.print("Ingrese su respuesta: ");
                opcion = scan.nextInt();
                System.err.println("");

                switch (opcion) {
                    case 0 -> {
                        System.out.println("Hasta pronto!");
                    }
                    case 1 -> {
                        Planeta actual = mapa.getPlanetaActual();
                        System.out.println("Las caracteristicas generales del planeta son las siguientes:");
                        System.out.println("Es un planeta de tipo " + actual.getClass().getSimpleName() + " de radio "+ actual.getRadio() + " metros.");
                        System.out.println("Cantidad de Flores de Sodio: "+ actual.getFloresDeSodio());
                        System.out.println("Cantidad de Cristales de Hidrogeno: " + actual.getCristalesHidrogeno());


                        switch (actual) {
                            case Volcanico volcanico -> {
                                System.out.println("Cantidad de platino: " + volcanico.getPlatino());
                                System.out.println("Temperatura: " + volcanico.getTemperatura() + " grados");
                            }
                            case Radiactivo radiactivo -> {
                                System.out.println("Cantidad de uranio: " + radiactivo.getUranio());
                                System.out.println("Radiacion: " + radiactivo.getRadiacion());
                            }
                            case Helado helado -> System.err.println("Temperatura: " + helado.getTemperatura() + " grados");
                            case Oceanico oceanico -> System.err.println("Profundidad: " + oceanico.getProfundidad() + " metros");
                            default -> System.out.println("Tipo de planeta desconocido.");
                        }
                        
                        System.out.println("Presione alguna tecla para continuar...");
                        scan.next();
                    }
                    default ->{
                        System.out.println("Ingrese un valor valido");
                    }                    
                }


            }else {
                System.out.println("Te encuentras parado en el planeta ("+ mapa.getPosicion()+") de tipo " + mapa.getNombrePlanetaActual());
                System.out.println("Tus opciones son:");
                System.out.println("1. Volver a la orbita");
                System.out.println("2. Obtener informacion del planeta actual");
                System.out.println("3. Extraer recursos");
                System.out.println("4. Buscar asentamientos");
                opcion = scan.nextInt();
                switch (opcion) {
                    case 1 -> {

                    }
                    default ->{
                        System.out.println("Ingrese un valor valido");
                    }                    
                }
            }

            
        }
    
    }
}