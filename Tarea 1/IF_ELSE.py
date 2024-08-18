import re
import diccs

def condicional(linea, i, condicion_OK):
    variable_entera = re.search(r"if\s*\(\s*(\$_\w+)\s*\)", linea)

    if variable_entera:
        variable = variable_entera.group(1)
        variable = re.search(r"\$_(.+)", variable).group(1)
        if not diccs.verificar_existencia(variable):
            print("Error: La variable de nombre '" + variable_entera.group(1)+ "' no ha sido definida o no se le ha asignado valor. Revisar la linea " +str(i))
            return 0
        
    else:
        print("Error: Variable mal definida. Revisar la linea "+str(i))
        return 0

    if type(diccs.dicc_variables[variable]) != bool:
        print("Error: Variable solo puede ser booleana dentro de una condicion. Revisar linea "+str(i))
        return 0



    if diccs.dicc_variables[variable]:
        condicion_OK[0] = True
    else:
        condicion_OK[0] = False

    return 1

