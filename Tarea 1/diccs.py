dicc_variables = {}

def verificar_existencia(nombre):
    if nombre in dicc_variables:
        return 1
    else:
        return 0