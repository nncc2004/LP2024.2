#ifndef H_CARTAS
#define H_CARTAS

#include "Tablero.h"

typedef struct Mano
{
    void ** carta;
    int disponibles;
} Mano;

extern Mano Cartas;
void inicializarMazo();
void mostrarMazo();
void usarCarta();

void * disparoSimple(int x, int y);
void * disparoGrande(int x, int y);
void * disparoLineal(int x, int y);
void * disparoRadar(int x, int y);
void * disparo500KG(int x, int y);

//Propias
extern void *** tablero;
extern int T;
#endif