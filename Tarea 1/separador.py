import re
import DEFINE as D
import DP
import MOSTRAR as M
from diccs import dicc_variables


def separador(linea, i, REGEX_DEFINE, REGEX_DP, REGEX_MOSTRAR, output):
    '''
    ***
    linea: string
    i: entero
    REGEX_DEFINE: string con patron de regex
    REGEX_DP: string con patron de regex
    REGEX_MOSTRAR: string con patron de regex
    output: string con nombre de archivo para escribir
    ...
    ***
    0: (int) en caso de error
    1: (int) en caso de exito
    ***
    Esta funcion tiene como objetivo dirigir una linea a la funcion que le corresponda según 
    el comando que tenga. Lo primero que hacer es definir si tiene string porque tiene un trato diferente. 
    Luego define los match para todos los comandos que se pueden encontrar.
    Asi luego segun qué match fue True (hay coincidencia) se llama a la funcion correspondiente.
    En el caso de los comandos que comienzan con DP se hace la diferencia entre con o sin strings en la linea
    pues esto influye en la cantidad de tokens separados por espacios que puede haber en una sintaxis correcta.
    La funcion retorna lo que le retornen las funciones, que en todos los casos es 0 en casos de haber algun error y
    1 en caso de no haberlo.
    '''

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
                        print("Error de sintaxis: Error sintactico en linea "+str(i))
                        return 0 
                    respuesta = DP.ASIG(i, ContieneString, palabras)
                elif comando == '+': #Suma
                    if len(palabras) != 5: 
                        print("Error de sintaxis: Error sintactico en linea "+str(i))
                        return 0 
                    respuesta = DP.SUM(i, ContieneString, palabras)
                elif comando == '*': #Multiplicacion
                    if len(palabras) != 5: 
                        print("Error de sintaxis: Error sintactico en linea "+str(i))
                        return 0
                    respuesta == DP.MULT(i, palabras)
                elif comando == '>': #Mayor que (Greater)
                    if len(palabras) != 5: 
                        print("Error de sintaxis: Error sintactico en linea "+str(i))
                        return 0
                    respuesta == DP.GREATER(i, palabras)
                elif comando == '==': #IGUAL
                    if len(palabras) != 5: 
                        print("Error de sintaxis: Error sintactico en linea "+str(i))
                        return 0
                    respuesta == DP.IGUAL(i, ContieneString, palabras)
                else:
                    print("Error de sintaxis: Comando desconocido o error sintactico en linea "+str(i))
                    return 0
            else:
                palabras_SinString = re.findall(r"[^\s]+", Linea_SinString)
                Strings = re.findall((r"#(.*?)#"), linea)

                if comando == 'ASIG': #Asignacion
                    if len(palabras_SinString) != 3 or len(Strings) != 1:
                        print("Error de sintaxis: Error sintactico en linea "+str(i))
                        return 0
                    respuesta = DP.ASIG(i, ContieneString, palabras_SinString, Strings)
                elif comando == '+':#Suma
                    if len(Strings) == 1 and len(palabras_SinString) != 4: 
                        print("Error de sintaxis: Error sintactico en linea "+str(i))
                        return 0
                    elif len(Strings) == 2 and len(palabras_SinString) != 3:
                        print("Error de sintaxis: Error sintactico en linea "+str(i))
                        return 0
                    elif len(Strings) > 2:
                        print("Error: de sintaxis Error sintactico en linea "+str(i))
                        return 0
                    respuesta = DP.SUM(i, ContieneString, palabras_SinString, Strings)
                elif comando == '*': #Multiplicacion
                    print("Error de tipo: La multiplicacion no permite variables de tipo string. Revisar la linea " +str(i))
                    return 0
                elif comando == '>': #Mayor que (Greater)
                    print("Error de tipo: La operacion 'mayor que' no permite variables de tipo string. Revisar la linea " +str(i))
                    return 0
                elif comando == '==': #IGUAL
                    if len(Strings) == 1 and len(palabras_SinString) != 4: 
                        print("Error de sintaxis: Error sintactico en linea "+str(i))
                        return 0
                    elif len(Strings) == 2 and len(palabras_SinString) != 3:
                        print("Error de sintaxis: Error sintactico en linea "+str(i))
                        return 0
                    elif len(Strings) > 2:
                        print("Error de sintaxis: Error sintactico en linea "+str(i))
                        return 0
                    respuesta = DP.IGUAL(i, ContieneString, palabras_SinString, Strings)
                else:
                    print("Error de sintaxis: Comando desconocido o error sintactico en linea "+str(i)+": "+linea)
                    return 0       
        elif MATCH_MOSTRAR: #MOSTRAR
            if ContieneString:
                print("Error de sintaxis: Mostrar debe contener una variable, no un string. Revisar la linea "+str(i))
                return 0
            respuesta = M.MOSTRAR(linea, i, output)
        else:
            print("Error de sintaxis: Comando desconocido o error sintactico en linea "+str(i))
            return 0
    else:
        print("Error de sintaxis: String mal definido. Revisar la linea "+str(i))
        return 0
    return respuesta
