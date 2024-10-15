public class CentroGalactico extends Planeta{

    @Override  
    public boolean visitar(Jugador jugador){
        /*
        Input: Jugador jugador
        Funcionalidad: Al ser el centro galactico, el juego debe asegurarse de que la nave del jugador tenga el 50% 
        de eficiencia requerida par poder ser visitado. Esta función se encarga de eso al acceder a la nave que tiene
        la clase jugador por defecto. En el caso de que la nave si cumpla los requisitos, se cambia el estado de victoria
        del jugador a true y se acaba la partida. En caso contrario se muestra por pantalla que no se pudo visitar 
        y no se cambia el estado en el que esté el jugador.

        Output: El retorno no se utiliza, por lo que siempre retorna true.W
        

        */
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