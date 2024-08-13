import re
import diccs
#Declaracion funcion DEFINE
#Agrega una variable nueva a un diccionario. Si ya existe -> error 'Variable ya definda'

def DEFINE(variable, i):
    verificar = re.match(r".*\$_[A-Z](\S+)$", variable)
    if verificar:
        variable = re.search(r"\$_(.+)", variable)
        variable = variable.group(1)
        if variable not in diccs.dicc_variables:
            diccs.dicc_variables[variable] = None
        else:
            print("Error en linea "+str(i)+": Variable ya definida")
            return 0
    else:
        print("Error durante ejecucion: Mala Sintaxis de variable en DEFINE en la linea "+str(i))
        return 0
    return 1
