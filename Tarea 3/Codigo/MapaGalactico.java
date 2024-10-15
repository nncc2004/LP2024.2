import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MapaGalactico{

    private List<Planeta> planetas;

    private int posicion;
    private int tamanoLista;
    private boolean CentroExistente;

    //Constructor
   public MapaGalactico(){
        /*
        Input: No recibe parametros
        Funcionalidad Este es el constructor de la clase MapaGalactico. Inicializa los atributos y crea una nueva lista
        con 'nre ArrayList<>();'

        Output: No genera output

        */
        this.CentroExistente = false;
        this.planetas = new ArrayList<>();
        this.posicion = 0;
        this.tamanoLista = 0;

   }

    //Metodos
    public void generadorPlaneta(){
        /*
        Input: No recibe parametros
        Funcionalidad Esta funcion se encarga de generar un planeta aleatoriamente y añadirlo a la lista del objeto.
        Además, suma 1 a la cantidad de planetas.

        Output: No genera output

        */
        Random aleatorio = new Random();
        int numeroPlaneta = aleatorio.nextInt(100)+1;
        Planeta NuevoPlaneta;
        if(numeroPlaneta <= 30){
            NuevoPlaneta = new Helado();
        }else if(numeroPlaneta <=60){
            NuevoPlaneta = new Oceanico();
        } else if(numeroPlaneta <= 80){
            NuevoPlaneta = new Radiactivo();
        } else if(numeroPlaneta <=99){
            NuevoPlaneta = new Volcanico();
        } else{
            if(this.CentroExistente){
                NuevoPlaneta = new Volcanico();
            }else{
                NuevoPlaneta = new CentroGalactico();
                this.CentroExistente = true;
            }
            
        }
        planetas.add(NuevoPlaneta);
        tamanoLista = tamanoLista +1;
        

    }

    public void Mostrar(){
        /*
        Input: No recibe parametros
        Funcionalidad Esta funcion se encarga de recorrer e ir imprimiendo la lista de planetas con su posicion
        Output: No genera output

        */
        System.out.println("Hay un total de "+ tamanoLista + " planetas descubiertos:");
        int i = 0;
        for (Planeta planeta :planetas){
            if (i == posicion){
                System.out.println(i+ ". " + planeta.getClass().getSimpleName() + " (pos. actual)");
            } else {
                System.out.println(i+ ". " + planeta.getClass().getSimpleName());
            }
            i= i +1;
        }
        System.out.println("\n");
    }

    public boolean descubrirSiguiente(){
        /*
        Input: No recibe parametros
        Funcionalidad Este metodo se encarga de generar un nuevo planeta llamando  a la funcion generadorPlaneta(), pero 
        antes verifica que el jugador este en el último planeta de la lista, ya que sólo así se pueden descubrir nuevos
        planetas. 

        Output: No genera output

        */
        if (posicion != tamanoLista-1){
            System.out.println("Se debe estar en el planeta mas lejano al inicial para poder descubrir nuevos planetas!");
            System.out.println("Viaja hasta el planeta en la posicion " + (tamanoLista-1) +" para hacerlo\n");
            return false;

        }
        this.generadorPlaneta();
        System.out.println("Analizando cercanias...");
        System.out.println("Se ha descubierto un nuevo planeta!! Revisa tu mapa galactico para ver te que tipo es.\n");
        return true;
        
    }

    /*
    public void viajar(Nave nave, int tamanoSalto, int direccion){    
        int futuraPoscion;
        float unidadesConsumidas = (float) (0.75* Math.pow(tamanoSalto, 2) * (1- nave.geteficienciaPropulsor()));

        if(direccion == 1){
            futuraPoscion = posicion - tamanoSalto;
        } else {
            futuraPoscion = posicion + tamanoSalto;
        }

        if(futuraPoscion >= tamanoLista || futuraPoscion < 0){
            System.out.println("Elige un planeta disponible en el mapa!");
            System.out.println("Viaje abortado.\n");
            return;
        }
        if(unidadesConsumidas >= nave.getUnidadesCombustible()){
            System.out.println("El planeta esta muy lejos y te vas a quedar sin combustible!");
            System.out.println("Elige otro planeta o recarga combustible!\n");
            System.out.println("Combustible actual: "+ nave.getUnidadesCombustible());
            System.out.println("Combustible necesario para el viaje: "+ unidadesConsumidas);
            System.out.println("Viaje abortado.\n");
            return;
        }

        System.out.println("Viaje exitoso!");
        System.out.println("Combustible consumido: "+ unidadesConsumidas + "\n");
        nave.reducirCombustible(tamanoSalto);
        posicion = futuraPoscion;


    }
    */



    public void reinicio(){
        /*
        Input: No recibe parametros
        Funcionalidad: Este metodo reinicia la posicion del jugador a cero. Se utiliza cuando se queda sin energía
        Output: No genera output

        */
        posicion = 0;
    }

    //Getters y setters

    public String getNombrePlanetaActual(){
        /*
        Input: No recibe parametros
        Funcionalidad: Getter del nombre del tipo de planeta (definido según la subclase que es). Crea un objeto tipo planeta y 
        le asigna el planeta de la posicion acutal de la lista. 
        Output: Retorna un string con el nombre del planeta (subclase)

        */
        Planeta actual = planetas.get(posicion);
        return actual.getClass().getSimpleName();
    }

    public int getPosicion(){
        /*
        Input: No recibe parametros
        Funcionalidad: Getter de la poscion actual en la lista
        Output: retorna un int  con el valor de la posicion

        */
        return posicion;
    }
    public Planeta getPlanetaActual(){
        /*
        Input: No recibe parametros
        Funcionalidad: Getter del planeta actual. 
        Output: retorna un objeto de tipo Planeta que corresponde al objeto planeta en la posicion actual

        */
        Planeta actual = planetas.get(posicion);
        return actual;
    }

    public int getTamanoLista(){
        /*
        Input: No recibe parametros
        Funcionalidad: Getter del tamaño de la lista
        Output: retorna el tamaño de la lista.

        */
        return tamanoLista;
    }

    public void setPosicion(int futuraPOS){
        /*
        Input: Recibe un entero con la futura posicion en la lista
        Funcionalidad: Setter de la posicion. Asigna el valor recibido al atributo posicion 
        Output: No genera output

        */
        posicion = futuraPOS;
    }
}
