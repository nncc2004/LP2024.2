public class Jugador {
    private float unidadesEnergiaProteccion;
    private float eficienciaEnergiaProteccion;
    private String estado; //planeta u orbita
    private Nave nave;
    private boolean victoria;
    private boolean derrota;
    private int vidas;

    //Inventario
    private float Hidrogeno;
    private float Sodio;
    private float Uranio;
    private float Platino;

    //Constructor
    public Jugador(){
        this.unidadesEnergiaProteccion = 100;
        this.eficienciaEnergiaProteccion = 0;
        this.Hidrogeno = 0;
        this.Sodio = 0;
        this.Uranio = 0;
        this.Platino = 0;
        this.estado = "orbita";
        this.nave = new Nave();
        this.victoria = false;
        this.victoria = false;
        this.vidas = 3;

    }

    //Metodos
    public void recargarEnergiaProteccion(int sodio){
        float unidadesRecargadas = (float) 0.65* sodio * (1+ eficienciaEnergiaProteccion);
        unidadesEnergiaProteccion += unidadesRecargadas;
        Sodio = Sodio - sodio;
        System.out.println("Se han recargado "+unidadesRecargadas+" unidades de energia.");

    }

    public void reducirEnergiaProteccion(int cantExtraccion, float ConsumoEnergia){
        float unidadesConsumidas = (float) 0.5 *cantExtraccion * (ConsumoEnergia/100)*(1 - eficienciaEnergiaProteccion);
        unidadesEnergiaProteccion -= unidadesConsumidas;

    }
    public void reinicio(){
        unidadesEnergiaProteccion = 100;
        eficienciaEnergiaProteccion = 0;
        Hidrogeno = 0;
        Sodio = 0;
        Uranio = 0;
        Platino = 0;
        estado = "orbita";
        vidas -=1;
        System.out.println("Has perdido una vida! Vidas restantes: "+ vidas);
        if(vidas == 0 ){
            System.out.println("Oh no! Te has quedado sin vidas. ");
            System.out.println("Game over...");
            derrota = true;
        }
    }
    public void aumentarEficienciaTraje(float aumento){
        eficienciaEnergiaProteccion += aumento;
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
    public float getPlatino() {
        return Platino;
    }

    public void setPlatino(float Platino) {
        this.Platino = Platino;
    }

    public Nave getNave(){
        return nave;
    }

    public boolean getVictoria(){
        return victoria;
    }

    public void setVictoria(boolean bool){
        victoria = bool;
    }

    public boolean getDerrota(){
        return derrota;
    }

    public void setDerrota(boolean bool){
        derrota = bool;
    }

    public int getVidas(){
        return vidas;
    }
}