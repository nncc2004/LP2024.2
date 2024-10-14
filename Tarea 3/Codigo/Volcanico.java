import java.util.Random;
import java.util.Scanner;

public class Volcanico extends Planeta{
    private int temperatura;
    private long platino;

    public Volcanico(){
        Random aleatorio = new Random();

        int radio = aleatorio.nextInt(100000 - 1000 + 1) + 1000;
        this.temperatura = aleatorio.nextInt(256 - 120 + 1) + 120;

        long cristalesHidrogeno = (long)(0.3 * 4 * Math.PI * Math.pow(radio, 2));
        this.platino =(long) ((0.25 * 4 * Math.PI * Math.pow(radio, 2)) - (20.5 * Math.pow(temperatura, 2)));
        super.setRadio(radio);
        super.setCristalesHidrogeno(cristalesHidrogeno);
        super.setFloresDeSodio(0);
    }

    //Getters y setters
    public long getPlatino() {
        return platino;
    }

    public int getTemperatura() {
        return temperatura;
    }

    //metodos
    @Override
    public float calcularConsumoEnergia() {
        return (float) 0.08 * temperatura;
    }

    @Override
    public int extraerRecursos(int tipo){
        long cristalesHidrogeno = super.getCristalesHidrogeno();
        Scanner scan = new Scanner(System.in);
        int cantExtraccion;
        switch (tipo) {
            case 1 ->{ // Cristales de hidrogeno
                System.out.println("Se ha seleccionado la extraccion de los cristales de hidrogeno. En el planeta hay "+cristalesHidrogeno+" cistrales de hidrogeno.");
                System.out.println("Cuantas flores deseas extraer?");
                cantExtraccion = scan.nextInt();
                while (cantExtraccion > cristalesHidrogeno && cantExtraccion < 0) { 
                    System.out.println("Ingresa un valor valido. (Entre 0 y la cantidad de cristales)");
                    cantExtraccion = scan.nextInt();
                }
                super.setCristalesHidrogeno(cristalesHidrogeno - cantExtraccion);
                return cantExtraccion;
                }
            case 2 -> { //Platino
                System.out.println("Se ha seleccionado la extraccion de platino. En el planeta hay "+platino+" unidades.");
                System.out.println("Cuantas unidades de platino deseas extraer?");
                cantExtraccion = scan.nextInt();
                while (cantExtraccion > platino && cantExtraccion < 0) { 
                    System.out.println("Ingresa un valor valido. (Entre 0 y la cantidad de platino)");
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