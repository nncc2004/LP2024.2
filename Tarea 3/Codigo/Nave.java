public class Nave{

    private float unidadesCombustible;
    private float eficienciaPropulsor;
    

    //Constructor
    public Nave(){
        this.unidadesCombustible = 100;
        this. eficienciaPropulsor = 0;
        
    }

    //Metodos
    public boolean viajarPlaneta(MapaGalactico MG, int direccion, int tamanoSalto){

        return false;
    }
    public void recargarPropulsores(int hidrogeno){
        float unidadesRecargadas = (float) 0.6 * hidrogeno * (1+ eficienciaPropulsor);
        unidadesCombustible  = unidadesCombustible + unidadesRecargadas;
        System.out.println("Unidades recargadas: "+ unidadesRecargadas);
    }

    public void reducirCombustible(int tamanioSalto){
        float unidadesConsumidas = (float) (0.75* Math.pow(tamanioSalto, 2) * (1- eficienciaPropulsor));
        unidadesCombustible = unidadesCombustible - unidadesConsumidas;
    }
    public void reinicio(){
        unidadesCombustible = 100;
        eficienciaPropulsor = 0;
    }
    public void aumentarEficienciaNave(float aumento){
        eficienciaPropulsor += aumento;
    }

    //getters y setters

    public float getUnidadesCombustible(){
        return unidadesCombustible;
    }

    public float geteficienciaPropulsor(){
        return eficienciaPropulsor;
    }

}