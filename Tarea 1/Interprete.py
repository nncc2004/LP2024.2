import re
from diccs import dicc_variables
import separador as S
import IF_ELSE

################ RESUMEN FUNCIONAMIENTO ################
'''
Esta seccion del codigo se encarga inicialmente de definir los patrones de expresiones regulares.
Además abre o crea los archivos correspondientes e inicializa ciertas variables que luego son utilizadas.
Luego recorre el archivo linea por linea y revisa si es que hay un "Match if", "Match else" o "Match corchete", 
en cuyo caso aplica la logica de una bandera que cambia segun la condicion. Si no es ninguna de las anteriores, llama a 
la funcion separador que esta en el 'separador.py'. 
'''
#DESTACAR: El código sólo funciona para codigos sin anidación. 

arch = open('archivo.txt', 'r')
output = open('output.txt', 'w')
i = 0
REGEX_DEFINE = r"^(DEFINE)\s+(\S+)$"
REGEX_DP = r"^(DP)\s(.+)$"
REGEX_MOSTRAR = r"^(MOSTRAR\((\s*))(\S)+((\s*)\))$"
REGEX_IF = r"^(if\s*\(\s*\S+\s*\)\s*\{$)" 
REGEX_ELSE = r"(\}\s*else\s*\{$)"

condicion_OK = [True]
cant_if = 0
cant_else = 0
cant_corch = 0

for linea in arch:
    i+=1
    linea = re.sub(r'^\s+|\s+$', '', linea)
    MATCH_IF = re.match(REGEX_IF, linea)
    MATCH_ELSE = re.match(REGEX_ELSE, linea)
    MATCH_CORCHETE = re.match(r"^\}$", linea)

    if not MATCH_IF and condicion_OK[0] and not MATCH_ELSE and not MATCH_CORCHETE:
        respuesta = S.separador(linea, i, REGEX_DEFINE, REGEX_DP, REGEX_MOSTRAR, output)
    
    if MATCH_IF:
        respuesta = IF_ELSE.condicional(linea, i, condicion_OK)
        cant_if += 1

    
    if MATCH_ELSE:
        cant_else += 1
        if cant_else > cant_if:
            print('Error de sintaxis: La cantidad de "else" no puede superar la cantidad de "if". Revisar la linea', str(i))
            respuesta = 0
        if condicion_OK[0] == False:
            condicion_OK[0] = True
        else:
            condicion_OK[0] = False

    if MATCH_CORCHETE:
        cant_corch += 1
        if cant_if - cant_corch < 0:
            print('Error de sintaxis: Hay un corchete "}" que no cuadra. Revisar la linea ', str(i))
            respuesta = 0
        condicion_OK = [True]

    if not respuesta: 
        print(linea)
        break
    

if cant_if != cant_corch or cant_if != cant_else:
    print("Error de sintaxis: El programa ha detectado inconsistencia en la cantidad de 'if', 'else' y '}'")


'''
print('',end='\n\n')
print('Variables almacenadas:')
for llave in dicc_variables:
    valor = str(llave)+": "+str(dicc_variables[llave])
    print(valor)
'''
