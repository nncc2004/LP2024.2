import java.util.Scanner;

public class procesamiento {
    Scanner scan = new Scanner(System.in);
    public void mostrarDetallePlaneta(Planeta planeta){
        System.out.println("Las caracteristicas generales del planeta son las siguientes:");
        System.out.println("    - Es un planeta de tipo " + planeta.getClass().getSimpleName() + " de radio "+ planeta.getRadio() + " metros.");
        System.out.println("    - Cantidad de Flores de Sodio: "+ planeta.getFloresDeSodio());
        System.out.println("    - Cantidad de Cristales de Hidrogeno: " + planeta.getCristalesHidrogeno());


        switch (planeta) {
            case Volcanico volcanico -> {
                System.out.println("    - Cantidad de platino: " + volcanico.getPlatino());
                System.out.println("    - Temperatura: " + volcanico.getTemperatura() + " grados");
            }
            case Radiactivo radiactivo -> {
                System.out.println("    - Cantidad de uranio: " + radiactivo.getUranio());
                System.out.println("    - Radiacion: " + radiactivo.getRadiacion());
            }
            case Helado helado -> System.err.println("    - Temperatura: " + helado.getTemperatura() + " grados");
            case Oceanico oceanico -> System.err.println("    - Profundidad: " + oceanico.getProfundidad() + " metros");
            default -> System.out.println("    - Te encuentras orbitando el centro galactico! No sabemos nada sobre el");
        }
    }

    public void infoNaveJugador(Nave nave, Jugador jugador){
        System.out.println("Informacion nave: ");
        System.out.println("    - Combustible: "+nave.getUnidadesCombustible());
        System.out.println("    - Eficiencia: "+nave.geteficienciaPropulsor());
        System.out.println("\nInformacion traje: ");
        System.out.println("    - Energia proteccion traje: "+jugador.getUnidadesEnergiaProteccion());
        System.out.println("    - Eficiencia traje: "+jugador.getEficienciaEnergiaProteccion());
        System.out.println("\nInventario:");
        System.out.println("    - Unidades de Sodio: "+jugador.getSodio());
        System.out.println("    - Unidades de Hidrogeno: "+jugador.getHidrogeno());
        System.out.println("    - Unidades de Uranio: "+jugador.getUranio());
        System.out.println("    - Unidades de Platino: "+jugador.getPlatino());
    }
    
    
    public int mostrarMateriales(Planeta planeta){
        System.out.println("Estos son los recursos disponibles: ");
        System.out.println("    1. Cristales de hidrogeno");

        switch (planeta.getClass().getSimpleName()) {
            case "Volcanico"-> {
                System.out.println("    2. Platino");
            }
            case "Radiactivo" -> {
                System.out.println("    2. Flores de Sodio");
                System.out.println("    3. Uranio");
            }
            case "Helado" -> {
                System.out.println("    2. Flores de Sodio");
            }
            case "Oceanico"-> {
                System.out.println("    2. Flores de Sodio");
            }
            default -> System.out.println("    - Te encuentras en el centro galactico! No sabemos nada sobre el");
        }
        System.out.print("Ingresa el recurso a extraer: ");
        int recurso = scan.nextInt();
        return recurso;
    }
    
    public void guardarMaterialInventario(int cantExtraccion, Jugador jugador, int recurso, Planeta planeta){
        switch (planeta.getClass().getSimpleName()) {
            case "Volcanico"-> {
                switch (recurso){
                    case 1 ->{ //Hidrogeno
                        jugador.setHidrogeno(jugador.getHidrogeno() + cantExtraccion);
                    }
                    case 2-> { //Platino
                        jugador.setPlatino(jugador.getPlatino() + cantExtraccion);
                    }
                }
            }
            case "Radiactivo" -> {
                switch (recurso){
                    case 1 ->{ //hidrogeno
                        jugador.setHidrogeno(jugador.getHidrogeno() + cantExtraccion);
                    }
                    case 2-> { //sodio
                        jugador.setSodio(jugador.getSodio() + cantExtraccion);
                    }
                    case 3-> { //Uranio
                        jugador.setUranio(jugador.getUranio() + cantExtraccion);
                    }
                }
            }
            case "Helado" -> {
                switch (recurso){
                    case 1 ->{ //Hidrogeno
                        jugador.setHidrogeno(jugador.getHidrogeno() + cantExtraccion);
                    }
                    case 2-> { //Sodio
                        jugador.setSodio(jugador.getSodio() + cantExtraccion);
                    }
                }
            }
            case "Oceanico"-> {
                switch (recurso){
                    case 1 ->{ //Hidrogeno
                        jugador.setHidrogeno(jugador.getHidrogeno() + cantExtraccion);
                    }
                    case 2-> { //Sodio
                        jugador.setSodio(jugador.getSodio() + cantExtraccion);
                    }
                }
            }
            default -> System.out.println("    - Te encuentras en el centro galactico! No sabemos nada sobre el");
        }
    }

    public void reiniciar(MapaGalactico mapa, Jugador jugador, Nave nave){
        jugador.reinicio();
        mapa.reinicio();
        nave.reinicio();
    }

    public void separador(){
        for (int i = 0; i < 100; i++) {
            System.out.print("-");
        }
        System.out.println("\n");
    }
    public void limpiar(){
        for (int i = 0; i < 100; i++) {
            System.out.println(" ");
        }
    }
}
