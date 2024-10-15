
import java.util.Random;
import java.util.Scanner;

public class Helado extends Planeta implements tieneAsentamientos {
    private int temperatura;

    //constructor
    public Helado(){
        /*
        Input: No recibe parametros
        Este es el constructor de la clase Helado, subclase de planeta. Segenera el radio según los parametros dados
        en las instrucciones y a partir de ahí se generan las flores de sodio  y los cristales de hidrogeno.
        Con los setters de la superclase se pasa el valor de los parametros.

        Output: No genera output
        */
        Random aleatorio = new Random();

        int radio = aleatorio.nextInt(1000000 - 1000 + 1) + 1000;
        int cristalesHidrogeno = (int)(0.65 * 4 * Math.PI * Math.pow(radio, 2));
        int floresDeSodio = (int)(0.35 * 4 * Math.PI * Math.pow(radio, 2));

        this.temperatura = aleatorio.nextInt(120 - 30 + 1) - 120;

        super.setRadio(radio);
        super.setCristalesHidrogeno(cristalesHidrogeno);
        super.setFloresDeSodio(floresDeSodio);

    }

    //Getters y setters
    public int getTemperatura() {
        /*
        Input: No recibe parametros
        Funcionalidad: Retorna la temperatura para poder acceder a ella luego.
        Output: temperatura (int)
        */
        return temperatura;
    }

    //Metodos
    @Override
    public float calcularConsumoEnergia() {
         /*
            Input: No recibe parametros
            Funcionalidad: Calcula y retorna el consumo de energia del planeta segun la formula dada
            Output: retorna el valor del calculo del consumo de energia

        */
        return (float) 0.15 * Math.abs(temperatura);
    }

    @Override
    public void visitarAsentamientos(Jugador jugador){
        /*
        Input: Rebide un objeto de la clase jugador
        funcionalidad: Esta es la funcion para que el jugador pueda acceder a los asentamientos y comprar mejoras.
        Se inicia preguntando que se quiere comprar y luego se muestra informacion al respecto, dando a elegir al usuario
        si quiere proseguir con la compra o no
        */
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
                System.out.print("Aceptas? (1: si | 2 : no) ");
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
                System.out.print("Aceptas? (1: si | 2 : no) ");
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