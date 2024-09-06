#include <stdlib.h>
#include <stdio.h>
#include "Cartas.h"

Mano Cartas;

void inicializarMazo(){
    Cartas.carta = malloc(5* sizeof(void* ));
    Cartas.disponibles = 5;
    for(int i = 0; i < 5; i++){
        int *valor = malloc(sizeof(int));
        *valor = 1;
        Cartas.carta[i] = valor;
    }
}


void mostrarMazo(){
    printf("|");
    for(int i = 0; i < Cartas.disponibles; i++){
        int *valor = (int *)Cartas.carta[i];
        switch(*valor){
            case 1:
                printf(" Disparo simple (%d) |", i+1);
                break;
            case 2:
                printf(" Disparo grande (%d) |", i+1);
                break;
            case 3:
                printf(" Disparo lineal (%d)  |", i+1);
                break;
            case 4:
                printf(" Disparo radar (%d) |", i+1);
                break;
            case 5:
                printf(" Disparo 500KG (%d) |", i+1);
                break;
            default:
                printf("A");
        }

    }
    printf("\n\n");
}
void usarCarta();

void * disparoSimple(int x, int y);
void * disparoGrande(int x, int y);
void * disparoLineal(int x, int y);
void * disparoRadar(int x, int y);
void * disparo500KG(int x, int y);