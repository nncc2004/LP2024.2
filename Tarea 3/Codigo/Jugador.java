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
        /*
        Input: No recibe parametros
        Funcionalidad Este es el constructor de la clase Jugador. Se inicializan los parametros y
        se inicializa in objeto de tipo Nave

        Output: No genera output

        */

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
        /*
        Input: Recibe un entero con la cantidad de sodio a usar para recargar la energia del trjae
        Funcionalidad: Segun el sodio dado, calcula cuanta energia de proteccion se debe recargar y se recarga 
        las unidades en el parametro correspondiente. 
        Retorno: No genera retorno
        */
        float unidadesRecargadas = (float) 0.65* sodio * (1+ eficienciaEnergiaProteccion);
        unidadesEnergiaProteccion += unidadesRecargadas;
        Sodio = Sodio - sodio;
        System.out.println("Se han recargado "+unidadesRecargadas+" unidades de energia.");

    }

    public void reducirEnergiaProteccion(int cantExtraccion, float ConsumoEnergia){
        /*
        Input: Recibe la cantidad de material extraido y el consumo de energia entregado por el planeta 
        Funcionalidad: Calcula la cantidad de energia a extraer del traje segun la formula entregada y los parametros recibidos
        y se las resta al objeto de clase jugador.
        Retorno: No genera retorno
        */
        float unidadesConsumidas = (float) 0.5 *cantExtraccion * (ConsumoEnergia/100)*(1 - eficienciaEnergiaProteccion);
        unidadesEnergiaProteccion -= unidadesConsumidas;

    }
    public void reinicio(){
        /*
        Input: No recibe input
        Funcionalidad: Utilizada cuando el jugador se queda sin energia en el traje, reinicia los parametros de la energia 
        y los objetos del inventario. Además resta una vida y hace que el jugador quede "en orbita" 
        Por último si te quedas sin vida cambia la variable derrota a true para que se acabe la ejecucion del programa
        Retorno: No genera retorno
        */
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
        /*
        Input: Recibe un float co nla cantidad de eficiencia a aumentar
        Funcionalidad: Aumenta la cantidad segun lo recibido en el parametro. Funciona como un setter
        Retorno: No genera retorno
        */
        eficienciaEnergiaProteccion += aumento;
    }


    //getters y setters
    public float getUnidadesEnergiaProteccion() {
        /*
        Input: Recibe un float co nla cantidad de eficiencia a aumentar
        Funcionalidad: Aumenta la cantidad segun lo recibido en el parametro. Funciona como un setter
        Retorno: No genera retorno
        */
        return unidadesEnergiaProteccion;
    }

    public float getEficienciaEnergiaProteccion() {
        /*
        Input: No recibe parametros
        Funcionalidad: Getter de la eficiencia
        Retorno: retorna la eficiencia
        */
        return eficienciaEnergiaProteccion;
    }

    public String getEstado() {
        /*
        Input: No recibe parametros
        Funcionalidad: Getter del estado
        Retorno: retorna el estado
        */
        return estado;
    }

    public void setEstado(String estado) {
        /*
        Input: recibe un string 
        Funcionalidad: Setter del estado. Da el valor del string al atributo estado. 
        Retorno: retorna el estado
        */
        this.estado = estado;
    }
    public float getHidrogeno() {
        /*
        Input: No recibe parametros
        Funcionalidad: Getter de la cantidad de hidrogeno
        Retorno: No genera retorno
        */
        return Hidrogeno;
    }

    public void setHidrogeno(float Hidrogeno) {
        /*
        Input: Recibe un float 
        Funcionalidad: Setter del hidrogeno. Da el valor recibido al atributo hidrogeno
        Retorno: no genera retorno
        */
        this.Hidrogeno = Hidrogeno;
    }
    public float getSodio() {
        /*
        Input: No recibe parametros
        Funcionalidad: Getter del sodio
        Retorno: retorna el sodio
        */
        return Sodio;
    }

    public void setSodio(float Sodio) {
        /*
        Input: recibe un float 
        Funcionalidad: Setter del sodio. Da el valor recibido al atributo Sodio.
        Retorno: No genera retorno
        */
        this.Sodio = Sodio;
    }
    public float getUranio() {
        /*
        Input: No recibe parametros
        Funcionalidad: Getter del Uranio
        Retorno: retorna el uranio
        */
        return Uranio;
    }

    public void setUranio(float Uranio) {
        /*
        Input: Recibe un float
        Funcionalidad: Setter del Uranio. Da el valor de 
        Retorno: retorna la eficiencia
        */
        this.Uranio = Uranio;
    }
    public float getPlatino() {
        /*
        Input: No recibe parametros
        Funcionalidad: Getter del platino
        Retorno: retorna el platino
        */
        return Platino;
    }

    public void setPlatino(float Platino) {
        /*
        Input: No recibe parametros
        Funcionalidad: Getter de la eficiencia
        Retorno: retorna la eficiencia
        */
        this.Platino = Platino;
    }

    public Nave getNave(){
        /*
        Input: No recibe parametros
        Funcionalidad: Getter del objeto nave del objeto jugador
        Retorno: retorna un objeto de tipo Nave
        */
        return nave;
    }

    public boolean getVictoria(){
        /*
        Input: No recibe parametros
        Funcionalidad: Getter del booleano victoria
        Retorno: retorna el atributo victoria
        */
        return victoria;
    }

    public void setVictoria(boolean bool){
        /*
        Input: Recibe un booleano
        Funcionalidad: Setter del atributo victoria segun el valor recibido como parametro
        Retorno: No genera retorno
        */
        victoria = bool;
    }

    public boolean getDerrota(){
        /*
        Input: No recibe parametros
        Funcionalidad: Getter de la derrota
        Retorno: retorna un booleano con el atributo derrota
        */
        return derrota;
    }

    public void setDerrota(boolean bool){
        /*
        Input: Recibe un booleano
        Funcionalidad: Setter de la derrota. Asigna el valor recibido al atributo derrota
        Retorno: No genera retorno
        */
        derrota = bool;
    }

    public int getVidas(){
        /*
        Input: No recibe parametros
        Funcionalidad: Getter de las vidas
        Retorno: retorna las vidas
        */
        return vidas;
    }
}