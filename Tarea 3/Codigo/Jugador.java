public class Jugador {
    private float unidadesEnergiaProteccion;
    private float eficienciaEnergiaProteccion;
    private String estado; //planeta u orbita

    //Inventario
    private float Hidrogeno;
    private float Sodio;
    private float Uranio;

    //Constructor
    public Jugador(){
        this.unidadesEnergiaProteccion = 100;
        this.eficienciaEnergiaProteccion = 0;
        this.Hidrogeno = 0;
        this.Sodio = 0;
        this.Uranio = 0;
        this.estado = "orbita";

    }

    //Metodos
    public void recargarEnergiaProteccion(int sodio){
        float unidadesRecargadas = (float) 0.65* sodio * (1+ eficienciaEnergiaProteccion);
        unidadesEnergiaProteccion = unidadesEnergiaProteccion + unidadesRecargadas;
        Sodio = Sodio - sodio;

    }

    public void reducirEnergiaProteccion(int cantExtraccion, int ConsumoEnergia){
        float unidadesConsumidas = (float) 0.5 *cantExtraccion * (ConsumoEnergia/100)*(1 - eficienciaEnergiaProteccion);
        unidadesEnergiaProteccion = unidadesEnergiaProteccion - unidadesConsumidas;

    }
    public void reinicio(){
        unidadesEnergiaProteccion = 100;
        eficienciaEnergiaProteccion = 0;
        Hidrogeno = 0;
        Sodio = 0;
        Uranio = 0;
        estado = "orbita";
    }


    //getters y setters
    public float getUnidadesEnergiaProteccion() {
        return unidadesEnergiaProteccion;
    }

    public float getEficienciaEnergiaProteccion() {
        return eficienciaEnergiaProteccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    public float getHidrogeno() {
        return Hidrogeno;
    }

    public void setHidrogeno(float Hidrogeno) {
        this.Hidrogeno = Hidrogeno;
    }
    public float getSodio() {
        return Sodio;
    }

    public void setSodio(float Sodio) {
        this.Sodio = Sodio;
    }
    public float getUranio() {
        return Uranio;
    }

    public void setUranio(float Uranio) {
        this.Uranio = Uranio;
    }
}