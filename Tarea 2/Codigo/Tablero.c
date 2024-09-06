#include <stdlib.h>
#include<stdio.h>
#include <time.h>
#include "Tablero.h"


void *** tablero;
int T;

void inicializarTablero(int tamano){
    tablero = malloc(tamano *sizeof(void **));
    T = tamano;
    for (int i = 0; i < tamano; i++){
        tablero[i] = malloc(tamano * sizeof(void *));
        for(int j = 0; j< tamano; j++){
            tablero[i][j] = malloc(sizeof(casilla));
            ((casilla * ) tablero[i][j])->simbolo = 'O';
            ((casilla * ) tablero[i][j])->barco =  0;
        }
    }
}

void mostrarTablero(){
    printf("\n    ");
    for(int i = 0; i <T; i++){printf("%d    ", i);}
    printf("\n    ");
    for(int i = 0; i <T; i++){printf("-    ");}
    printf("\n");
    for(int i = 0; i<T; i++){
        printf("%d| ", i);
        for(int j = 0; j<T; j++){
            printf("(%c", ((casilla * ) tablero[j][i])->simbolo);
            printf("%d) ", ((casilla * ) tablero[j][i])->barco);
        }
        printf("\n");
    }

}

void crearBarco(Barco *bar){
    int orientacion = rand() %2;//0: vertical, 1: horizontal    
    int Flag = 0;
    if(orientacion == 0){ //vertical
        while(Flag == 0){
            int X = rand() % T;
            int Y = rand() % T;
            int Ymov = Y;
            Flag = 1;
            int i = 0;

            while(i < bar->largo && Flag == 1){
                if(Ymov >= T){
                    Flag = 0;
                }
                if(Flag == 1 && ((casilla * ) tablero[X][Ymov])->barco == 1){
                    Flag = 0;
                }
                Ymov++;
                i++;
            }

            if(Flag == 1){
                for(int i = 0; i < bar->largo; i++){
                    ((casilla * ) tablero[X][Y+i])->barco = 1;
                    ((casilla * ) tablero[X][Y+i])->simbolo = 'B';
                }
                
            }
        }

    }else{ //horizontal
        while(Flag == 0){
            int X = rand() % T;
            int Y = rand() % T;
            int Xmov = X;
            Flag = 1;
            int i = 0;

            while(i < bar->largo && Flag == 1){
                if(Xmov >= T){
                    Flag = 0;
                }
                if(Flag == 1 && ((casilla * ) tablero[Xmov][Y])->barco == 1){
                    Flag = 0;
                }
                Xmov++;
                i++;
            }

            if(Flag == 1){
                for(int i = 0; i < bar->largo; i++){
                    ((casilla * ) tablero[X+i][Y])->barco = 1;
                    ((casilla * ) tablero[X+i][Y])->simbolo = 'B';
                }
                
            }
        }
    }
}

/*

*/
