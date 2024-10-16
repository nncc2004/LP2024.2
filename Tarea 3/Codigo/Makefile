# Variables
JAVAC = javac
JAVA = java
JAVAFAGS = -d bin


SRC_DIR = .
BIN_DIR = ./bin

# Esto para elegir todos los archivos .java que hay 
SOURCES = $(wildcard $(SRC_DIR)/*.java)


all: $(BIN_DIR) $(SOURCES:.java=.class)


%.class: %.java
	$(JAVAC) $(JAVAFAGS) $<



$(BIN_DIR):
	mkdir -p $(BIN_DIR)


#para limpiar con make clean
clean:
	rm -rf $(BIN_DIR)/*.class

#Para ejecutar con make run
run: all
	$(JAVA) -cp $(BIN_DIR) NoJavaSky
