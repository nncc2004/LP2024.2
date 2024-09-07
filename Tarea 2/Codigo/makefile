# Variables
CC = gcc        # El compilador a usar
CFLAGS = -Wall -g  # Opciones del compilador (activar todas las advertencias y modo depuración)

# Objetivo: El ejecutable final
all: CityDefender

# Cómo construir el ejecutable a partir de los archivos objeto
CityDefender: CityDefender.o Tablero.o Cartas.o
	$(CC) $(CFLAGS) -o CityDefender CityDefender.o Tablero.o Cartas.o

# Cómo construir cada archivo objeto a partir de los archivos .c y .h
CityDefender.o: CityDefender.c Cartas.h Tablero.h
	$(CC) $(CFLAGS) -c CityDefender.c

Tablero.o: Tablero.c Tablero.h
	$(CC) $(CFLAGS) -c Tablero.c

Cartas.o: Cartas.c Cartas.h Tablero.h
	$(CC) $(CFLAGS) -c Cartas.c

# Limpiar los archivos generados (.o y ejecutables)
clean:
	rm -f *.o CityDefender
