#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include "Cartas.h"
#include "Tablero.h"

void teclaContinuar(){
    /*
    no recibe parámetros

    //////////////////////////////////////

    La función se encarga de hacer al suuario ingresar "Enter" para ir a la siguiente "pantalla"
    Lo hace mediante un wihle que en su propia condicion lee y limpia el buffer hasat que sea enter
    Luego vuelve a limpiar el buffer para evitar problemas mas adelante.

    //////////////////////////////////////
    
    No hay retorno
    */
    printf("\nPresione 'Enter' para continuar . . .");
    while (getchar() != '\n');
    getchar(); 
}
const char *limpiar;
int dificultad;
int cantAciertos;

int main(int argc, char const *argv[]){
    srand(time(NULL));
    int turnos;
    #ifdef _WIN32
        limpiar = "cls";  // Windows
    #else
        limpiar = "clear";  // Unix/Linux/MacOS
    #endif
    system(limpiar);
    //////////////////////// Bienvenida //////////////////////////////
    while (dificultad != 1 && dificultad != 2 && dificultad !=3){
        printf("Bienvenido! Selecciona una dificultad:\n");
        printf(" 1. Facil -> 11 X 11 , 5 Barcos \n 2. Media -> 17 X 17 , 7 Barcos\n 3. Dificil -> 21 X 21 , 9 Barcos\nDificultad: ");
        scanf("%d", &dificultad);
        system(limpiar);
        if(dificultad != 1 && dificultad != 2 && dificultad !=3) printf("Valor incorrecto, ingresa de nuevo");
    }
    switch (dificultad)    {
    case 1:
        printf("Se ha seleccionado la dificultad 'Facil'. \nEsta incluye un tablero de 11 x 11 y 5 barcos:\n   - 2 barcos de 1 X 2\n   - 1 barco de 1 X 3 \n   - 1 barco de 1 X 4\n   - 1 barco de 1 X 5\n");
        printf("La partida contará con un límite de 30 turnos\n");
        inicializarTablero(11);
        turnos = 30;
        cantAciertos = 16;
        break;
    case 2: 
        printf("Se ha seleccionado la dificultad 'Media'. \nEsta incluye un tablero de 17 x 17 y 7 barcos:\n   - 3 barcos de 1 X 2\n   - 2 barco de 1 X 3 \n   - 1 barco de 1 X 4\n   - 1 barco de 1 X 5\n");
        printf("La partida contará con un límite de 25 turnos\n");
        inicializarTablero(17);
        turnos = 25;
        cantAciertos = 21;
        break;
    case 3: 
        printf("Se ha seleccionado la dificultad 'Dificil'. \nEsta incluye un tablero de 21 x 21 y 9 barcos:\n   - 3 barcos de 1 X 2\n   - 2 barco de 1 X 3 \n   - 2 barco de 1 X 4\n   - 2 barco de 1 X 5\n");
        printf("La partida contará con un límite de 15 turnos\n");
        inicializarTablero(21);
        turnos = 15;
        cantAciertos = 30;
        break;
    default:
        break;
    }
    printf("\nCuentas con 5 disparos disponibles: \n   - Disparo simple: 1x1 \n   - Disparo grande: 3x3\n   - Disparo radar 5x5 (muestra barcos)\n   - Disparo lineal: 1x5 o 5x1\n   - Disparo 500Kg: 11x11  (solo una vez)");

    iniciarBarcos(dificultad);
    inicializarMazo();
    teclaContinuar();
    system(limpiar);
    //////////////////////// Logica de los turnos //////////////////////////////
    while (turnos > 0 && cantAciertos >0){
        printf("Turnos restantes: %d\n", turnos);
        printf("Barcos restantes: %d\n", CantidadBarcos);
        mostrarTablero();
        mostrarMazo();
        usarCarta();
        teclaContinuar();
        system(limpiar);
        if(cantAciertos> 0) turnos --;
    }

    //////////////////////// Victoria o derrota //////////////////////////////
    if(turnos == 0) {
        printf("Perdiste!!!\n");
        printf("Faltaron %d barcos\n", CantidadBarcos);
        mapaDerrota();
        mostrarTablero();
    }
    if(cantAciertos == 0) {
        printf("Ganaste!!!\n");
        printf("Restaron %d turnos antes de la invasión\n", turnos);
        mapaVictoria();    
        mostrarTablero();
    }
    teclaContinuar();
    //////////////////////// Liberar memoria //////////////////////////////
    liberarTablero();
    liberarMazo();
    liberarBarcos();
    return 0;
}
