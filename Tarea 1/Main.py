import re
import DEFINE as D
from diccs import dicc_variables
arch = open('archivo.txt', 'r')
i = 0

REGEX_DEFINE = r"^(DEFINE)\s\$_[A-Z](\S+)$"
REGEX_DP = r"^(DP)\s(.+)$" #Puede hacerse mejor. Hay dos patrones y se pueden poner ambos con el 'or'
REGEX_MOSTRAR = r""#Pendiente
REGEX_IF = r"" #Pendiente



for linea in arch:
    i+=1
    linea = linea.strip()

    MATCH_DEFINE = re.match(REGEX_DEFINE, linea)
    MATCH_DP = re.match(REGEX_DP, linea)
    MATCH_MOSTRAR = re.match(REGEX_MOSTRAR, linea)
    MATCH_IF = re.match(REGEX_IF, linea)


    if MATCH_DEFINE:
        print("LOGRADO (DE): "+linea)
        D.DEFINE(linea, i)
    elif MATCH_DP:
        print("LOGRADO (DP): "+linea)
    else:
        print("Syntax error en linea "+str(i)+": "+linea)
        #Llamar a error


    '''
        elif MATCH_MOSTRAR:
        pass
        elif MATCH_IF:
        pass
    '''

print('',end='\n\n')
print('Variables almacenadas:')
for llave in dicc_variables:
    print(llave)
