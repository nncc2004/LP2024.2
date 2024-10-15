
import java.util.Random;
import java.util.Scanner;

public class Radiactivo extends Planeta{
    private long radiacion;
    private long uranio;

    public Radiactivo(){
        Random aleatorio = new Random();

        int radio = aleatorio.nextInt(100000 - 10000 + 1) + 10000;
        this.radiacion = aleatorio.nextInt(50 - 10 + 1) + 10;

        int cristalesHidrogeno = (int) (0.2 * 4 * Math.PI * Math.pow(radio, 2));
        int floresDeSodio = (int)(0.2 * 4 * Math.PI * Math.pow(radio, 2));

        this.uranio = (long)(0.25 * 4 * Math.PI * Math.pow(radio, 2) * radiacion);

        super.setRadio(radio);
        super.setCristalesHidrogeno(cristalesHidrogeno);
        super.setFloresDeSodio(floresDeSodio);
    }

    //Setters y getters
    public long getUranio() {
        return uranio;
    }

    public long getRadiacion() {
        return radiacion;
    }

    //Metodos
    @Override
    public float calcularConsumoEnergia() {
        return  (float)0.3 * radiacion;
    }

    @Override
    public int extraerRecursos(int tipo){
        int floresDeSodio = super.getFloresDeSodio();
        int cristalesHidrogeno = super.getCristalesHidrogeno();
        Scanner scan = new Scanner(System.in);
        int cantExtraccion;
        switch (tipo) {
            case 1 ->{ //Cristales de hidrogeno
                System.out.println("Se ha seleccionado la extraccion de los cristales de hidrogeno. En el planeta hay "+cristalesHidrogeno+" cistrales de hidrogeno.");
                System.out.println("Cuantos cristales deseas extraer?");
                cantExtraccion = scan.nextInt();
                while (cantExtraccion > cristalesHidrogeno && cantExtraccion < 0) { 
                    System.out.println("Ingresa un valor valido. (Entre 0 y la cantidad de cristales)");
                    cantExtraccion = scan.nextInt();
                }
                super.setCristalesHidrogeno(cristalesHidrogeno - cantExtraccion);
                return cantExtraccion;
                }
            case 2 -> { //Flores de sodio
                System.out.println("Se ha seleccionado la extraccion de las Flores de Sodio. En el planeta hay "+floresDeSodio+" flores de sodio.");
                System.out.println("Cuantas flores deseas extraer?");
                cantExtraccion = scan.nextInt();
                while (cantExtraccion > floresDeSodio && cantExtraccion < 0) { 
                    System.out.println("Ingresa un valor valido. (Entre 0 y la cantidad de flores)");
                    cantExtraccion = scan.nextInt();
                }
                super.setFloresDeSodio(floresDeSodio - cantExtraccion); 
                return cantExtraccion;
                }
                
            case 3 -> { //Uranio
                System.out.println("Se ha seleccionado la extraccion de Uranio. En el planeta hay "+uranio+" unidades.");
                System.out.println("Cuantas unidades de uranio deseas extraer?");
                cantExtraccion = scan.nextInt();
                while (cantExtraccion > uranio && cantExtraccion < 0) { 
                    System.out.println("Ingresa un valor valido. (Entre 0 y la cantidad de uranio)");
                    cantExtraccion = scan.nextInt();
                }
                uranio = uranio - cantExtraccion;
                return cantExtraccion;
            }
            default ->{
                System.out.println("Elige un recurso disponible en el planeta!");
            }
        }
        return -1;
    }
}