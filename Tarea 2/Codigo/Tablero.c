#include <stdlib.h>
#include "Tablero.h"

void *** tablero;

void inicializarTablero(int tamano){
    tablero = malloc(tamano *sizeof(void **));

    for (int i = 0; i < tamano; i++){
        tablero[i] = malloc(tamano * sizeof(void *));
        for(int j = 0; j< tamano; j++){
            tablero[i][j] = NULL;
        }
    }
}


