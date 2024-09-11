#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include "Cartas.h"

Mano Cartas;
int Flag500KG = 1;
int cartaElegida;
void inicializarMazo(){
    /*
    
    No tiene parametros

    //////////////////////////////////////

    Esta funcion se encarga de inicializar la mano  de forma dinámica 
    con un tamaño de 5 (por 5 cartas), asignando cada puntero a la funcion
    disparoSimple.

    //////////////////////////////////////

    Retorno: detalle

 */
    Cartas.carta = malloc(5* sizeof(void* ));
    Cartas.disponibles = 5;

    for(int i = 0; i < 5; i++){
        Cartas.carta[i] = (void*)disparoSimple;
    }
}


void mostrarMazo(){
    /*
    No tiene parametros

    //////////////////////////////////////

    Esta funcion se encarga de imprimir por pantalla la información de las cartas disponibles.
    Lo hace llamando a la función asignada a cada carta, con valores x = y = -1, lo que hace que 
    cada función retorne su nombre. Al recibir el retorno, lo muestra por pantalla junto con su numero
    de carta para que el usuario pueda elegir.

    //////////////////////////////////////

    No hay retorno

 */
    printf("Cartas disponibles:\n| ");
    for(int i = 0; i < Cartas.disponibles; i++){
        char *nombre = ((void *(*)(int, int))Cartas.carta[i])(-1, -1);
        printf("%s (%d) | ", nombre, i+1);
    }
    printf("\n");
}


void usarCarta(){
    /*
    No tiene parametros

    //////////////////////////////////////

    Esta funcion se encarga de recibir la opcion elegida por el usuario, pedir coordenadas x,y
    y llamar a la carta correspondiente para ejecutar su función . 
    La función se asegura de que el valor ingresado por el usuario esté dentro de los valores aceptados 
    Por último, al llamar a la función a tavés del puntero de la carta, le asigna el valor 
    del retorno a una nueva variable de tipo (void *), para que este valor sea reasignado 
    a la carta en la posicion elegida por el usuario.
    (en resumen, cambia la cafrta antigua por la nueva)

    //////////////////////////////////////

    No hay retorno.

    */
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
    /*
    int x: recibe una coordenada x del tablero
    int y: recibe una coordenada y del tablero

    //////////////////////////////////////

    Esta funcion se encarga de ejecutar el disparo simple. 
    inicialmente verifica si los valores son x = y = -1 porque ese caso
    retorna el nombre de la funcion.
    Para la logica del disparo, busca la casilla y revisa si el valor de barco es
    igual a 1 (hay barco y no se le ha disparado) o igual a 0 (no hay barco)

    En el caso de haber barco y que no se le haya disparado antes (1) cambia el valor a 
    2 y el simbolo a 'H' para que luego se imprima en rojo. Además muestra por pantalla
    que se ha acertado el tiro y llama a la función restarParticion() con la posicion
    del barco al que se le ha acertado

    //////////////////////////////////////

    El retorno es la función de alguno de los otros disparos, como estipulado en el documento

    */
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
    /*
    int x: recibe una coordenada x del tablero
    int y: recibe una coordenada y del tablero

    //////////////////////////////////////

    Esta funcion se encarga de ejecutar el disparo grande.
    lo primero que hace (luego de verifica x = y = -1), es 
    restar -1 a ambas coordenadas para posicionarnos en la esquina sup. derecha
    de lo que será el área del disparo. Luego, con los for, se va recorriendo
    la casilla en un cuadrado de 3X3 y revisando con la misma logica del disparo
    simple para cada espacio (o casilla) del tablero.

    //////////////////////////////////////

    El retorno es la función de alguno de los otros disparos, como estipulado en el documento

    */
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
    /*
    int x: recibe una coordenada x del tablero
    int y: recibe una coordenada y del tablero

    //////////////////////////////////////

    Esta funcion se encarga de ejecutar el disparo Lineal. 
    Luego de verificar el caso x = y = -1, pregunta si el disparo va a ser en vertical
    u horizontal, ya que dependiendo de eso, modifica la variable x o y.
    Sea la que se modifique, esta se posicionará en la primera casilla del area del disparo, y al igual
    que con el disparo grande irá recorriendo el área y ejecutando la misma lógica del disparo simple 
    para cada celda (o casilla) del tablero.

    //////////////////////////////////////

    El retorno es la función de alguno de los otros disparos, como estipulado en el documento

    */
    if (x == -1 && y == -1) return "\033[0;33mDisparo lineal\033[0m";
    int orientacion;
    printf("¿Vertical (0) u horizontal (1)?: ");
    scanf("%d", &orientacion);
    while(orientacion != 0 && orientacion != 1){
        printf("Ingrese un valor correcto\n");
        printf("¿Vertical (0) u horizontal (1)?: ");
        scanf("%d", &orientacion);
    }
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
    /*
    int x: recibe una coordenada x del tablero
    int y: recibe una coordenada y del tablero

    //////////////////////////////////////

    Esta funcion se encarga de ejecutar el disparo radar. 
    Luego de verificar el caso x = y = -1, resta 2 a cada coordenada para posicionarse
    en la esquina sup. derecha de lo que será el área del disparo. Así, va a recorrer, pero esat vez
    cuando encuentre un barco (1) no va a cambiar el valor a 2, sino que únicamente cambiará su símbolo 
    a 'B', así cuando se imprima el tablero se verá que ahí está el barco en verde.

    //////////////////////////////////////

    El retorno es la función de alguno de los otros disparos, como estipulado en el documento

    */
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
    /*
    int x: recibe una coordenada x del tablero
    int y: recibe una coordenada y del tablero

    //////////////////////////////////////

    Esta funcion se encarga de ejecutar el disparo  500KG.
    Luego del caso x = y = -1, la función resta 5 a cada coordenada para posicionarse
    en la esquina sup. derecha de lo que será el área de disparo. Luego, recorre toda el área
    de 11x11 ejecutando la misma lógica del disparo simple para cada casilla. 
    Por ultimo, cambia el valor de la bandera creada como variable global "Flag500KG" a 0 para
    que el resto de las funciones descarten la posibilidad de sacar otra carta de 500KG y en vez
    retornen un disparo simple

    //////////////////////////////////////

    El retorno es la función "canionDestruido"

    */
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
    /*
    int x: recibe una coordenada x del tablero
    int y: recibe una coordenada y del tablero

    //////////////////////////////////////

    Esta funcion se encarga "Bloquear" el cañon que utilizó el disparo de 500kg
    Luego de verificar el caso x = y = -1, imrpime que el cañon no se puede utilizar, y permite
    que el usuario elija otra carta con usarCarta().

    //////////////////////////////////////

    Se retorna a si misma, para que el cañon no cambie a otra carta. 

    */


    if (x == -1 && y == -1) return "\033[0;31mCañón destruido\033[0m";
    printf("Este cañón no se puede utilizar, selecciona otra carta\n");
    usarCarta();
    return (void *)canionDestruido;

}

void liberarMazo(){
    /*
    no tiene parametros

    //////////////////////////////////////

    Esta funcion se encarga de liberar la memoria usada en el mazo de cartas

    //////////////////////////////////////

    No hay retorno

    */
    if(Cartas.carta == NULL) return;
    free(Cartas.carta);
    Cartas.carta = NULL;
    Cartas.disponibles = 0; 

}