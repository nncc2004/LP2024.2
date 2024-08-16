arch = open('archivo.txt', 'r')
import re

REGEX_1_TAB = r"^(\s{4}\S)"
for linea in arch:
    buscar = re.search(REGEX_1_TAB, linea)

    if buscar:
        print(linea)