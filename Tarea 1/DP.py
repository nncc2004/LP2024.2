import re
import diccs

def ASIG(i, ContieneString, palabras, Strings = 0):
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
            valor = bool(palabras[3])
        
        variable_entera_origen = re.search(r"\$_(.+)", palabras[3])
        if variable_entera_origen:
            variable_origen = variable_entera_origen.group(1)
            if not diccs.verificar_existencia(variable_origen) or diccs.dicc_variables[variable_origen] == None:
                print("Variable No Definida: La variable de nombre '" +palabras[3]+ "' no ha sido definida o no se le ha asignado valor en la linea " +str(i))
                return 0
            valor = diccs.dicc_variables[variable_origen]
            
        
        if valor == '':
            print("Los strings deben ir entre '#'. Error sintactico en la linea " +str(i))
            return 0


    if variable:
        variable = variable.group(1)
        if not diccs.verificar_existencia(variable):
            print("Variable No Definida: La variable de nombre '" + variable_entera+ "' no ha sido definida o no se le ha asignado valor en la linea " +str(i))
            return 0
        diccs.dicc_variables[variable] = valor
    else:
        print("Variable mal definida en la linea "+str(i))
        return 0
    

    return 1


def SUM(i, ContieneString, palabras, Strings = 0):
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
                        print("Variable No Definida: La variable de nombre '" +palabras[3]+ "' no ha sido definida o no se le ha asignado valor en la linea " +str(i))
                        return 0
                    valor1 = diccs.dicc_variables[variable_origen]
                else:
                    print("La suma solo permite tipo string y entero. Revisar la linea " +str(i))
                    return 0
            if type(valor1) == bool: 
                print("La suma solo permite tipo string y entero. Revisar la linea " +str(i))
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
                        print("Variable No Definida: La variable de nombre '" +palabras[3]+ "' no ha sido definida o no se le ha asignado valor en la linea " +str(i))
                        return 0
                    valor1 = diccs.dicc_variables[variable_origen]
            else:
                print("La suma solo permite tipo string y entero. Revisar la linea " +str(i))
                return 0
        #valor 2
        try:
            valor2 = int(palabras[4])
        except:
            if re.search(r"\$_(.+)", palabras[4]):
                variable_origen = re.search(r"\$_(.+)", palabras[4]).group(1)
                if not diccs.verificar_existencia(variable_origen) or diccs.dicc_variables[variable_origen] == None:
                        print("Variable No Definida: La variable de nombre '" +palabras[4]+ "' no ha sido definida o no se le ha asignado valor en la linea " +str(i))
                        return 0
                valor2 = diccs.dicc_variables[variable_origen]
            else:
                print("La suma solo permite tipo string y entero. Revisar la linea " +str(i))
                return 0
            
        #Verificar que no sea Bool y orden de la suma
        if type(valor1) == bool or type(valor2) == bool: 
            print("La suma solo permite tipo string y entero. Revisar la linea " +str(i))
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
            print("Variable No Definida: La variable de nombre '" + variable_entera+ "' no ha sido definida o no se le ha asignado valor en la linea " +str(i))
            return 0
        diccs.dicc_variables[variable] = suma
        
    else:
        print("Variable mal definida en la linea "+str(i))
        return 0
    
    return 1


def MULT(linea, i, palabras):
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
                    print("Variable No Definida: La variable de nombre '" +palabras[3]+ "' no ha sido definida o no se le ha asignado valor en la linea " +str(i))
                    return 0
                valor1 = diccs.dicc_variables[variable_origen]
        else:
            print("La multiplicacion solo permite tipo entero. Revisar la linea " +str(i))
            return 0
    #valor 2
    try:
        valor2 = int(palabras[4])
    except:
        if re.search(r"\$_(.+)", palabras[4]):
            variable_origen = re.search(r"\$_(.+)", palabras[4]).group(1)
            if not diccs.verificar_existencia(variable_origen) or diccs.dicc_variables[variable_origen] == None:
                    print("Variable No Definida: La variable de nombre '" +palabras[4]+ "' no ha sido definida o no se le ha asignado valor en la linea " +str(i))
                    return 0
            valor2 = diccs.dicc_variables[variable_origen]
        else:
            print("La multiplicacion solo permite tipo entero. Revisar la linea " +str(i))
            return 0
        
    #Verificar que no sea Bool o string
    if type(valor1) != int or type(valor2) != int: 
        print("La multiplicacion solo permite tipo entero. Revisar la linea " +str(i))
        return 0
    
    mult = valor1*valor2
    
    #Asignacion de valor final
    if variable:
        variable = variable.group(1)
        if not diccs.verificar_existencia(variable):
            print("Variable No Definida: La variable de nombre '" + variable_entera+ "' no ha sido definida o no se le ha asignado valor en la linea " +str(i))
            return 0

        diccs.dicc_variables[variable] = mult
    else:
        print("Variable mal definida en la linea "+str(i))
        return 0
    

    return 1

    pass


def GREATER(linea, i, string, palabras):
    pass


def IGUAL(linea, i, string, palabras):
    pass