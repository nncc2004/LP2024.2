import java.util.Scanner;

public abstract class Planeta{
    private int radio;
    private int cristalesHidrogeno;
    private int  floresDeSodio;

        

    //getters y setters
    public int getRadio() {
        /*
         Input: No recibe input
         Funcionalidad: Getter del radio
         Output: retorna el radio 
         */
        return radio;
    }

    public void setRadio(int radio) {
        /*
         Input: Recibe un entero
         Funcionalidad: Setter del radio. Asigna el valor recibido al radio del planeta
         Output: No tiene retorno
         */
        this.radio = radio;
    }

    public int getCristalesHidrogeno() {
        /*
         Input: No recibe input
         Funcionalidad: Getter de los cristales de hidrogeno
         Output: retorna la cantidad de cristales de hidrogeno
         */
        return cristalesHidrogeno;
    }

    public void setCristalesHidrogeno(int cristalesHidrogeno) {
        /*
         Input: Recibe un entero
         Funcionalidad: Setter de los cristales de hidrogeno. Asigna el valor recibido a la cantidad de cristales de hidrogeno
         Output: No tiene retorno
         */
        this.cristalesHidrogeno = cristalesHidrogeno;
    }

    public int getFloresDeSodio() {
        /*
         Input: No recibe input
         Funcionalidad: Getter de las flores de sodio
         Output: retorna la cantidad de flores de sodio
         */
        return floresDeSodio;
    }

    public void setFloresDeSodio(int  floresDeSodio) {
        /*
         Input: Recibe un entero
         Funcionalidad: Setter del radio. Asigna el valor recibido a la cantidad de flroes de sodio
         Output: No tiene retorno
         */
        this.floresDeSodio = floresDeSodio;
    }


    //metodos
    public boolean visitar(Jugador jugador){
        /*
         Input: Recibe un objeto de tipo jugador.
         Funcionalidad: Cambia el estado del jugador a "Planeta" para que desde main se sepa que ya no se esta en orbita.
         Retorno: Siempre retorna ture.
         */
        System.out.println("Accediendo al planeta....");
        System.err.println("El acercamiento ha sido un exito.");
        jugador.setEstado("planeta");
        return true;
    }

    public int extraerRecursos(int tipo){
        /*
         Input: Recibe un entero que representa el tipo de recurso a extraer
         Funcionalidad: Segun el tipo de recurso elegido para extraer (1 = hidrogeno, 2 = sodio), muestra la cantidad disponible en el planeta, y se da a elegir al usuario cuantos quiere extraer
         se verifica que sea un valor entre 0 y el maximo disponible. Kuego resta esa cantidad de recursos del planeta
         Retorno: Retorna la cantidad de recursos extraida, que luego sera necesario para restar la energia al traje. 
         */
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
        /*
         Input: No recibe input
         Funcionalidad: No le encontre una funcionalidad real, por lo que solo printea 
         Retorno: Siempre retorna true.
         */
        System.out.println("Volviendo a la orbita...");
        return true;
    }

    //Esta funcion sera comentada en cada sublcase
    public abstract float calcularConsumoEnergia();
}