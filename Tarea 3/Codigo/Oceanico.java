import java.util.Random;

public class  Oceanico extends Planeta implements tieneAsentamientos{
    private int profundidad;

    public Oceanico(){
        Random aleatorio = new Random();

        int radio = aleatorio.nextInt(1000000 - 10000 + 1) + 10000;

        this.profundidad = aleatorio.nextInt(1000 - 30 + 1) + 30;
        
        int cristalesHidrogeno = (int)(0.2 * 4 * Math.PI * Math.pow(radio, 2));
        int floresDeSodio = (int)(0.65 * 4 * Math.PI * Math.pow(radio, 2));

        super.setRadio(radio);
        super.setCristalesHidrogeno(cristalesHidrogeno);
        super.setFloresDeSodio(floresDeSodio);
    }

    
    //Getters y setters
    public int getProfundidad() {
        return profundidad;
    }

    //Metodos
    public double calcularConsumoEnergia() {
        return 0.002 * Math.pow(profundidad, 2);
    }

    @Override
    public void visitarAsentamientos(Jugador jugador){
        
    }
}