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
        this.CentroExistente = false;
        this.planetas = new ArrayList<>();
        this.posicion = 0;
        this.tamanoLista = 0;

   }

    //Metodos
    public void generadorPlaneta(){
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

    public void reinicio(){
        posicion = 0;
    }

    //Getters y setters

    public String getNombrePlanetaActual(){
        Planeta actual = planetas.get(posicion);
        return actual.getClass().getSimpleName();
    }

    public int getPosicion(){
        return posicion;
    }
    public Planeta getPlanetaActual(){
        Planeta actual = planetas.get(posicion);
        return actual;
    }
}