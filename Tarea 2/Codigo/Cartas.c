#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include "Cartas.h"

Mano Cartas;
int Flag500KG = 1;
int cartaElegida;
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
    scanf("%d", &cartaElegida);
    while(cartaElegida <= 0 || cartaElegida > 5){
        printf("Ingres una carta valida: ");
        scanf("%d", &cartaElegida);
    }
    int x, y;
    printf("Coordenada X del disparo: ");
    scanf("%d", &x);
    while(x < 0 || x>=T){
        printf("Ingrese una coordenada X dentro del tablero\n");
        printf("Coordenada X del disparo: ");
        scanf("%d", &x);
    }
    printf("Coordenada Y del disparo: ");
    scanf("%d", &y); 
    while(y < 0 || y>=T){
        printf("Ingrese una coordenada Y dentro del tablero\n");
        printf("Coordenada Y del disparo: ");
        scanf("%d", &y);
    }
    printf("Disparo a las coordenadas (%d,%d)\n", x,y);

    void * nuevaFuncion = ((void *(*)(int, int))Cartas.carta[cartaElegida-1])(x, y);
    Cartas.carta[cartaElegida-1] = nuevaFuncion;
}   

void * disparoSimple(int x, int y){
    if (x == -1 && y == -1) return "\033[0;36mDisparo simple\033[0m";
    //Lógica de disparo:
    if(((casilla * ) tablero[x][y])->barco == 1){
        ((casilla * ) tablero[x][y])->simbolo = 'H';
        ((casilla * ) tablero[x][y])->barco = 2;
        printf("Le has dado a un barco en (%d, %d)\n",x, y);
        cantAciertos--;
        restarParticion(((casilla * ) tablero[x][y])->n_barco);
    }else if(((casilla * ) tablero[x][y])->barco == 0){
        ((casilla * ) tablero[x][y])->simbolo = 'X';
    }


    
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
    if (x == -1 && y == -1) return "\033[0;35mDisparo grande\033[0m";

    x--;
    y--;
    for (int i = 0; i<3; i++){
        for(int j = 0; j< 3; j++){
            if(x+j >= 0 && x+j<T && y+i>=0 && y+i<T){
                if(((casilla * ) tablero[x+j][y+i])->barco == 1){
                    ((casilla * ) tablero[x+j][y+i])->simbolo = 'H';
                    ((casilla * ) tablero[x+j][y+i])->barco = 2;
                    printf("Le has dado a un barco en (%d, %d)\n",x+j, y+i);
                    cantAciertos--;
                    restarParticion(((casilla * ) tablero[x+j][y+i])->n_barco);
                    //Este es la posicion del arrego del barco que debemos modificar (Restar 1 a particiones_activas)
                }else if(((casilla * ) tablero[x+j][y+i])->barco == 0){
                    ((casilla * ) tablero[x+j][y+i])->simbolo = 'X';
                }
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
    } else if(nuevaCarta <= 98) {
        return (void *)disparoRadar;
    } else {
        if(Flag500KG){
            return (void *)disparo500KG;
        }else{
            return (void *)disparoSimple;
        }
        
    }
    

}
void * disparoLineal(int x, int y){
    if (x == -1 && y == -1) return "\033[0;33mDisparo lineal\033[0m";
    int orientacion;
    printf("¿Vertical (0) u horizontal (1)?: ");
    scanf("%d", &orientacion);

    if(orientacion) {
        x=x-2;
    }else {
        y=y-2;
    }
    
    for(int i = 0; i<5; i++){
        if(orientacion){
            if(x+i >= 0 && x+i<T){
                if(((casilla * ) tablero[x+i][y])->barco == 1){
                    ((casilla * ) tablero[x+i][y])->simbolo = 'H';
                    ((casilla * ) tablero[x+i][y])->barco = 2;
                    printf("Le has dado a un barco en (%d, %d)\n",x+i, y);
                    cantAciertos--;
                    restarParticion(((casilla * ) tablero[x+i][y])->n_barco);
                }else if(((casilla * ) tablero[x+i][y])->barco == 0){
                    ((casilla * ) tablero[x+i][y])->simbolo = 'X';
                }
                }
            }else if(y+i>=0 && y+i<T){
                if(((casilla * ) tablero[x][y+i])->barco == 1){
                    ((casilla * ) tablero[x][y+i])->simbolo = 'H';
                    ((casilla * ) tablero[x][y+i])->barco = 2;
                    printf("Le has dado a un barco en (%d, %d)\n",x+i, y);
                    cantAciertos--;
                    restarParticion(((casilla * ) tablero[x][y+i])->n_barco);
                }else if(((casilla * ) tablero[x][y+i])->barco == 0){
                    ((casilla * ) tablero[x][y+i])->simbolo = 'X';
                }
            }
    }

    //Logica de azar:
    int nuevaCarta = rand() % 100;
    if(nuevaCarta <= 84) {
        return (void *)disparoSimple;
    } else if(nuevaCarta <= 89) {
        return (void *)disparoGrande;
    } else if(nuevaCarta <= 91) {
        return (void *)disparoLineal;
    } else if(nuevaCarta <= 97) {
        return (void *)disparoRadar;
    } else {
        if(Flag500KG){
            return (void *)disparo500KG;
        }else{
            return (void *)disparoSimple;
        }
    }


}
void * disparoRadar(int x, int y){
    if (x == -1 && y == -1) return "\033[0;32mDisparo radar\033[0m";
    x = x-2;
    y = y -2;
    for (int i = 0; i<5; i++){
        for(int j = 0; j< 5; j++){
            if(x+j >= 0 && x+j<T && y+i>=0 && y+i<T){
                if(((casilla * ) tablero[x+j][y+i])->barco == 1){
                    ((casilla * ) tablero[x+j][y+i])->simbolo = 'B';
                    printf("Has encontrado un barco en (%d, %d)\n",x+j, y+i);
                }
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
    } else if(nuevaCarta <= 98) {
        return (void *)disparoRadar;
    } else {
        if(Flag500KG){
            return (void *)disparo500KG;
        }else{
            return (void *)disparoSimple;
        }
    }
}
void * disparo500KG(int x, int y){
    if (x == -1 && y == -1) return "\033[0;31mDisparo 500KG\033[0m";

    
    x = x-5;
    y = y-5;
    for (int i = 0; i<11; i++){
        for(int j = 0; j< 11; j++){
            if(x+j >= 0 && x+j<T && y+i>=0 && y+i<T){
                if(((casilla * ) tablero[x+j][y+i])->barco == 1){
                    ((casilla * ) tablero[x+j][y+i])->simbolo = 'H';
                    ((casilla * ) tablero[x+j][y+i])->barco = 2;
                    printf("Le has dado a un barco en (%d, %d)\n",x+j, y+i);
                    cantAciertos--;
                    restarParticion(((casilla * ) tablero[x+j][y+i])->n_barco);
                }else  if(((casilla * ) tablero[x+j][y+i])->barco == 0){
                    ((casilla * ) tablero[x+j][y+i])->simbolo = 'X';
                }
            }
        }
    }

    Flag500KG = 0;
    //Cartas.disponibles --;

    return (void *)canionDestruido; //Aquí debo retornar algo para des habilitar ese cañon!!
}
void * canionDestruido(int x, int y){
    if (x == -1 && y == -1) return "\033[0;31mCañón destruido\033[0m";
    printf("Este cañón no se puede utilizar, selecciona otra carta\n");
    usarCarta();
    return (void *)canionDestruido;

}

void liberarMazo(){
    if(Cartas.carta == NULL) return;
    free(Cartas.carta);
    Cartas.carta = NULL;
    Cartas.disponibles = 0; 

}