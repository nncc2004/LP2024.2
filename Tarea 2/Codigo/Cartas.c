#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include "Cartas.h"

Mano Cartas;

void inicializarMazo(){
    Cartas.carta = malloc(5* sizeof(void* ));
    Cartas.disponibles = 5;

    for(int i = 0; i < 5; i++){
        Cartas.carta[i] = (void*)disparoSimple;
    }
}


void mostrarMazo(){
    printf("Cartas disponibles:\n| ");
    for(int i = 0; i < Cartas.disponibles; i++){
        char *nombre = ((void *(*)(int, int))Cartas.carta[i])(-1, -1);
        printf("%s (%d) | ", nombre, i+1);
    }
    printf("\n");
}


void usarCarta(){
    printf("Ingrese la carta a usar: ");
    int cartaElegida;
    scanf("%d", &cartaElegida);

    int x, y;
    printf("Coordenada X del disparo: ");
    scanf("%d", &x);
    printf("Coordenada Y del disparo: ");
    scanf("%d", &y); 
    int nuevaCarta = rand() % 100;
    printf("Disparo a las coordenadas (%d,%d)\n", x,y);

    void * nuevaFuncion = ((void *(*)(int, int))Cartas.carta[cartaElegida-1])(x, y);
    Cartas.carta[cartaElegida-1] = nuevaFuncion;
}   


void * disparoSimple(int x, int y){
    if (x == -1 && y == -1) return "Disparo simple";

    //Lógica de disparo:
    if(((casilla * ) tablero[x][y])->barco == 1){
        ((casilla * ) tablero[x][y])->simbolo = 'H';
        printf("Le has dado a un barco en (%d, %d)\n",x, y);
        //((casilla * ) tablero[x][y])->n_barco
        //Este es la posicion del arrego del barco que debemos modificar (Restar 1 a particiones_activas)
    }else{
        ((casilla * ) tablero[x][y])->simbolo = 'X';
    }
    printf("Se logró disparar\n");
    //Logica de azar:
    int nuevaCarta = rand() % 100;
        if(nuevaCarta <= 64) {
        return (void *)disparoSimple;
    } else if(nuevaCarta <= 84) {
        return (void *)disparoGrande;
    } else if(nuevaCarta <= 89) {
        return (void *)disparoLineal;
    } else {
        return (void *)disparoRadar;
    }

    
    
}
void * disparoGrande(int x, int y){
    if (x == -1 && y == -1) return "Disparo grande";

    x--;
    y--;
    for (int i = 0; i<3; i++){
        for(int j = 0; j< 3; j++){
            if(x+j >= 0 && x+j<T && y+i>=0 && y+i<T){
                if(((casilla * ) tablero[x+j][y+i])->barco == 1){
                    ((casilla * ) tablero[x+j][y+i])->simbolo = 'H';
                    printf("Le has dado a un barco en (%d, %d)\n",x+j, y+i);
                    //((casilla * ) tablero[x+j][y+i])->n_barco
                    //Este es la posicion del arrego del barco que debemos modificar (Restar 1 a particiones_activas)
                }else{
                    ((casilla * ) tablero[x+j][y+i])->simbolo = 'X';
                }
                printf("Se logró disparar\n");
            }
        }
    }
    //Logica de azar:
    int nuevaCarta = rand() % 100;
    if(nuevaCarta <= 79) {
        return (void *)disparoSimple;
    } else if(nuevaCarta <= 82) {
        return (void *)disparoGrande;
    } else if(nuevaCarta <= 92) {
        return (void *)disparoLineal;
    } else if(nuevaCarta <= 97) {
        return (void *)disparoRadar;
    } else {
        return (void *)disparo500KG;
    }

}
void * disparoLineal(int x, int y){
    if (x == -1 && y == -1) return "Disparo lineal";
}
void * disparoRadar(int x, int y){
    if (x == -1 && y == -1) return "Disparo radar";
}
void * disparo500KG(int x, int y){
    if (x == -1 && y == -1) return "Disparo 500KG";
}



/*


*/