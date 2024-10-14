import java.util.Random;
import java.util.Scanner;

public class  Oceanico extends Planeta implements tieneAsentamientos{
    private int profundidad;

    public Oceanico(){
        Random aleatorio = new Random();

        int radio = aleatorio.nextInt(1000000 - 10000 + 1) + 10000;

        this.profundidad = aleatorio.nextInt(1000 - 30 + 1) + 30;
        
        long cristalesHidrogeno = (long)(0.2 * 4 * Math.PI * Math.pow(radio, 2));
        long floresDeSodio = (long)(0.65 * 4 * Math.PI * Math.pow(radio, 2));

        super.setRadio(radio);
        super.setCristalesHidrogeno(cristalesHidrogeno);
        super.setFloresDeSodio(floresDeSodio);
    }

    
    //Getters y setters
    public int getProfundidad() {
        return profundidad;
    }

    //Metodos
    @Override
    public float calcularConsumoEnergia() {
        return (float)( 0.002 * Math.pow(profundidad, 2));
    }


    @Override
    public void visitarAsentamientos(Jugador jugador){
        Scanner scan = new Scanner(System.in);
        System.out.println("Bienvenido al asentamiento de este planeta. En que podemos ayudarte hoy?");
        System.err.println("1. Comprar piezas para mejorar eficiencia traje espacial");
        System.out.println("2. Comprar pezas para mejorar eficiencia nave espacial");
        System.out.println("0. Salir del asentamiento");
        System.out.print("Ingrese una opcion: ");
        int respuesta = scan.nextInt();

        switch (respuesta) {
            case 1-> {
                System.err.println("Por un 5% extra de eficiencia en tu traje te cobraremos 100.000 unidades de Platino");
                System.out.print("Aceptas? (1: si | 2 : no)");
                respuesta = scan.nextInt();
                while( respuesta != 0 && respuesta != 1){
                    respuesta = scan.nextInt();
                    
                }
                if(respuesta == 0){
                    System.out.println("Hasta pronto!");
                    return;
                } 
                if(jugador.getPlatino() >= 100000){
                    System.out.println("Se ha procedido con la compra. Ya puedes revisar los efectos en tu menu.");
                    jugador.setPlatino(jugador.getPlatino() - 100000);
                    jugador.aumentarEficienciaTraje((float)0.05);
                    
                }else {
                    System.out.println("Veo que no te alcanza para comprar la mejora deseada.");
                    System.out.println("Explora mundos, extrae recursos y luego vuelve!");
                }

            }
            case 2 ->{
                Nave nave = jugador.getNave();

                System.err.println("Por un 5% extra de eficiencia en tu nave te cobraremos 150.000 unidades de Platino");
                System.out.print("Aceptas? (1: si | 2 : no)");
                respuesta = scan.nextInt();
                while( respuesta != 0 && respuesta != 1){
                    respuesta = scan.nextInt();
                    
                }
                if(respuesta == 0){
                    System.out.println("Hasta pronto!");
                    return;
                } 
                if(jugador.getUranio() >= 150000){
                    System.out.println("Se ha procedido con la compra. Ya puedes revisar los efectos en tu menu.");
                    jugador.setUranio(jugador.getUranio() - 150000);
                    nave.aumentarEficienciaNave((float)0.05);
                    
                }else {
                    System.out.println("Veo que no te alcanza para comprar la mejora deseada.");
                    System.out.println("Explora mundos, extrae recursos y luego vuelve!");
                }

            }
            case 0 -> {
                System.out.println("Hasta pronto!");
                return;
            }
            default -> {

            }
                

        }

    }
}