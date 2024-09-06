#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include "Cartas.h"
#include "Tablero.h"

const char *limpiar;
int main(int argc, char const *argv[])
{
    srand(time(NULL));
    int a;
    #ifdef _WIN32
        limpiar = "cls";  // Windows
    #else
        limpiar = "clear";  // Unix/Linux/MacOS
    #endif
    system(limpiar);
    
    
    inicializarMazo();
    inicializarTablero(11);
    Barco bar;
    bar.largo = 2;
    bar.particiones_activas = 3;
    bar.pos_arreglo = 0;


    crearBarco(&bar);
    crearBarco(&bar);


    while (1)
    {   
        mostrarTablero();
        mostrarMazo();
        usarCarta();
        //system(limpiar);
    }
    
    
    return 0;
}
