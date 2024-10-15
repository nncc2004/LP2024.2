public class Nave{

    private float unidadesCombustible;
    private float eficienciaPropulsor;
    

    //Constructor
    public Nave(){
        /*
        Input: No recibe parametros
        Funcionalidad: Inicializa la nave con 100 de combustible y 0 de eficiencia
        Output: No genera output
        */
        this.unidadesCombustible = 100;
        this. eficienciaPropulsor = 0;
        
    }

    //Metodos
    public boolean viajarPlaneta(MapaGalactico MG, int direccion, int tamanoSalto){
        /*
        Input: Recibe un objeto tipo MapaGalactico y dos enteros, la direccion y el tamaño del salto.
        Funcionalidad: Este metodo se engarga de mover al jugador a la posicion deseada y llamando a la funcion reducirCombustible
        reducir el combustible de la nave. También verifica que el viaje sea posible según la cantidad de combustible y ve que la 
        posicion a la que se quiere viajar efectivamente esté descubiera en el mapa espacial.
        Output: Aunque no se utilice, retorna false si se encuentra cualquiera de los errores de antes. Si el viaje se realiza
        retorna true.

        */
        int futuraPoscion;
        float unidadesConsumidas = (float) (0.75* Math.pow(tamanoSalto, 2) * (1- geteficienciaPropulsor()));

        if(direccion == 1){
            futuraPoscion = MG.getPosicion() - tamanoSalto;
        } else {
            futuraPoscion = MG.getPosicion() + tamanoSalto;
        }

        if(futuraPoscion >= MG.getTamanoLista() || futuraPoscion < 0){
            System.out.println("Elige un planeta disponible en el mapa!");
            System.out.println("Viaje abortado.\n");
            return false;
        }
        if(unidadesConsumidas >= getUnidadesCombustible()){
            System.out.println("El planeta esta muy lejos y te vas a quedar sin combustible!");
            System.out.println("Elige otro planeta o recarga combustible!\n");
            System.out.println("Combustible actual: "+ getUnidadesCombustible());
            System.out.println("Combustible necesario para el viaje: "+ unidadesConsumidas);
            System.out.println("Viaje abortado.\n");
            return false;
        }

        System.out.println("Viaje exitoso!");
        System.out.println("Combustible consumido: "+ unidadesConsumidas + "\n");
        reducirCombustible(tamanoSalto);
        MG.setPosicion(futuraPoscion);


        return true;
    }


    public void recargarPropulsores(int hidrogeno){
        /*
        Input: Recibe un entero que representa la cantida de hidrogeno a utilizar
        Funcionalidad: El método se encarga de recargar los propulsores según la cantidad de hidrogeno 
        recibida en el parametro y la función definida en la tarea
        Output: No retorna nada
        */
        float unidadesRecargadas = (float) 0.6 * hidrogeno * (1+ eficienciaPropulsor);
        unidadesCombustible  = unidadesCombustible + unidadesRecargadas;
        System.out.println("Unidades recargadas: "+ unidadesRecargadas);
    }

    public void reducirCombustible(int tamanioSalto){
        /*
        Input: Recibe un entero correspondiente al tamanio del salto
        Funcionalidad: Con el tamanio del salto se reduce el combustible. 
        Output: no hay retorno

        */
        float unidadesConsumidas = (float) (0.75* Math.pow(tamanioSalto, 2) * (1- eficienciaPropulsor));
        unidadesCombustible = unidadesCombustible - unidadesConsumidas;
    }
    
    public void reinicio(){
        /*
        Input: No recibe parametros
        Funcionalidad: El metodo se utiliza cuando el traje se queda energía. Reinicia el combustible a 100
        Output: No genera retorno

        */
        unidadesCombustible = 100;
    }

    public void aumentarEficienciaNave(float aumento){
        /*
        Input: Recibe un float correspondiente a la cantidad a aumentar
        Funcionalidad: Similar a un setter, aumenta la eficiencia en la cantidad recibida. 
        Output:No genera retorno
        */
        eficienciaPropulsor += aumento;
    }

    //getters y setters

    public float getUnidadesCombustible(){
        /*
        Input: No recibe parametros
        Funcionalidad: Getter de la cantidad de combustible
        Output: retorna la cantidad de combustible

        */
        return unidadesCombustible;
    }

    public float geteficienciaPropulsor(){
        /*
        Input: No recibe parametros
        Funcionalidad: Getter de la  eficiencia del propulsor
        Output: retorna la eficiencia de propulsor

        */
        return eficienciaPropulsor;
    }

}