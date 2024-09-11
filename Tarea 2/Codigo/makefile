CC = gcc #Se selecciona el compilador
CFLAGS = -Wall -g #Vi en internet que esto había que ponerlo
OBJ = Tablero.o Cartas.o CityDefender.o


all: ejecutable

ejecutable: $(OBJ)
	$(CC) -o ejecutable $(OBJ)



Tablero.o: Tablero.c  Tablero.h
	$(CC) $(CFLAGS) -c Tablero.c

Cartas.o: Cartas.c  Cartas.h
	$(CC) $(CFLAGS) -c Cartas.c

CityDefender.o: CityDefender.c Tablero.h Cartas.h
	$(CC) $(CFLAGS) -c CityDefender.c

clean:
	rm -f *.o ejecutable