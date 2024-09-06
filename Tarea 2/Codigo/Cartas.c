#include <stdlib.h>
#include <stdio.h>
#include <time.h>
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
                break;
        }

    }
    printf("\n\n");
}


void usarCarta(){
    printf("Ingrese la carta a usar: ");
    int cartaElegida;
    scanf("%d", &cartaElegida);

    int *valor = (int *)Cartas.carta[cartaElegida-1];
    int x, y;
    printf("Coordenada X del disparo: ");
    scanf("%d", &x);
    printf("Coordenada Y del disparo: ");
    scanf("%d", &y); 
    int nuevaCarta = rand() % 100;
    printf("Disparo a las coordenadas (%d,%d): ", x,y);
    switch(*valor){
            case 1:
                printf(" Disparo simple\n");
                disparoSimple(x, y);
                free(Cartas.carta[cartaElegida-1]);  // Liberar la memoria antigua
                int *nuevoValor = malloc(sizeof(int));  // Asignar nueva memoria
                if(nuevaCarta <= 64) {
                    *nuevoValor = 1;
                } else if(nuevaCarta <= 84) {
                    *nuevoValor = 2;
                } else if(nuevaCarta <= 89) {
                    *nuevoValor = 3;
                } else {
                    *nuevoValor = 4;
                }
                Cartas.carta[cartaElegida-1] = nuevoValor;  // Actualizar el puntero

                break;
            case 2:
                printf(" Disparo grande\n");
                void * disparoGrande(int x, int y);
                break;
            case 3:
                printf(" Disparo lineal \n");
                void * disparoLineal(int x, int y);
                break;
            case 4:
                printf(" Disparo radar\n");
                void * disparoRadar(int x, int y);
                break;
            case 5:
                printf(" Disparo 500KG \n");
                void * disparo500KG(int x, int y);
                break;
            default:
                break;
        }
}

void * disparoSimple(int x, int y){
    
    if(((casilla * ) tablero[x][y])->barco == 1){
        ((casilla * ) tablero[x][y])->simbolo = 'H';
        printf("Le has dado a un barco en (%d, %d)",x, y);
        //((casilla * ) tablero[x][y])->n_barco
        //Este es la posicion del arrego del barco que debemos modificar (Restar 1 a particiones_activas)
    }else{
        ((casilla * ) tablero[x][y])->simbolo = 'X';
    }
}
void * disparoGrande(int x, int y);
void * disparoLineal(int x, int y);
void * disparoRadar(int x, int y);
void * disparo500KG(int x, int y);



