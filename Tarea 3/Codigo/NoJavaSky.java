import java.util.Scanner;


public class NoJavaSky{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        MapaGalactico mapa = new MapaGalactico();
        mapa.generadorPlaneta();
        Jugador jugador = new Jugador();
        Nave nave = jugador.getNave();
        procesamiento pr = new procesamiento();
        int opcion = -1;
        
        for (int i = 0; i < 150; i++) {
            mapa.generadorPlaneta(); //Borrar luego!!
        }
        
        pr.limpiar();
        Planeta actual;
        int tamanioSalto;
        int direccion;
        int respuestaGral;

        while(!(jugador.getVictoria()) && opcion != 0 && !(jugador.getDerrota())){
            actual = mapa.getPlanetaActual();

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
                System.out.println("8. Ver informacion nave, traje e inventario");
                System.out.println("0. Terminar la partida");
                System.err.print("Ingrese su respuesta: ");
                opcion = scan.nextInt();
                System.out.println("");

                switch (opcion) {
                    case 0 -> {
                    }
                    case 1 -> {
                        pr.mostrarDetallePlaneta(actual);
                        
                        
                    }
                    case 2-> {
                        actual.visitar(jugador);
                    }
                    case 3 -> {
                        System.out.println("Cargando mapa galactico....");
                        mapa.Mostrar();
                    }
                    case 4 -> {
                        System.out.print("Ingrese la distancia que va a recorrer: ");
                        tamanioSalto = scan.nextInt();
                        System.out.print("Ingrese la direccion del salto (1: negativo; 2; positivo): ");
                        direccion= scan.nextInt();
                        //mapa.viajar(nave, tamanioSalto, direccion);
                        nave.viajarPlaneta(mapa, direccion, tamanioSalto);
                    }
                    case 5 -> {
                        mapa.descubrirSiguiente();
                    }
                    case 6 -> {
                        System.out.println("Para recargar combustible se debe usar hidrogeno.");
                        System.err.println("Unidades disponibles: " + jugador.getHidrogeno());
                        System.out.print("Cuantas desea usar? ");
                        respuestaGral = scan.nextInt();
                        while(respuestaGral < 0 || respuestaGral > jugador.getHidrogeno()){
                            System.out.print("Ingrese un valor entre 0 y " + jugador.getHidrogeno());
                            respuestaGral = scan.nextInt();
                        }
                        jugador.setHidrogeno(jugador.getHidrogeno()-respuestaGral);
                        nave.recargarPropulsores(respuestaGral);
                        System.out.println("Nueva cantidad de combustible en la nave: "+nave.getUnidadesCombustible());

                    }
                    case 7 -> {
                        System.out.println("Para recargar la energia del traje se debe usar sodio.");
                        System.err.println("Unidades disponibles: " + jugador.getSodio());
                        System.out.print("Cuantas desea usar? ");
                        respuestaGral = scan.nextInt();
                        while(respuestaGral < 0 || respuestaGral > jugador.getSodio()){
                            System.out.print("Ingrese un valor entre 0 y " + jugador.getSodio());
                            respuestaGral = scan.nextInt();
                        }
                        jugador.recargarEnergiaProteccion(respuestaGral);
                        System.out.println("Nueva cantidad de energia de proteccion en el traje: "+ jugador.getUnidadesEnergiaProteccion());
                    }
                    case 8 -> {
                        pr.infoNaveJugador(nave, jugador);
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
                System.out.println("0. Terminar la partida");
                System.err.print("Ingrese su respuesta: ");
                opcion = scan.nextInt();
                switch (opcion) {
                    case 0 -> {
                    }
                    case 1 -> {
                        if(actual.salir()){
                            jugador.setEstado("orbita");
                        }
                    }
                    case 2 -> {
                        pr.mostrarDetallePlaneta(actual);
                        
                    }
                    case 3 -> {                        
                        int recurso = pr.mostrarMateriales(actual);
                        int cantExtraccion = actual.extraerRecursos(recurso);
                        float consumoEnergia = actual.calcularConsumoEnergia();
                        jugador.reducirEnergiaProteccion(cantExtraccion, consumoEnergia);
                        pr.guardarMaterialInventario(cantExtraccion, jugador, recurso, actual);
                        if (cantExtraccion != -1){
                            if(jugador.getUnidadesEnergiaProteccion() <= 0){
                            System.out.println("Oh No! Te has quedado sin energia de proteccion en tu traje!. ");
                            System.out.println("La nave te ha llevado al inicio, pero has perdido tu inventario.");
                            System.out.println("Se ha reiniciado tu energia de proteccion y combustible, pero se han guardado las mejoras hechas a las eficiencias.\n");
                            pr.reiniciar(mapa, jugador, nave);
                        } else{
                            System.out.println("Todo extraido correctamente. Se ha almacenado el material en tu inventario.");
                            System.out.println("Energia del traje: "+jugador.getUnidadesEnergiaProteccion());
                            }

                        }
                        


                    }
                    case 4 ->{
                        switch (actual) {
                            case Helado helado -> {
                                helado.visitarAsentamientos(jugador);
                            }
                            case Oceanico oceanico -> {
                                oceanico.visitarAsentamientos(jugador);
                            }
                            
                            default -> System.out.println("    - Solos los planetas helados y oceanicos tienen asentamientos!");
                        }
                    }
                    default ->{
                        System.out.println("Ingrese un valor valido");
                    }                    
                }
            }
            pr.enterContinuar();
            pr.limpiar();
            
            
        }    
        scan.close();
        System.out.println("Hasta pronto!");
    }

}