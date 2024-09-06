#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include "Cartas.h"
#include "Tablero.h"

int main(int argc, char const *argv[])
{
    srand(time(NULL));
    if (1){
        int a;
        printf("Para ejecutar en windows, presione 1. Para Linux presione 2. Para otros, seleccione 0:\n");
        scanf("%d", &a);
        switch (a){
            case 1:
                system("cls");
                break;
            case 2:
                system("clear");
                break;
            default:
                break;
            }
    }
    
    
    printf("Tablero de 10x10:\n");
    inicializarTablero(10);
    mostrarTablero();
    printf("\nMano actual:\n");
    inicializarMazo();
    mostrarMazo();


    Barco bar;
    bar.largo = 4;
    bar.particiones_activas = 3;
    bar.pos_arreglo = 0;

    
    crearBarco(&bar);
    mostrarTablero();
    crearBarco(&bar);
    mostrarTablero();
    
    return 0;
}
