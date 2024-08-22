import re
import diccs

def condicional(linea, i, condicion_OK):
    '''
    ***
    linea : string
    i : entero
    condicion_OK: arreglo
    ...
    ***
    0: (int) en caso de error
    1: (int) en caso de exito
    ***
    La funcion tiene como objetivo evaluar la condicion dentro de los parentesis de un if.
    Comienza verificando sintaxis y extrayendo el nombre de la varible (si no hay -> error)
    Si la variable existe y tiene un valor booleano, asigna ese valor al elemento 0 de la lista, 
    que pasa 'por referencia', de esta forma desde el main se tiene el valor del condicional.
    Si se encuentra cualquier error durante la ejecucion, retorna 0. Sino, retorna 1.
    '''
    variable_entera = re.search(r"if\s*\(\s*(\$_\w+)\s*\)", linea)

    if variable_entera:
        variable = variable_entera.group(1)
        variable = re.search(r"\$_(.+)", variable).group(1)
        if not diccs.verificar_existencia(variable):
            print("Error de sintaxis: La variable de nombre '" + variable_entera.group(1)+ "' no ha sido definida o no se le ha asignado valor. Revisar la linea " +str(i))
            return 0
        
    else:
        print("Error de sintaxis: Variable mal definida. Revisar la linea "+str(i))
        return 0

    if type(diccs.dicc_variables[variable]) != bool:
        print("Error de tipo: Variable solo puede ser booleana dentro de una condicion. Revisar linea "+str(i))
        return 0



    if diccs.dicc_variables[variable]:
        condicion_OK[0] = True
    else:
        condicion_OK[0] = False

    return 1

