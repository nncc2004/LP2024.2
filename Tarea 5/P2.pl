puente(p12, p5, 2).
puente(p11, p12, 3).
puente(p11, p4, 7).
puente(p10, p4, 6).
puente(p10, p3, 3).
puente(p9, p10, 10).
puente(p8, p9, 3).
puente(p8, p3, 8).
puente(p7, p6, 4).
puente(p7, p2, 3).
puente(p6, p2, 2).
puente(p5, p1, 7).
puente(p4, c, 3).
puente(p3, c, 2).
puente(p2, p3, 7).
puente(p2, p1, 3).
puente(p1, p4, 1).
puente(p1, c, 4).



% implementaci�n predicado "camino"
%--------------------------------------

%------------------------------------------------------------------------------------
% Predicado: camino_aux_backtracking(S, c, [S, c])
% Par�metros:
%   - S: Nodo inicial desde donde comienza la b�squeda.
%   - c: Nodo final (centro gal�ctico).
%   - [S, c]: Camino directo acumulado como lista.
% Descripci�n:
%   Caso base del backtracking. Si hay un puente directo desde S hacia c,
%   el camino es simplemente [S, c].
% Retorno:
%   - [S, c]: Camino directo entre S y c.
%   - false: Si no existe un puente directo entre S y c.

% Implementaci�n:
camino_aux_backtracking(S, c, [S, c]) :-
    puente(S, c, _).

%------------------------------------------------------------------------------------
%------------------------------------------------------------------------------------
% Predicado: camino_aux_backtracking(S, c, [S | Res])
% Par�metros:
%   - S: Nodo inicial desde donde se realiza la b�squeda.
%   - c: Nodo final (centro gal�ctico).
%   - [S | Res]: Camino acumulado, comenzando en S y extendi�ndose con Res.
% Descripci�n:
%   Usando backtracking, busca un nodo intermedio conectado a S que no haya
%   sido visitado previamente. Luego, llama recursivamente para continuar
%   construyendo el camino desde el nodo intermedio hasta c.
% Retorno:
%   - [S | Res]: Camino acumulado desde S hasta c.
%   - false: Si no hay camino posible con nodos intermedios.

% Implementaci�n:
camino_aux_backtracking(S, c, [S | Res]) :-
    puente(S, Intermedio, _),
    \+ member(Intermedio, [S]),
    camino_aux_backtracking(Intermedio, c, Res).

%------------------------------------------------------------------------------------
%------------------------------------------------------------------------------------
% Predicado: camino(S, Camino)
% Par�metros:
%   - S: Nodo inicial desde donde comienza la b�squeda.
%   - Camino: Lista de nodos que representan el trayecto desde S hasta c.
% Descripci�n:
%   Predicado principal que utiliza camino_aux_backtracking para encontrar
%   un camino desde el nodo inicial S hasta el centro gal�ctico c. Implementado
%   con backtracking para explorar todas las posibles rutas.
% Retorno:
%   - Camino: Lista de nodos en el trayecto encontrado.
%   - false: Si no existe ning�n camino posible.

% Implementaci�n:
camino(S, Camino) :-
    camino_aux_backtracking(S, c, Camino).



% implementaci�n predicado "combustible"
%--------------------------------------

%------------------------------------------------------------------------------------
% Predicado: combustible_entre_nodos(A, B, Peso)
% Par�metros:
%   - A: Nodo inicial.
%   - B: Nodo final.
%   - Peso: Costo en combustible para moverse de A a B.
% Descripci�n:
%   Busca el peso (costo) asociado a un puente entre los nodos A y B.
% Retorno:
%   - Peso: El costo en combustible para moverse entre los nodos A y B.
%   - false: Si no existe un puente directo entre A y B.

% Implementaci�n:
combustible_entre_nodos(A, B, Peso) :-
    puente(A, B, Peso).
    
%------------------------------------------------------------------------------------
%------------------------------------------------------------------------------------
% Predicado: combustible_en_total([Nodo], Combustible, [[Nodo, Combustible]])
% Par�metros:
%   - [Nodo]: Lista que contiene solo el nodo actual (�ltimo nodo en el camino).
%   - Combustible: Cantidad de combustible restante.
%   - [[Nodo, Combustible]]: Resultado acumulado con el nodo y el combustible restante.
% Descripci�n:
%   Caso base que ocurre al llegar al �ltimo nodo del camino (centro gal�ctico).
%   No hay m�s nodos que recorrer, por lo que se devuelve el nodo y el combustible restante.
% Retorno:
%   - [[Nodo, Combustible]]: Lista con el nodo actual y el combustible restante.

% Implementaci�n:
combustible_en_total([Nodo], Combustible, [[Nodo, Combustible]]).


%------------------------------------------------------------------------------------
%------------------------------------------------------------------------------------
% Predicado: combustible_en_total([Nodo1, Nodo2 | Resto], Combustible, [[Nodo1, Combustible] | Res])
% Par�metros:
%   - [Nodo1, Nodo2 | Resto]: Lista de nodos en el camino actual.
%   - Combustible: Cantidad de combustible restante al llegar a Nodo1.
%   - [[Nodo1, Combustible] | Res]: Resultado acumulado que incluye Nodo1 y el combustible restante, seguido de los resultados del resto del camino.
% Descripci�n:
%   Caso recursivo que avanza de Nodo1 a Nodo2:
%     - Obtiene el peso (usando el predicado "combustible_entre_nodos") del puente entre Nodo1 y Nodo2.
%     - Verifica que el combustible restante sea suficiente.
%     - Resta el costo al combustible actual.
%     - Llama recursivamente al siguiente tramo del camino.
% Retorno:
%   - [[Nodo1, Combustible] | Res]: Lista acumulada con cada nodo visitado y el combustible restante.
%   - false: Si en alg�n tramo del camino el combustible no es suficiente.

% Implementaci�n:
combustible_en_total([Nodo1, Nodo2 | Resto], Combustible, [[Nodo1, Combustible] | Res]) :-
    combustible_entre_nodos(Nodo1, Nodo2, Peso),
    Combustible >= Peso,
    NuevoCombustible is Combustible - Peso,
    combustible_en_total([Nodo2 | Resto], NuevoCombustible, Res).


%------------------------------------------------------------------------------------
%------------------------------------------------------------------------------------
% Predicado: combustible(S, V, Res)
% Par�metros:
%   - S: Nodo inicial desde donde comienza el camino.
%   - V: Cantidad inicial de combustible.
%   - Res: Lista acumulada que incluye cada nodo y el combustible restante al llegar a �l.
% Descripci�n:
%   Encuentra un camino desde el nodo inicial S hasta el centro gal�ctico c usando
%   el predicado "camino", y calcula el combustible restante en cada paso del camino.
% Retorno:
%   - Res: Lista acumulada con el trayecto completo, donde cada elemento es de la forma [Nodo, Combustible].
%   - false: Si no hay suficiente combustible para completar el camino.

% Implementaci�n:
combustible(S, V, Res) :-
    camino(S, Camino),
    combustible_en_total(Camino, V, Res).

