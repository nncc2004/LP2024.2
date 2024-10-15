public class CentroGalactico extends Planeta{

    @Override  
    public boolean visitar(Jugador jugador){
        Nave nave = jugador.getNave();
        System.out.println("Estas intentando acceder al centro galactico!");
        System.err.println("Solo podras hacerlo si tu nave tiene una eficciencia por sobre el 50%");
        if(nave.geteficienciaPropulsor() >= 0.5){
            System.out.println("Es increible!! Lo has logrado, eres la primera persona en entrar al centro galactico.");
            System.out.println("Has ganado el dia de hoy!! Mucha suerte en tus futuras aventuras.");
            jugador.setVictoria(true);
        } else {
            System.out.println("Incluso con tus grandes habilidades pilotando la nave, ha sido imposible acercarse al centro galactico.");
            System.out.println("Actialmente la eficiencia de tu nave es " + nave.geteficienciaPropulsor());
            System.out.println("Consigue materiales e intercambialos por mejoras en los acentamientos de planetas oceanicos y helados para mejorar!");
            System.out.println("Por ahora sigues orbitando el centro galactico.");
        }
        
        return true;
    }
    
    @Override
    public float calcularConsumoEnergia() {
        return 0;
    }
}