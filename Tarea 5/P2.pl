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



 % implementación predicado "camino"
camino_aux_backtracking(S, c, [S, c]) :-
    puente(S, c, _).


camino_aux_backtracking(S, c, [S | Res]) :-
    puente(S, Intermedio, _),
    \+ member(Intermedio, [S]),
    camino_aux_backtracking(Intermedio, c, Res).

camino(S, Camino) :-
    camino_aux_backtracking(S, c, Camino).
    

% implementación predicado "combustible"
combustible_entre_nodos(A, B, Peso) :-
    puente(A, B, Peso).
    
    
% Caso base: cuando estamos en el último nodo (centro galáctico).
combustible_en_total([Nodo], Combustible, [[Nodo, Combustible]]).

% Caso recursivo: recorre el camino de Nodo1 a Nodo2 y resta el peso del combustible
combustible_en_total([Nodo1, Nodo2 | Resto], Combustible, [[Nodo1, Combustible] | Res]) :-
    combustible_entre_nodos(Nodo1, Nodo2, Peso),                  % Obtiene el peso entre Nodo1 y Nodo2
    Combustible >= Peso,                        % Verifica que hay suficiente combustible
    NuevoCombustible is Combustible - Peso,     % Resta el peso del combustible
    combustible_en_total([Nodo2 | Resto], NuevoCombustible, Res).  % Continua con el siguiente nodo


combustible(S, V, Res) :-
    camino(S, Camino),                        % Encuentra un camino desde S hasta c
    combustible_en_total(Camino, V, Res).       % Calcula el combustible restante en cada paso


