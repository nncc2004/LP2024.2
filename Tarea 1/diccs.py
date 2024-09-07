dicc_variables = {}

def verificar_existencia(nombre):
    '''
    ***
    nombre: entero
    ...
    ***
    0: (int) en caso de no encontrar
    1: (int) en caso de exito
    ***
    La funcion recibe el nombre de una variable y revisa si esta en el diccionario. 
    Si esta retorna 1 y si no esta retorna 0
    '''
    if nombre in dicc_variables:
        return 1
    else:
        return 0