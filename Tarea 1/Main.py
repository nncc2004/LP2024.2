import re
import DEFINE as D
import DP
import MOSTRAR as M
from diccs import dicc_variables

arch = open('archivo.txt', 'r')
output = open('output.txt', 'w')
i = 0
respuesta = 1
REGEX_DEFINE = r"^(DEFINE)\s(\S+)$"
REGEX_DP = r"^(DP)\s(.+)$"
REGEX_MOSTRAR = r"^(MOSTRAR\()(\S)+(\))$"
REGEX_IF = r"" #Pendiente


for linea in arch:
    i+=1
    linea = linea.strip()
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
        if not respuesta: 
            break
    else:
        print("String mal definido en linea "+str(i)+": "+linea)
        break

    

'''
elif MATCH_IF:
pass

''' 


print('',end='\n\n')
print('Variables almacenadas:')
for llave in dicc_variables:
    valor = str(llave)+": "+str(dicc_variables[llave])
    print(valor)
