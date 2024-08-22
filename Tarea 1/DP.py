import re
import diccs

def ASIG(i, ContieneString, palabras, Strings = 0):
    '''
    ***
    i: entero
    ContieneString: Booleano
    palabras: arreglo de strings
    Strings: arreglo de strings
    ...
    ***
    0: (int) en caso de error
    1: (int) en caso de exito
    ***
    La funcion tiene como objetivo asignar el valor a su variable correspondiente cuando haya un ASIG en el codigo
    Lo primero que hace es encontrar la variable de destino con el search. Luego busca el valor que se quiere asignar.
    Si hay string es el string completo y si no hay se busca el tipo de dato. Primero se intenta con entero, luego bool y si no es ninguno
    da error (porque ya se descartó el tipo string). En caso de cualqueir error con esto o con la variable, imprime el mensaje por pantalla y retorna 0. Si no hay errores retorna 1
    '''
    variable_entera = palabras[1]
    variable = re.search(r"\$_(.+)", variable_entera)

    valor = ''
    if ContieneString:
        valor = Strings[0]
    else:
        try:
            valor = int(palabras[3])
        except:
            pass

        if palabras[3] == 'True' or palabras[3] == 'False':
            valor = True if palabras[3] == 'True' else False
        
        variable_entera_origen = re.search(r"\$_(.+)", palabras[3])
        if variable_entera_origen:
            variable_origen = variable_entera_origen.group(1)
            if not diccs.verificar_existencia(variable_origen) or diccs.dicc_variables[variable_origen] == None:
                print("Error de sintaxis: La variable de nombre '" + palabras[3]+ "' no ha sido definida o no se le ha asignado valor. Revisar la linea " +str(i))
                return 0
            valor = diccs.dicc_variables[variable_origen]
            
        
        if valor == '':
            print("Error de sintaxis: Los strings deben ir entre '#'. Revisar la linea " +str(i))
            return 0


    if variable:
        variable = variable.group(1)
        if not diccs.verificar_existencia(variable):
            print("Error de sintaxis: La variable de nombre '" + variable_entera+ "' no ha sido definida o no se le ha asignado valor. Revisar la linea " +str(i))
            return 0
        diccs.dicc_variables[variable] = valor
    else:
        print("Error de sintaxis: Variable mal definida en la linea "+str(i))
        return 0
    

    return 1


def SUM(i, ContieneString, palabras, Strings = 0):
    '''
    ***
    i: entero
    ContieneString: Booleano
    palabras: arreglo de strings
    Strings: arreglo de strings
    ...
    ***
    0: (int) en caso de error
    1: (int) en caso de exito
    ***
    La funcion tiene como objetivo sumar dos valores dados y asignarlo a la variable correspondiente. Lo primero que se hace es extraer la variable y luego se pasa a 
    verificar los tipos de datos para la suma. En caso de haber strings (contieneString = True) se sabe que hay por lo menos un valor que es o una variable o un numero.
    Se extrae y se verifica que no sea bool. En caso de que sean los dos strings simplemente se concatena. Si no hay strings se tienen las posibilidades correctas de 
    int - int, var - int o var - var. En cualquier caso se extraen los valores 1 y 2 y se verifica que no sean bool. Luego se suma o concatena según corresponda.
    En caso de cualquier error de tipo o sintaxis se muestra el mensaje por pantalla y se retorna 0. En caso de no haber errores se retorna 1. Luego de extraer los datos se 
    asigna el resultado a la variable. 
    '''
    variable_entera = palabras[1]
    variable = re.search(r"\$_(.+)", variable_entera)
    if ContieneString:
        if(len(Strings) == 1):
            valor1 = palabras[3]
            try:
                valor1 = int(valor1)
            except:
                if re.search(r"\$_(.+)", palabras[3]):
                    variable_origen = re.search(r"\$_(.+)", palabras[3]).group(1)
                    if not diccs.verificar_existencia(variable_origen) or diccs.dicc_variables[variable_origen] == None:
                        print("Error de sintaxis: La variable de nombre '" + palabras[3]+ "' no ha sido definida o no se le ha asignado valor. Revisar la linea " +str(i))
                        return 0
                    valor1 = diccs.dicc_variables[variable_origen]
                else:
                    print("Error de tipo: La suma solo permite tipo string y entero. Revisar la linea " +str(i))
                    return 0
            if type(valor1) == bool: 
                print("Error de tipo: La suma solo permite tipo string y entero. Revisar la linea " +str(i))
                return 0
            valor1 = str(valor1)
            valor2 = Strings[0]                
            suma = valor2 + valor1
        else:
            suma = Strings[0] + Strings[1]
    else: #Si no hay strings.
        #valor 1
        try:
            valor1 = int(palabras[3])
        except:
            if re.search(r"\$_(.+)", palabras[3]):
                    variable_origen = re.search(r"\$_(.+)", palabras[3]).group(1)
                    if not diccs.verificar_existencia(variable_origen) or diccs.dicc_variables[variable_origen] == None:
                        print("Error de sintaxis: La variable de nombre '" + palabras[3]+ "' no ha sido definida o no se le ha asignado valor. Revisar la linea " +str(i))
                        return 0
                    valor1 = diccs.dicc_variables[variable_origen]
            else:
                print("Error de tipo: La suma solo permite tipo string y entero. Revisar la linea " +str(i))
                return 0
        #valor 2
        try:
            valor2 = int(palabras[4])
        except:
            if re.search(r"\$_(.+)", palabras[4]):
                variable_origen = re.search(r"\$_(.+)", palabras[4]).group(1)
                if not diccs.verificar_existencia(variable_origen) or diccs.dicc_variables[variable_origen] == None:
                        print("Error de sintaxis: La variable de nombre '" + palabras[4]+ "' no ha sido definida o no se le ha asignado valor. Revisar la linea " +str(i))
                        return 0
                valor2 = diccs.dicc_variables[variable_origen]
            else:
                print("Error de tipo: La suma solo permite tipo string y entero. Revisar la linea " +str(i))
                return 0
            
        #Verificar que no sea Bool y orden de la suma
        if type(valor1) == bool or type(valor2) == bool: 
            print("Error de tipo: La suma solo permite tipo string y entero. Revisar la linea " +str(i))
            return 0
        
        if type(valor1) == type(valor2):
            suma = valor1 + valor2
        elif type(valor1) == int:
            suma = valor2 + str(valor1)
        else:
            suma = valor1 + str(valor2)

    if variable:
        variable = variable.group(1)
        if not diccs.verificar_existencia(variable):
            print("Error d esintaxis: La variable de nombre '" + variable_entera + "' no ha sido definida o no se le ha asignado valor. Revisar la linea " +str(i))
            return 0
        diccs.dicc_variables[variable] = suma
        
    else:
        print("Error de sintaxis: Variable mal definida en la linea "+str(i))
        return 0
    
    return 1


def MULT( i, palabras):
    '''
    ***
    i: entero
    palabras: arreglo de strings
    ...
    ***
    0: (int) en caso de error
    1: (int) en caso de exito
    ***
    La funcion tiene como objetivo multiplicar dos valores enteros y asignarlos a la variable que corresponde. De forma similar a la funcion anterior, se extraen
    los valores 1 y 2, pero en este caso se verifica que sean tipo enteros, o si es una variable, que su valor sea de tipo entero. Luego se hace la multiplicacion
    y se le asigna el restultado a la variable que corresponde. En caso de haber cualquier error durante la ejecución se muestra por pantalla el mensaje y se retorna 0. Si no
    hay errores se retorna 1. 
    '''
    variable_entera = palabras[1]
    variable = re.search(r"\$_(.+)", variable_entera)

    #Extraccion de valores:
    #valor 1
    try:
        valor1 = int(palabras[3])
    except:
        if re.search(r"\$_(.+)", palabras[3]):
                variable_origen = re.search(r"\$_(.+)", palabras[3]).group(1)
                if not diccs.verificar_existencia(variable_origen) or diccs.dicc_variables[variable_origen] == None:
                    print("Error de sintaxis: La variable de nombre '" + palabras[3]+ "' no ha sido definida o no se le ha asignado valor. Revisar la linea " +str(i))
                    return 0
                valor1 = diccs.dicc_variables[variable_origen]
        else:
            print("Error de tipo: La multiplicacion solo permite tipo entero. Revisar la linea " +str(i))
            return 0
    #valor 2
    try:
        valor2 = int(palabras[4])
    except:
        if re.search(r"\$_(.+)", palabras[4]):
            variable_origen = re.search(r"\$_(.+)", palabras[4]).group(1)
            if not diccs.verificar_existencia(variable_origen) or diccs.dicc_variables[variable_origen] == None:
                    print("Error de sintaxis: La variable de nombre '" + palabras[4]+ "' no ha sido definida o no se le ha asignado valor. Revisar la linea " +str(i))
                    return 0
            valor2 = diccs.dicc_variables[variable_origen]
        else:
            print("Erro de tipor: La multiplicacion solo permite tipo entero. Revisar la linea " +str(i))
            return 0
        
    #Verificar que no sea Bool o string
    if type(valor1) != int or type(valor2) != int: 
        print("Error de tipo: La multiplicacion solo permite tipo entero. Revisar la linea " +str(i))
        return 0
    
    mult = valor1*valor2
    
    #Asignacion de valor final
    if variable:
        variable = variable.group(1)
        if not diccs.verificar_existencia(variable):
            print("Error de tipo: La variable de nombre '" + variable_entera+ "' no ha sido definida o no se le ha asignado valor. Revisar la linea " +str(i))
            return 0

        diccs.dicc_variables[variable] = mult
    else:
        print("Error de sintaxis: variable mal definida en la linea "+str(i))
        return 0
    

    return 1


def GREATER(i, palabras):
    '''
    ***
    i: entero
    palabras: arreglo de strings
    ...
    ***
    0: (int) en caso de error
    1: (int) en caso de exito
    ***
    La funcion tiene como objetivo asignar el valor de la comparacion "mayor que" entre dos valores enteros a la variable dada. Primero se obtiene la variable destino
    y luego se extraen los valores 1 y 2 y se va verificando que sean de tipo entero. Por ultimo se hace la comparacion y se asigna el valor booleano a la variable 
    encontrada. En caso de haber cualquier error durante la ejecución se muestra por pantalla el mensaje y se retorna 0. Si no hay errores se retorna 1. 
    '''
    variable_entera = palabras[1]
    variable = re.search(r"\$_(.+)", variable_entera)

    #Extraccion de valores:
    #valor 1
    try:
        valor1 = int(palabras[3])
    except:
        if re.search(r"\$_(.+)", palabras[3]):
                variable_origen = re.search(r"\$_(.+)", palabras[3]).group(1)
                if not diccs.verificar_existencia(variable_origen) or diccs.dicc_variables[variable_origen] == None:
                    print("Error de sintaxis: La variable de nombre '" + palabras[3]+ "' no ha sido definida o no se le ha asignado valor. Revisar la linea " +str(i))
                    return 0
                valor1 = diccs.dicc_variables[variable_origen]
        else:
            print("Error de tipo: La operacion 'mayor que' solo permite tipo entero. Revisar la linea " +str(i))
            return 0
    #valor 2
    try:
        valor2 = int(palabras[4])
    except:
        if re.search(r"\$_(.+)", palabras[4]):
            variable_origen = re.search(r"\$_(.+)", palabras[4]).group(1)
            if not diccs.verificar_existencia(variable_origen) or diccs.dicc_variables[variable_origen] == None:
                    print("Error de sintaxis: La variable de nombre '" + palabras[4]+ "' no ha sido definida o no se le ha asignado valor. Revisar la linea " +str(i))
                    return 0
            valor2 = diccs.dicc_variables[variable_origen]
        else:
            print("Error de tipo: La operacion 'mayor que' solo permite tipo entero. Revisar la linea " +str(i))
            return 0
        
    #Verificar que no sea Bool o string
    if type(valor1) != int or type(valor2) != int: 
        print("Error de tipo: La operacion 'mayor que' solo permite tipo entero. Revisar la linea " +str(i))
        return 0
    
    mayor = True if valor1>valor2 else False
    
    #Asignacion de valor final
    if variable:
        variable = variable.group(1)
        if not diccs.verificar_existencia(variable):
            print("Error de sintaxis: La variable de nombre '" + variable_entera+ "' no ha sido definida o no se le ha asignado valor. Revisar la linea " +str(i))
            
            return 0

        diccs.dicc_variables[variable] = mayor
    else:
        print("Error de sintaxis: Variable mal definida en la linea "+str(i))
        return 0
    

    return 1


def IGUAL(i, ContieneString, palabras, Strings = 0):
    '''
    ***
    i: entero
    ContieneString: Booleano
    palabras: arreglo de strings
    Strings: arreglo de strings
    ...
    ***
    0: (int) en caso de error
    1: (int) en caso de exito
    ***
    La funcion tiene como objetivo asignar el valor de la comparacion  de igualdad entre dos valores enteros o string a la variable dada. Primero se obtiene la variable destino
    y luego se extraen los valores 1 y 2 y se va verificando que sean de tipo entero o string. Por ultimo se hace la comparacion y se asigna el valor booleano a la variable 
    encontrada. En caso de haber cualquier error durante la ejecución se muestra por pantalla el mensaje y se retorna 0. Si no
    hay errores se retorna 1. 
    '''
    variable_entera = palabras[1]
    variable = re.search(r"\$_(.+)", variable_entera)

    if ContieneString:
        variable_entera = palabras[1]
        variable = re.search(r"\$_(.+)", variable_entera)
        if ContieneString:
            if(len(Strings) == 1):
                valor1 = palabras[3]
                try:
                    valor1 = int(valor1)
                except:
                    if re.search(r"\$_(.+)", palabras[3]):
                        variable_origen = re.search(r"\$_(.+)", palabras[3]).group(1)
                        if not diccs.verificar_existencia(variable_origen) or diccs.dicc_variables[variable_origen] == None:
                            print("Error de tipo: La variable de nombre '" + palabras[3]+ "' no ha sido definida o no se le ha asignado valor. Revisar la linea " +str(i))
                            return 0
                        valor1 = diccs.dicc_variables[variable_origen]
                    else:
                        print("Error de tipo: La suma solo permite valores de tipo string y entero. Revisar la linea " +str(i))
                        return 0
                if type(valor1) == bool: 
                    print("Error de tipo: La suma solo permite valores de tipo string y entero. Revisar la linea " +str(i))
                    return 0
 
                valor2 = Strings[0]                
                iguales = True if valor1 == valor2 else False
            else:
                iguales = True if Strings[0] == Strings[1] else False
    else: #Si no contiene strings
        #valor 1
        try:
            valor1 = int(palabras[3])
        except:
            if re.search(r"\$_(.+)", palabras[3]):
                    variable_origen = re.search(r"\$_(.+)", palabras[3]).group(1)
                    if not diccs.verificar_existencia(variable_origen) or diccs.dicc_variables[variable_origen] == None:
                        print("Error de tipo: La variable de nombre '" + palabras[3]+ "' no ha sido definida o no se le ha asignado valor. Revisar la linea " +str(i))
                        return 0
                    valor1 = diccs.dicc_variables[variable_origen]
            else:
                print("Error de tipo: La comparacion de igualdad solo permite variables de tipo string y entero. Revisar la linea " +str(i))
                return 0
        #valor 2
        try:
            valor2 = int(palabras[4])
        except:
            if re.search(r"\$_(.+)", palabras[4]):
                variable_origen = re.search(r"\$_(.+)", palabras[4]).group(1)
                if not diccs.verificar_existencia(variable_origen) or diccs.dicc_variables[variable_origen] == None:
                        print("Error de tipo: La variable de nombre '" + palabras[4]+ "' no ha sido definida o no se le ha asignado valor. Revisar la linea " +str(i))
                        return 0
                valor2 = diccs.dicc_variables[variable_origen]
            else:
                print("Error de tipo: La comparacion de igualdad solo permite variables de tipo string y entero. Revisar la linea " +str(i))
                return 0
        #Verificar que no sea Bool y orden de la suma
        if type(valor1) == bool or type(valor2) == bool: 
            print("Error de tipo: La comparacion de igualdad solo permite variables de tipo string y entero. Revisar la linea " +str(i))
            return 0
        iguales = True if valor1 == valor2 else False

    #Asignacion de valor final
    if variable:
        variable = variable.group(1)
        if not diccs.verificar_existencia(variable):
            print("Error de sintaxis: La variable de nombre '" + variable_entera+ "' no ha sido definida o no se le ha asignado valor. Revisar la linea " +str(i))
            return 0
        diccs.dicc_variables[variable] = iguales
    else:
        print("Error de sintaxis: Variable mal definida en la linea "+str(i))
        return 0
    return 1