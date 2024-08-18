import re
import DEFINE as D
import DP
import MOSTRAR as M
from diccs import dicc_variables
import separador as S
import IF_ELSE

arch = open('archivo.txt', 'r')
output = open('output.txt', 'w')
i = 0
REGEX_DEFINE = r"^(DEFINE)\s+(\S+)$"
REGEX_DP = r"^(DP)\s(.+)$"
REGEX_MOSTRAR = r"^(MOSTRAR\((\s*))(\S)+((\s*)\))$"
REGEX_IF = r"^(if\(\s*\S+\s*\)\s*\{$)" 
REGEX_ELSE = r"(\}\s*else\s*\{$)"

condicion_OK = [True]

for linea in arch:
    i+=1
    linea = linea.strip()
    MATCH_IF = re.match(REGEX_IF, linea)
    MATCH_ELSE = re.match(REGEX_ELSE, linea)
    MATCH_CORCHETE = re.match(r"^\}$", linea)
    if not MATCH_IF and condicion_OK[0] and not MATCH_ELSE and not MATCH_CORCHETE:
        respuesta = S.separador(linea, i, REGEX_DEFINE, REGEX_DP, REGEX_MOSTRAR, arch, output)
    
    if MATCH_IF:
        respuesta = IF_ELSE.condicional(linea, i, condicion_OK)

    
    if MATCH_ELSE:
        if condicion_OK[0] == False:
            condicion_OK[0] = True
        else:
            condicion_OK[0] = False

    if MATCH_CORCHETE:
        condicion_OK = [True]

    if not respuesta: 
        break
    

    
print('',end='\n\n')
print('Variables almacenadas:')
for llave in dicc_variables:
    valor = str(llave)+": "+str(dicc_variables[llave])
    print(valor)
