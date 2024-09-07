import re
from diccs import dicc_variables   
import separador as S
import IF_ELSE_2 as IF_ELSE

arch = open('archivo.txt', 'r')
output = open('output.txt', 'w')
i = 0
REGEX_DEFINE = r"^(DEFINE)\s+(\S+)$"
REGEX_DP = r"^(DP)\s(.+)$"
REGEX_MOSTRAR = r"^(MOSTRAR\((\s*))(\S)+((\s*)\))$"
REGEX_IF = r"^(IF\(\s*\S+\s*\)\s*\{$)" 
REGEX_ELSE = r"(\}\s*ELSE\s*\{$)"
REGEX_CORCHETE = r"^\}$"

REGEX_0_TAB = r"^(\S)"
REGEX_1_TAB = r"^(\s{4}\S)"
REGEX_2_TAB = r"^(\s{8}\S)"
REGEX_3_TAB = r"^(\s{12}\S)"

condicion_OK = [True, False, False]

# Pendientes: verificar que la cantidad de IF - ELSE y } sean correctas

for linea in arch:
    i+=1
    if re.match(REGEX_0_TAB, linea): 
        nivel = 0
        linea = linea.strip()
        MATCH_IF = re.match(REGEX_IF, linea)
        MATCH_ELSE = re.match(REGEX_ELSE, linea)
        MATCH_CORCHETE = re.match(REGEX_CORCHETE, linea)
        print('nivel:', str(nivel), 'linea:"', linea, '" numero: ', str(i), condicion_OK)
        if not MATCH_IF and not MATCH_ELSE and not MATCH_CORCHETE:
            respuesta = S.separador(linea, i, REGEX_DEFINE, REGEX_DP, REGEX_MOSTRAR, arch, output)
        
        if MATCH_IF:
            respuesta = IF_ELSE.condicional(linea, i, condicion_OK, 0)

        if MATCH_ELSE:
            if condicion_OK[0]:
                condicion_OK[0] = False
            else:
                condicion_OK[0] = True

    elif re.match(REGEX_1_TAB, linea):
        nivel = 1
        linea = linea.strip()
        print('nivel:', str(nivel), 'linea:"', linea, '" numero: ', str(i), condicion_OK)
        MATCH_IF = re.match(REGEX_IF, linea)
        MATCH_ELSE = re.match(REGEX_ELSE, linea)
        MATCH_CORCHETE = re.match(REGEX_CORCHETE, linea)

        if not MATCH_IF and not MATCH_ELSE and not MATCH_CORCHETE and condicion_OK[0]:
            respuesta = S.separador(linea, i, REGEX_DEFINE, REGEX_DP, REGEX_MOSTRAR, arch, output)
        
        if MATCH_IF and condicion_OK[0]:
            respuesta = IF_ELSE.condicional(linea, i, condicion_OK, 1)
            print("hola")

        if MATCH_ELSE and condicion_OK [0]:
            if condicion_OK[1]:
                condicion_OK[1] = False
            else:
                condicion_OK[1] = True
        
        if MATCH_CORCHETE:
            condicion_OK[1] = False

    elif re.match(REGEX_2_TAB, linea):
        nivel = 2
        linea = linea.strip()
        print('nivel:', str(nivel), 'linea:"', linea, '" numero: ', str(i), condicion_OK)
        MATCH_IF = re.match(REGEX_IF, linea)
        MATCH_ELSE = re.match(REGEX_ELSE, linea)
        MATCH_CORCHETE = re.match(REGEX_CORCHETE, linea)
        if not MATCH_IF and not MATCH_ELSE and not MATCH_CORCHETE and condicion_OK[1]:
            respuesta = S.separador(linea, i, REGEX_DEFINE, REGEX_DP, REGEX_MOSTRAR, arch, output)
        
        if MATCH_IF and condicion_OK[1]:
            respuesta = IF_ELSE.condicional(linea, i, condicion_OK, 2)

        if MATCH_ELSE and condicion_OK [1]:
            if condicion_OK[2]:
                condicion_OK[2] = False
            else:
                condicion_OK[2] = True

        if MATCH_CORCHETE:
            condicion_OK[2] = False

    elif re.match(REGEX_3_TAB, linea):
        nivel = 3
        linea = linea.strip()
        print('nivel:', str(nivel), 'linea:"', linea, '" numero: ', str(i), condicion_OK)
        MATCH_IF = re.match(REGEX_IF, linea)
        MATCH_ELSE = re.match(REGEX_ELSE, linea)
        MATCH_CORCHETE = re.match(REGEX_CORCHETE, linea)
        if not MATCH_IF and not MATCH_ELSE and not MATCH_CORCHETE and condicion_OK[2]:
            respuesta = S.separador(linea, i, REGEX_DEFINE, REGEX_DP, REGEX_MOSTRAR, arch, output)
        

    else:
        print("Algo pasó con la indentacion en la linea", str(i))


    if not respuesta:
        break



print('',end='\n\n')
print('Variables almacenadas:')
for llave in dicc_variables:
    valor = str(llave)+": "+str(dicc_variables[llave])
    print(valor)
