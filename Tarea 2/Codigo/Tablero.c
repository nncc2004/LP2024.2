#include <stdlib.h>
#include<stdio.h>
#include <time.h>
#include "Tablero.h"


void *** tablero;
Barco * BarcosTablero;
int T;
int CantidadBarcos;

void inicializarTablero(int tamano){
    /*
    int tamano: Recibe un entero que representa el tamano del tablero a crear

    //////////////////////////////////////

    La función se encarga de inicializar el tablero, con un arreglo de arreglos. a su vez cada "casilla"
    apuntará a un struct de tipo "casilla" para almacenar la información pertinente. 
    Inicialmente se asigna un valor de "?" al simbolo para mostrar y 0 al valor de barco para representar 
    que no hay ningún barco en esa casilla. 

    //////////////////////////////////////

    No hay retorno
    */
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
    /*
    no recibe parámetros

    //////////////////////////////////////

    La función se encarga de recorrer el tablero e ir imprimiento el valor de 
    "simbolo" del struct de tipo casilla en cada una de los espacios de la matriz.

    Además imprime numeros de 0 a T (t = tamano) para ayudar al jugador.

    Mencionar que según el símbolo que se imprime en el tablero, (?, X, H, B)
    cambiará el color del mismo.

    //////////////////////////////////////

    No hay retorno
    */
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
    /*
    Barco *bar: Recibe un struct de tipo Barco desde donde se extraerá la información necesaria
    
    //////////////////////////////////////

    La función se encarga de ubicar barcos en el tablero en posiciones aleatorias, como también
    en orientación aleatoria (vertical u horizontal). Esto lo hace generando coordenadas x,y aleatorias
    en valores que estén dentro del tablero y revisando si la linea del largo del barco está "disponible"
    según si la casilla tiene el atributo "barco" en 0 o 1. Si encontró un espacio libre, luego cambia los valores 
    de esas casillas en su atributo barco a 1, además de asignarle a la casilla la posicion del barco en el arreglo
    para luego poder encontrarlo.  
    Si no encontró un espacio completamente disponible, genera otra coordenada x,y aleatoria y empieza de nuevo.
    

    //////////////////////////////////////

    No hay retorno
    */
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
    /*
    int dificultad: Recibe un entero con la dificultad elegida por el usuario

    //////////////////////////////////////

    La función se encarga de crear el arreglo de barcos (que es del tipo struct Barco) del largo
    correspondiente según la dificultad. También crea los barcos y los inserta en el tablero con la función
    "crearBarco()"

    //////////////////////////////////////

    No hay retorno
    */
    switch (dificultad)
    {
    case 1:
        CantidadBarcos = 5;
        BarcosTablero = malloc(CantidadBarcos *sizeof(Barco));
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
        CantidadBarcos = 7;
        BarcosTablero = malloc(CantidadBarcos *sizeof(Barco));
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
        CantidadBarcos = 9;
        BarcosTablero = malloc(CantidadBarcos *sizeof(Barco));
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
    /*
    int pos: recibe un enetero con la posicion del barco al que se le acaba de disparar

    //////////////////////////////////////

    La función se encarga de restar el numero de particiones activas en el barco correspondiente
    una vez le hayan acertado un disparo. Esto lo hace accediendo al arreglo de barcos con la posicion
    recbida como parametro y le resta a "particiones_activas". 
    También, si "particiones_activas" llega a cero, da aviso por pantalla de que se hundió un barco
    y se le resta 1 a la variable "CantidadBarcos"

    //////////////////////////////////////
    No hay retorno
    */
    BarcosTablero[pos].particiones_activas--;

    if(BarcosTablero[pos].particiones_activas == 0){
        printf("Barco hundido! El barco tenía un largo de %d\n", BarcosTablero[pos].largo);
        CantidadBarcos--;
    }
}

void mapaVictoria(){
    /*
    no recibe parámetros

    //////////////////////////////////////

    La función se encarga de cambiar los símbolos del tablero en caso de victoria. Hace que todos los '?' 
    pasen a ser 'X' para que se muestren luego en azul como pantalla de victoria

    //////////////////////////////////////
    No hay retorno
    */
    for(int i = 0; i<T; i++){
        for(int j = 0; j<T; j++){
            if(((casilla * ) tablero[j][i])->simbolo == '?') ((casilla * ) tablero[j][i])->simbolo = 'X';
        }
    }
}

void mapaDerrota(){
    /*
    no recibe parámetros

    //////////////////////////////////////

    La función se encarga de cambiar los símbolos del tablero en caso de derrota. Hace que todos los 'X' 
    pasen a ser ''' para que se muestren luego en blanco como pantalla de derrota. Además, En aquellas casillas 
    en las que hayan barcos a los que no se les haya disparado (El valor de casilla.barco = 1) hace que el simbolo
    sea 'B' para que se muestre también en el tablero en color verde para indicar dónde staban los barcos restantes
    al momento de la derrota. 

    //////////////////////////////////////
    No hay retorno
    */
    for(int i = 0; i<T; i++){
        for(int j = 0; j<T; j++){
            if(((casilla * ) tablero[j][i])->simbolo == 'X') ((casilla * ) tablero[j][i])->simbolo = '?';
            if(((casilla * ) tablero[j][i])->barco == 1) ((casilla * ) tablero[j][i])->simbolo = 'B';
        }
    }
}


void liberarTablero() {
    /*
    no recibe parámetros

    //////////////////////////////////////

    La función se encarga de liberar la memoria dinamica utilizada en la generación del tablero

    //////////////////////////////////////

    No hay retorno
    */
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
    /*
    no recibe parámetros

    //////////////////////////////////////

    La función se encarga de liberar la memoria dinamica utilizada en la generación 
    del arreglo de barcos.
    
    //////////////////////////////////////
    
    No hay retorno
    */
    if(BarcosTablero == NULL) return;
    free(BarcosTablero);
    BarcosTablero = NULL;

}