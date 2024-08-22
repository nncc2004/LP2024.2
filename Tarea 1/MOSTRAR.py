import re
import diccs


def MOSTRAR(linea, i, output):
    '''
    ***
    linea : string
    i : entero
    output: entero (nombre archivo para escribir)
    ...
    ***
    0: (int) en caso de error
    1: (int) en caso de exito
    ***
    La funcion está destinada a escribir el valor de una variable en el .txt segun 
    las indicaciones de la tarea. Lo primero que hace es verificar la sintaxis y en caso de
    no habre errores busca la variable dentro del comando para susbtraer su valor desde el diccionario
    y escribirla en el texto. En caso de que la variable no exista, no tenga valor definido o no cumpla con el formato,
    se muestra el mensaje de error y se retorna 0. En caso de no haber error se retorna 1
    '''
    Verificar_formato = r"^(MOSTRAR\((\s*))(\S)+((\s*)\))$"

    if re.match(Verificar_formato, linea):
        variable = re.search(r"(\((\s*))(\S+)((\s*)\))", linea).group(3)
        nombre_variable = re.search(r"(\((\s*))(\$_)(\S+)((\s*)\))", linea).group(4)
        if not diccs.verificar_existencia(nombre_variable) or diccs.dicc_variables[nombre_variable] == None:
                print("Error de sintaxis: La variable de nombre '" + variable+ "' no ha sido definida o no se le ha asignado valor. Revisar la linea " +str(i))
                return 0
        output.write(str(diccs.dicc_variables[nombre_variable])+"\n")
        
    else:
        print("Error de sintaxis: Mostrar debe contener una variable valida. Revisar la linea ", str(i))
        return 0

    return 1