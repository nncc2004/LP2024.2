import re
texto = "$_hola"
prueba = re.match(r"^\$_(\S+)", texto)
print(prueba)