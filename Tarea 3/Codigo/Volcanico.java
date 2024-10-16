import java.util.Random;
import java.util.Scanner;

public class Volcanico extends Planeta{
    private int temperatura;
    private long platino;

    public Volcanico(){
        /*
        Input: No recibe parametros
        Funcionalidad: Este es el constructor de la clase Radiactivo, subclase de planeta. Se genera el radio según los parametros dados
        en las instrucciones y a partir de ahí se generan el Platino, las flores de sodio  y los cristales de hidrogeno.
        Con los setters de la superclase se pasa el valor de los atributos.

        Output: No genera output
        */
        Random aleatorio = new Random();

        int radio = aleatorio.nextInt(100000 - 1000 + 1) + 1000;
        this.temperatura = aleatorio.nextInt(256 - 120 + 1) + 120;

        int cristalesHidrogeno = (int)(0.3 * 4 * Math.PI * Math.pow(radio, 2));
        this.platino =(long) ((0.25 * 4 * Math.PI * Math.pow(radio, 2)) - (20.5 * Math.pow(temperatura, 2)));
        super.setRadio(radio);
        super.setCristalesHidrogeno(cristalesHidrogeno);
        super.setFloresDeSodio(0);
    }

    //Getters y setters
    public long getPlatino() {
        /*
         Input: No recibe input
         Funcionalidad: Getter del Platino
         Output: retorna el Platino
         */
        return platino;
    }

    public int getTemperatura() {
        /*
         Input: No recibe input
         Funcionalidad: Getter de la temperatura
         Output: retorna la temperatura
         */
        return temperatura;
    }

    //metodos
    @Override
    public float calcularConsumoEnergia() {
        /*
            Input: No recibe parametros
            Funcionalidad: Calcula y retorna el consumo de energia del planeta segun la formula dada
            Output: retorna el valor del calculo del consumo de energia

        */
        return (float) 0.08 * temperatura;
    }

    @Override
    public int extraerRecursos(int tipo){
        /*
         Input: Recibe un entero que representa el tipo de recurso a extraer
         Funcionalidad: Segu el tipo de recurso elegido para extraer (1 = hidrogeno, 2 = Platino), muestra la cantidad disponible en el planeta, 
         y se da a elegir al usuario cuantos quiere extraer.
         se verifica que sea un valor entre 0 y el maximo disponible. Luego resta esa cantidad de recursos del planeta
         Retorno: Retorna la cantidad de recursos extraida, que luego sera necesario para restar la energia al traje. 
         */
        int cristalesHidrogeno = super.getCristalesHidrogeno();
        Scanner scan = new Scanner(System.in);
        int cantExtraccion;
        switch (tipo) {
            case 1 ->{ // Cristales de hidrogeno
                System.out.println("Se ha seleccionado la extraccion de los cristales de hidrogeno. En el planeta hay "+cristalesHidrogeno+" cistrales de hidrogeno.");
                System.out.println("Cuantas flores deseas extraer? ");
                cantExtraccion = scan.nextInt();
                while (cantExtraccion > cristalesHidrogeno && cantExtraccion < 0) { 
                    System.out.println("Ingresa un valor valido. (Entre 0 y la cantidad de cristales) ");
                    cantExtraccion = scan.nextInt();
                }
                super.setCristalesHidrogeno(cristalesHidrogeno - cantExtraccion);
                return cantExtraccion;
                }
            case 2 -> { //Platino
                System.out.println("Se ha seleccionado la extraccion de platino. En el planeta hay "+platino+" unidades.");
                System.out.println("Cuantas unidades de platino deseas extraer? ");
                cantExtraccion = scan.nextInt();
                while (cantExtraccion > platino && cantExtraccion < 0) { 
                    System.out.println("Ingresa un valor valido. (Entre 0 y la cantidad de platino) ");
                    cantExtraccion = scan.nextInt();
                }
                platino = platino - cantExtraccion;
                return cantExtraccion;
            }
            default ->{
                System.out.println("Elige un recurso disponible en el planeta!");
            }
        }
        return -1;
    }
}