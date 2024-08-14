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
REGEX_IF = r"^(IF\(\s*\S+\s*\)\s*\{$)" 
REGEX_ELSE = r"(\}\s*ELSE\s*\{$)"

numero_anidacion = 0
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
        numero_anidacion+=1
        respuesta = IF_ELSE.condicional(linea, i, condicion_OK)

    
    if MATCH_ELSE:
        if condicion_OK[0] == False:
            condicion_OK[0] = True
        else:
            condicion_OK[0] = False

        numero_anidacion -= 1


        #aca deberia usar los niveles para ir restando siempre que sea mayor que 0. (sino error)
        #Con esto hacer las combinaciones para poder definir que lineas se ejecutan y cuales no

    if MATCH_CORCHETE:
        condicion_OK = [True]

    if not respuesta: 
        break
    
if numero_anidacion != 0:
    print("No se cerro el condicional de manera apropiada, por favor revisar el codigo. " + str(numero_anidacion))
    

'''
verificar_string = re.findall('#', linea)
    if len(verificar_string)%2 == 0:
        if True: #ELIMINAR ESTE IF!!!!
            ContieneString = True if len(verificar_string) != 0 else False
            Linea_SinString = re.sub(r"#.*?#", "", linea)
            MATCH_DEFINE = re.match(REGEX_DEFINE, linea)
            MATCH_DP = re.match(REGEX_DP, linea)
            MATCH_MOSTRAR = re.match(REGEX_MOSTRAR, linea)
            MATCH_IF = re.match(REGEX_IF, linea)

        if MATCH_DEFINE: #DEFINE
            respuesta = D.DEFINE(linea, i)
        elif MATCH_DP: #DP
            palabras = re.findall(r"[^\s]+", linea)
            comando = palabras[2]
            if not ContieneString:
                if comando == 'ASIG': #Asignacion
                    if len(palabras) != 4: 
                        print("Error sintactico en linea "+str(i)+": "+linea)
                        break
                    respuesta = DP.ASIG(i, ContieneString, palabras)
                elif comando == '+': #Suma
                    if len(palabras) != 5: 
                        print("Error sintactico en linea "+str(i)+": "+linea)
                        break
                    respuesta = DP.SUM(i, ContieneString, palabras)
                elif comando == '*': #Multiplicacion
                    if len(palabras) != 5: 
                        print("Error sintactico en linea "+str(i)+": "+linea)
                        break
                    respuesta == DP.MULT(i, palabras)
                elif comando == '>': #Mayor que (Greater)
                    if len(palabras) != 5: 
                        print("Error sintactico en linea "+str(i)+": "+linea)
                        break
                    respuesta == DP.GREATER(i, palabras)
                elif comando == '==': #IGUAL
                    if len(palabras) != 5: 
                        print("Error sintactico en linea "+str(i)+": "+linea)
                        break
                    respuesta == DP.IGUAL(i, ContieneString, palabras)
                else:
                    print("Comando desconocido o error sintactico en linea "+str(i)+": "+linea)
                    break
            else:
                palabras_SinString = re.findall(r"[^\s]+", Linea_SinString)
                Strings = re.findall((r"#(.*?)#"), linea)

                if comando == 'ASIG': #Asignacion
                    if len(palabras_SinString) != 3 or len(Strings) != 1:
                        print("Error sintactico en linea "+str(i)+": "+linea)
                        break
                    respuesta = DP.ASIG(i, ContieneString, palabras_SinString, Strings)
                elif comando == '+':#Suma
                    if len(Strings) == 1 and len(palabras_SinString) != 4: 
                        print("Error sintactico en linea "+str(i)+": "+linea)
                        break
                    elif len(Strings) == 2 and len(palabras_SinString) != 3:
                        print("Error sintactico en linea "+str(i)+": "+linea)
                        break
                    elif len(Strings) > 2:
                        print("Error sintactico en linea "+str(i)+": "+linea)
                        break
                    respuesta = DP.SUM(i, ContieneString, palabras_SinString, Strings)
                elif comando == '*': #Multiplicacion
                    print("La multiplicacion no permite tipo string. Revisar la linea " +str(i))
                    break
                elif comando == '>': #Mayor que (Greater)
                    print("La operacion 'mayor que' no permite tipo string. Revisar la linea " +str(i))
                    break
                elif comando == '==': #IGUAL
                    if len(Strings) == 1 and len(palabras_SinString) != 4: 
                        print("Error sintactico en linea "+str(i)+": "+linea)
                        break
                    elif len(Strings) == 2 and len(palabras_SinString) != 3:
                        print("Error sintactico en linea "+str(i)+": "+linea)
                        break
                    elif len(Strings) > 2:
                        print("Error sintactico en linea "+str(i)+": "+linea)
                        break
                    respuesta = DP.IGUAL(i, ContieneString, palabras_SinString, Strings)
                else:
                    print("Comando desconocido o error sintactico en linea "+str(i)+": "+linea)
                    break       
        elif MATCH_MOSTRAR: #MOSTRAR
            if ContieneString:
                print("Syntax error en linea "+str(i)+": "+linea+". Mostrar debe contener una variable, no un string")
                break
            respuesta = M.MOSTRAR(linea, i, output)
        else:
            print("Syntax error en linea "+str(i)+": "+linea)
            break

    else:
        print("String mal definido en linea "+str(i)+": "+linea)
        break
'''

print('',end='\n\n')
print('Variables almacenadas:')
for llave in dicc_variables:
    valor = str(llave)+": "+str(dicc_variables[llave])
    print(valor)
