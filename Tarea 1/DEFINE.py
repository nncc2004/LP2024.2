import re
import diccs
#Declaracion funcion DEFINE
#Agrega una variable nueva a un diccionario. Si ya existe -> error 'Variable ya definda'

def DEFINE(linea, i):
    '''
    ***
    linea: string
    i: entero
    ...
    ***
    0: (int) en caso de error
    1: (int) en caso de exito
    ***
    La funcion tiene como objetivo ingresar las variables del comando 'DEFINE' en el diccionario
    Lo primero que hace es verificar el formato de la palabra para que cumpla con los requisitos
    para que sea una variable valida. Luego verifica que no este en el diccionario y en tal caso la ingresa
    como key y con un value de 'none'
    '''

    verificar = re.match(r".*\$_[A-Z](\S+)$", linea)
    if verificar:
        variable = re.search(r"\$_(.+)", linea)
        variable = variable.group(1)
        if variable not in diccs.dicc_variables:
            diccs.dicc_variables[variable] = None
        else:
            print("Error de sintaxis: Variable ya definida. Revisar la linea "+str(i) )
            return 0
    else:
        print("Error de sintaxis: Variable mal definida en la linea "+str(i))
        return 0
    return 1
