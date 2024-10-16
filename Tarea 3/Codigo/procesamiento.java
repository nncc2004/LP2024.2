import java.io.IOException;
import java.util.Scanner;

public class procesamiento {
    Scanner scan = new Scanner(System.in);

    public void mostrarDetallePlaneta(Planeta planeta){
        /*
            Input: Recibe un objeto de clase planeta
            Funcionalidad: Con los getters del planeta, obtiene y muestra por pantalla diferentes atributos del planeta. Primero
            muestra los genrales y luego segun el tipo de planeta muestra los diferentes atributos especificos de cada uno. 
            Output: No genera retorno

        */
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
        /*
            Input: Recibe un objeto de tipo Nave y uno de tipo Jugador. 
            Funcionalidad: Muestra por pantalla la informacion obtenida con los getters de la nave y del jugador. 
            Output: No genera retorno

        */

        System.out.println("Informacion nave: ");
        System.out.println("    - Combustible: "+nave.getUnidadesCombustible());
        System.out.println("    - Eficiencia: "+nave.geteficienciaPropulsor());
        System.out.println("\nInformacion traje: ");
        System.out.println("    - Energia proteccion traje: "+jugador.getUnidadesEnergiaProteccion());
        System.out.println("    - Eficiencia traje: "+jugador.getEficienciaEnergiaProteccion());
        System.out.println("    - Vidas restantes: "+jugador.getVidas());
        System.out.println("\nInventario:");
        System.out.println("    - Unidades de Sodio: "+jugador.getSodio());
        System.out.println("    - Unidades de Hidrogeno: "+jugador.getHidrogeno());
        System.out.println("    - Unidades de Uranio: "+jugador.getUranio());
        System.out.println("    - Unidades de Platino: "+jugador.getPlatino());
    }
    
    public int mostrarMateriales(Planeta planeta){
        /*
            Input: Recibe un objeto de tipo planeta
            Funcionalidad: Segun el planeta muestra los diferentes recursos que va a tener dispoible y da al usuario a escoger una opcion
            Output: Retorna un entero que representa el tipo de recurso a extraer.

        */

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
        /*
            Input: Recibe parametros de tipo int (x2), Jugador y planeta
            Funcionalidad: Es la funcion encargada de, una vez extraidos los recuross, almacenar la misma cantidad en el inventario
            del jugador segun corresponda. Lo primer oque hace es verificar en que tipo de planeta esta el jugador, para que con el entero que
            representa el tipo de recurso, sepa de que recurso se tiene que guardar en que atributo del jugador. La cantidad representa la cantidad
            extraida.
            Output: No genera retorno

        */
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
        /*
            Input: Recibe objetos de tipo MapaGalactico, Jugador y Nave
            Funcionalidad: Llama a las funciones de reinicio de cada una para reiniciar los valores.
            Se usa cuando el jugador pierde la energia de su traje 
            Output: No genera retorno

        */
        jugador.reinicio();
        mapa.reinicio();
        nave.reinicio();
    }

    public void limpiar(){
        /*
            Input: No recibe parametros
            Funcionalidad: Dependiendo del sistema operativo genera uno u otro comando para limpiar lo visible de la consola
            Output: No genera retorno

        */
        try {
            if(System.getProperty("os.name").contains("Windows")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException ex) {
        }

    }

    public void enterContinuar(){
        /*
            Input: No recibe parametros
            Funcionalidad: Simplemente meustra el mensaje y espera un retorno por consola de cualquier tipo.
            Output: No genera retorno

        */
        System.out.println("Presione 'enter' para continuar...");
        try {
            System.in.read();
        } catch (IOException e) {
        }
    }

}
