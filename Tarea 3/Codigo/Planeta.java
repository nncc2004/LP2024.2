import java.util.Scanner;

public abstract class Planeta{
    private int radio;
    private int cristalesHidrogeno;
    private int  floresDeSodio;



    //getters y setters
    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    public int getCristalesHidrogeno() {
        return cristalesHidrogeno;
    }

    public void setCristalesHidrogeno(int cristalesHidrogeno) {
        this.cristalesHidrogeno = cristalesHidrogeno;
    }

    public int getFloresDeSodio() {
        return floresDeSodio;
    }

    public void setFloresDeSodio(int  floresDeSodio) {
        this.floresDeSodio = floresDeSodio;
    }


    //metodos
    public boolean visitar(Jugador jugador){
        System.out.println("Accediendo al planeta....");
        System.err.println("El acercamiento ha sido un exito.");
        jugador.setEstado("planeta");
        return true;
    }
    public int extraerRecursos(int tipo){
        Scanner scan = new Scanner(System.in);
        int cantExtraccion;
        switch (tipo) {
            case 1 ->{  //Cristales de hidrogeno
                System.out.println("Se ha seleccionado la extraccion de los cristales de hidrogeno. En el planeta hay "+cristalesHidrogeno+" cistrales de hidrogeno.");
                System.out.println("Cuantos cristales deseas extraer?");
                cantExtraccion = scan.nextInt();
                while (cantExtraccion > cristalesHidrogeno && cantExtraccion < 0) { 
                    System.out.println("Ingresa un valor valido. (Entre 0 y la cantidad de cristales)");
                    cantExtraccion = scan.nextInt();
                }
                cristalesHidrogeno = cristalesHidrogeno - cantExtraccion;
                return cantExtraccion;
                }
            case 2 -> { //Flores de Sodio
                System.out.println("Se ha seleccionado la extraccion de las Flores de Sodio. En el planeta hay "+floresDeSodio+" flores de sodio.");
                System.out.println("Cuantas flores deseas extraer?");
                cantExtraccion = scan.nextInt();
                while (cantExtraccion > floresDeSodio && cantExtraccion < 0) { 
                    System.out.println("Ingresa un valor valido. (Entre 0 y la cantidad de flores)");
                    cantExtraccion = scan.nextInt();
                }
                floresDeSodio = floresDeSodio - cantExtraccion;
                return cantExtraccion;
                }
            default ->{
                System.out.println("Elige un recurso disponible en el planeta!");
            }
        }
        return -1;
    }

    public boolean salir(){
        System.out.println("Volviendo a la orbita...");
        return true;
    }

    public abstract float calcularConsumoEnergia();
}