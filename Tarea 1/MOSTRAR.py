import re
import diccs


def MOSTRAR(linea, i, output):

    Verificar_formato = r"^(MOSTRAR\()(\$_(\S)+)(\))$"

    if re.match(Verificar_formato, linea):
        variable = re.search(r"(\()(\S+)(\))", linea).group(2)
        nombre_variable = re.search(r"(\()(\$_)(\S+)(\))", linea).group(3)
        if not diccs.verificar_existencia(nombre_variable) or diccs.dicc_variables[nombre_variable] == None:
                print("Variable No Definida: La variable de nombre '" +variable+ "' no ha sido definida o no se le ha asignado valor en la linea " +str(i))
                return 0
        output.write(str(diccs.dicc_variables[nombre_variable])+"\n")
        
    else:
        print("Syntax error en linea "+str(i)+": "+linea+". Mostrar debe contener una variable")
        return 0

    return 1