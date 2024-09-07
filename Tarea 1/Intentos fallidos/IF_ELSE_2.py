import re
import diccs

def condicional(linea, i, condicion_OK, nivel):
    variable_entera = re.search(r"IF\s*\(\s*(\$_\w+)\s*\)", linea)

    if variable_entera:
        variable = variable_entera.group(1)
        variable = re.search(r"\$_(.+)", variable).group(1)
        if not diccs.verificar_existencia(variable):
            print("Variable No Definida: La variable de nombre '" + variable_entera.group(1)+ "' no ha sido definida o no se le ha asignado valor en la linea " +str(i))
            return 0
        
    else:
        print("Variable mal definida en la linea "+str(i))
        return 0

    if type(diccs.dicc_variables[variable]) != bool:
        print("Variable solo puede ser booleana. Revisar linea "+str(i))
        return 0



    if diccs.dicc_variables[variable]:
        condicion_OK[nivel] = True
    else:
        condicion_OK[nivel] = False

    return 1


#En el else, volver condicion_ok siempre a True