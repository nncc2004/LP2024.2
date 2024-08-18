import re
import DEFINE as D
import DP
import MOSTRAR as M
from diccs import dicc_variables


def separador(linea, i, REGEX_DEFINE, REGEX_DP, REGEX_MOSTRAR, arch, output):
    respuesta = 1
    verificar_string = re.findall('#', linea)
    if len(verificar_string)%2 == 0:

        #Variables previas
        ContieneString = True if len(verificar_string) != 0 else False
        Linea_SinString = re.sub(r"#.*?#", "", linea)
        MATCH_DEFINE = re.match(REGEX_DEFINE, linea)
        MATCH_DP = re.match(REGEX_DP, linea)
        MATCH_MOSTRAR = re.match(REGEX_MOSTRAR, linea)


        if MATCH_DEFINE: #DEFINE
            respuesta = D.DEFINE(linea, i)
        elif MATCH_DP: #DP
            palabras = re.findall(r"[^\s]+", linea)
            comando = palabras[2]
            if not ContieneString:
                if comando == 'ASIG': #Asignacion
                    if len(palabras) != 4: 
                        print("Error: Error sintactico en linea "+str(i))
                        return 0 
                    respuesta = DP.ASIG(i, ContieneString, palabras)
                elif comando == '+': #Suma
                    if len(palabras) != 5: 
                        print("Error: Error sintactico en linea "+str(i))
                        return 0 
                    respuesta = DP.SUM(i, ContieneString, palabras)
                elif comando == '*': #Multiplicacion
                    if len(palabras) != 5: 
                        print("Error: Error sintactico en linea "+str(i))
                        return 0
                    respuesta == DP.MULT(i, palabras)
                elif comando == '>': #Mayor que (Greater)
                    if len(palabras) != 5: 
                        print("Error: Error sintactico en linea "+str(i))
                        return 0
                    respuesta == DP.GREATER(i, palabras)
                elif comando == '==': #IGUAL
                    if len(palabras) != 5: 
                        print("Error: Error sintactico en linea "+str(i))
                        return 0
                    respuesta == DP.IGUAL(i, ContieneString, palabras)
                else:
                    print("Error: Comando desconocido o error sintactico en linea "+str(i))
                    return 0
            else:
                palabras_SinString = re.findall(r"[^\s]+", Linea_SinString)
                Strings = re.findall((r"#(.*?)#"), linea)

                if comando == 'ASIG': #Asignacion
                    if len(palabras_SinString) != 3 or len(Strings) != 1:
                        print("Error: Error sintactico en linea "+str(i))
                        return 0
                    respuesta = DP.ASIG(i, ContieneString, palabras_SinString, Strings)
                elif comando == '+':#Suma
                    if len(Strings) == 1 and len(palabras_SinString) != 4: 
                        print("Error: Error sintactico en linea "+str(i))
                        return 0
                    elif len(Strings) == 2 and len(palabras_SinString) != 3:
                        print("Error: Error sintactico en linea "+str(i))
                        return 0
                    elif len(Strings) > 2:
                        print("Error: Error sintactico en linea "+str(i))
                        return 0
                    respuesta = DP.SUM(i, ContieneString, palabras_SinString, Strings)
                elif comando == '*': #Multiplicacion
                    print("Error: La multiplicacion no permite variables de tipo string. Revisar la linea " +str(i))
                    return 0
                elif comando == '>': #Mayor que (Greater)
                    print("Error: La operacion 'mayor que' no permite variables de tipo string. Revisar la linea " +str(i))
                    return 0
                elif comando == '==': #IGUAL
                    if len(Strings) == 1 and len(palabras_SinString) != 4: 
                        print("Error: Error sintactico en linea "+str(i))
                        return 0
                    elif len(Strings) == 2 and len(palabras_SinString) != 3:
                        print("Error: Error sintactico en linea "+str(i))
                        return 0
                    elif len(Strings) > 2:
                        print("Error: Error sintactico en linea "+str(i))
                        return 0
                    respuesta = DP.IGUAL(i, ContieneString, palabras_SinString, Strings)
                else:
                    print("Error: Comando desconocido o error sintactico en linea "+str(i)+": "+linea)
                    return 0       
        elif MATCH_MOSTRAR: #MOSTRAR
            if ContieneString:
                print("Error: Mostrar debe contener una variable, no un string. Revisar la linea "+str(i))
                return 0
            respuesta = M.MOSTRAR(linea, i, output)
        else:
            print("Error: Comando desconocido o error sintactico en linea "+str(i))
            return 0
    else:
        print("Error: String mal definido. Revisar la linea "+str(i))
        return 0
    return respuesta
