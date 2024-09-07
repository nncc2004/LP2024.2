#ifndef H_TABLERO
#define H_TABLERO

extern void *** tablero;

void inicializarTablero(int tamano);
void mostrarTablero();

//Metodos propios:
typedef struct casilla{
    char simbolo;
    int barco;
    int n_barco;
} casilla;
typedef struct Barco{
    int largo;
    int particiones_activas; //Se le deberá restar 1 cada vez que le den a una casilla que sea del barco
    int pos_arreglo;
}Barco;
void crearBarco(Barco *bar);
void iniciarBarcos(int dificultad);
void liberarTablero();
void liberarBarcos();
#endif
