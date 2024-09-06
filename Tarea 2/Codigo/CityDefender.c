#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include "Cartas.h"
#include "Tablero.h"

int main(int argc, char const *argv[])
{
    srand(time(NULL));
    int a;
    const char *limpiar;
    printf("Para ejecutar en windows, presione 1. Para Linux presione 2. Para otros, seleccione 0:\n");
    scanf("%d", &a);
    switch (a){
        case 1:
            limpiar = "cls";
            break;
        case 2:
            limpiar = "clear";
            break;
        default:
            break;
        }
    system(limpiar);
    
    inicializarTablero(4);
    inicializarMazo();
    
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
        system(limpiar);
    }
    
    
    return 0;
}
