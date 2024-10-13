
import java.util.Random;

public class Helado extends Planeta implements tieneAsentamientos {
    private int temperatura;

    //constructor
    public Helado(){
        Random aleatorio = new Random();

        int radio = aleatorio.nextInt(1000000 - 1000 + 1) + 1000;
        int cristalesHidrogeno = (int)(0.65 * 4 * Math.PI * Math.pow(radio, 2));
        int floresDeSodio = (int)(0.35 * 4 * Math.PI * Math.pow(radio, 2));

        this.temperatura = aleatorio.nextInt(120 - 30 + 1) - 120;

        super.setRadio(radio);
        super.setCristalesHidrogeno(cristalesHidrogeno);
        super.setFloresDeSodio(floresDeSodio);

    }

    //Getters y setters
    public int getTemperatura() {
        return temperatura;
    }

    //Metodos
    public double calcularConsumoEnergia() {
        return 0.15 * Math.abs(temperatura);
    }

    @Override
    public void visitarAsentamientos(Jugador jugador){
        
    }
}