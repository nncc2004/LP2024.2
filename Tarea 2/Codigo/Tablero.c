#include <stdlib.h>
#include<stdio.h>
#include <time.h>
#include "Tablero.h"


void *** tablero;
Barco * BarcosTablero;
int T;

void inicializarTablero(int tamano){
    tablero = malloc(tamano *sizeof(void **));
    T = tamano;
    for (int i = 0; i < tamano; i++){
        tablero[i] = malloc(tamano * sizeof(void *));
        for(int j = 0; j< tamano; j++){
            tablero[i][j] = malloc(sizeof(casilla));
            ((casilla * ) tablero[i][j])->simbolo = '?';
            ((casilla * ) tablero[i][j])->barco =  0;
        }
    }
}

void mostrarTablero(){
    printf("\n     ");
    for(int i = 0; i <T; i++){
        if(i < 10){
            printf("%d   ", i);
        }else{
            printf("%d  ", i);
        }
    }
    printf("\n     ");
    for(int i = 0; i <T; i++){printf("-   ");}
    printf("\n");
    for(int i = 0; i<T; i++){
        if(i < 10){
            printf("%d | ", i);
        }else{
            printf("%d| ", i);
        }
        for(int j = 0; j<T; j++){
            if(((casilla * ) tablero[j][i])->simbolo == 'X'){
                printf("(\033[1;34m%c\033[0m) ", ((casilla * ) tablero[j][i])->simbolo);
            }else if(((casilla * ) tablero[j][i])->simbolo == 'H'){
                printf("(\033[1;31m%c\033[0m) ", ((casilla * ) tablero[j][i])->simbolo);
            }else if(((casilla * ) tablero[j][i])->simbolo == 'B'){
                printf("(\033[1;32m%c\033[0m) ", ((casilla * ) tablero[j][i])->simbolo);
            }else{
                printf("(%c) ", ((casilla * ) tablero[j][i])->simbolo);
            }


        }
        printf("\n\n");
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
                } else{
                    if(((casilla * ) tablero[X][Ymov])->barco == 1){
                        Flag = 0;
                    }
                }
                Ymov++;
                i++;
            }

            if(Flag == 1){
                for(int i = 0; i < bar->largo; i++){
                    ((casilla * ) tablero[X][Y+i])->barco = 1;
                    //((casilla * ) tablero[X][Y+i])->simbolo = 'B';
                    ((casilla * ) tablero[X][Y+i])->n_barco = bar->pos_arreglo;
                    
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
                } else {
                    if(((casilla * ) tablero[Xmov][Y])->barco == 1){
                        Flag = 0;
                    }
                }
                Xmov++;
                i++;
            }

            if(Flag == 1){
                for(int i = 0; i < bar->largo; i++){
                    ((casilla * ) tablero[X+i][Y])->barco = 1;
                    //((casilla * ) tablero[X+i][Y])->simbolo = 'B';
                    ((casilla * ) tablero[X+i][Y])->n_barco = bar->pos_arreglo;
                }
                
            }
        }
    }
}

void iniciarBarcos(int dificultad){
    switch (dificultad)
    {
    case 1:
        BarcosTablero = malloc(5 *sizeof(Barco));
        //1x2
        BarcosTablero[0].largo = 2;
        BarcosTablero[0].particiones_activas = 2;
        BarcosTablero[0].pos_arreglo = 0;

        //1x2
        BarcosTablero[1].largo = 2;
        BarcosTablero[1].particiones_activas = 2;
        BarcosTablero[1].pos_arreglo = 1;

        //1x3
        BarcosTablero[2].largo = 3;
        BarcosTablero[2].particiones_activas = 3;
        BarcosTablero[2].pos_arreglo = 2;

        //1x4
        BarcosTablero[3].largo = 4;
        BarcosTablero[3].particiones_activas = 4;
        BarcosTablero[3].pos_arreglo = 3;

        //1x5
        BarcosTablero[4].largo = 5;
        BarcosTablero[4].particiones_activas = 5;
        BarcosTablero[4].pos_arreglo = 4;
    
        for(int i = 0; i < 5; i++){
            crearBarco(&BarcosTablero[i]);
        }
        
        break;
    case 2: 
        BarcosTablero = malloc(7 *sizeof(Barco));
        //1x2
        BarcosTablero[0].largo = 2;
        BarcosTablero[0].particiones_activas = 2;
        BarcosTablero[0].pos_arreglo = 0;

        //1x2
        BarcosTablero[1].largo = 2;
        BarcosTablero[1].particiones_activas = 2;
        BarcosTablero[1].pos_arreglo = 1;

        //1x2
        BarcosTablero[2].largo = 2;
        BarcosTablero[2].particiones_activas = 2;
        BarcosTablero[2].pos_arreglo = 2;

        //1x3
        BarcosTablero[3].largo = 3;
        BarcosTablero[3].particiones_activas = 3;
        BarcosTablero[3].pos_arreglo = 3;

        //1x3
        BarcosTablero[4].largo = 3;
        BarcosTablero[4].particiones_activas = 3;
        BarcosTablero[4].pos_arreglo = 4;

        //1x4
        BarcosTablero[5].largo = 4;
        BarcosTablero[5].particiones_activas = 4;
        BarcosTablero[5].pos_arreglo = 5;

        //1x5
        BarcosTablero[6].largo = 5;
        BarcosTablero[6].particiones_activas = 5;
        BarcosTablero[6].pos_arreglo = 6;

        for(int i = 0; i < 7; i++){
            crearBarco(&BarcosTablero[i]);
        }
        break;
    case 3: 
        BarcosTablero = malloc(9 *sizeof(Barco));
        //1x2
        BarcosTablero[0].largo = 2;
        BarcosTablero[0].particiones_activas = 2;
        BarcosTablero[0].pos_arreglo = 0;

        //1x2
        BarcosTablero[1].largo = 2;
        BarcosTablero[1].particiones_activas = 2;
        BarcosTablero[1].pos_arreglo = 1;

        //1x2
        BarcosTablero[2].largo = 2;
        BarcosTablero[2].particiones_activas = 2;
        BarcosTablero[2].pos_arreglo = 2;

        //1x3
        BarcosTablero[3].largo = 3;
        BarcosTablero[3].particiones_activas = 3;
        BarcosTablero[3].pos_arreglo = 3;

        //1x3
        BarcosTablero[4].largo = 3;
        BarcosTablero[4].particiones_activas = 3;
        BarcosTablero[4].pos_arreglo = 4;

        //1x4
        BarcosTablero[5].largo = 4;
        BarcosTablero[5].particiones_activas = 4;
        BarcosTablero[5].pos_arreglo = 5;

        //1x4
        BarcosTablero[6].largo = 4;
        BarcosTablero[6].particiones_activas = 4;
        BarcosTablero[6].pos_arreglo = 6;

        //1x5
        BarcosTablero[7].largo = 5;
        BarcosTablero[7].particiones_activas = 5;
        BarcosTablero[7].pos_arreglo = 7;

        //1x5
        BarcosTablero[8].largo = 5;
        BarcosTablero[8].particiones_activas = 5;
        BarcosTablero[8].pos_arreglo = 8;

        for(int i = 0; i < 9; i++){
            crearBarco(&BarcosTablero[i]);
        }
        break;
    default:
        break;
    }
}

void restarParticion(int pos){
    BarcosTablero[pos].particiones_activas--;

    if(BarcosTablero[pos].particiones_activas == 0){
        printf("Barco hundido! El barco tenía un largo de %d\n", BarcosTablero[pos].largo);
    }
}

void mapaVictoria(){
    for(int i = 0; i<T; i++){
        for(int j = 0; j<T; j++){
            if(((casilla * ) tablero[j][i])->simbolo == '?') ((casilla * ) tablero[j][i])->simbolo = 'X';
        }
    }
}

void mapaDerrota(){
    for(int i = 0; i<T; i++){
        for(int j = 0; j<T; j++){
            if(((casilla * ) tablero[j][i])->simbolo == 'X') ((casilla * ) tablero[j][i])->simbolo = '?';
            if(((casilla * ) tablero[j][i])->barco == 1) ((casilla * ) tablero[j][i])->simbolo = 'B';
        }
    }
}


void liberarTablero() {
    if (tablero == NULL) return;

    for (int i = 0; i < T; i++) {
        if (tablero[i] != NULL) { 
            for (int j = 0; j < T; j++) {
                if (tablero[i][j] != NULL) { 
                    free(tablero[i][j]);
                    tablero[i][j] = NULL; 
                }
            }
            free(tablero[i]); 
            tablero[i] = NULL; 
        }
    }

    free(tablero); 
    tablero = NULL;
}

void liberarBarcos(){
    if(BarcosTablero == NULL) return;
    free(BarcosTablero);
    BarcosTablero = NULL;

}