import re
from diccs import dicc_variables

#Declaracion funcion DEFINE
#Agrega una variable nueva a un diccionario. Si ya existe -> error 'Variable ya definda'
def DEFINE(variable, i):
    variable = re.search(r"\$_(.+)", variable)
    variable = variable.group(1)

    if variable not in dicc_variables:
        dicc_variables[variable] = ''
    else:
        print("Error en linea "+str(i)+": Variable ya definida")
        #Llamar a error
    pass