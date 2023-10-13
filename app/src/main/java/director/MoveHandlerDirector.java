package director;

import validator.ComposeMovementValidator;
import validator.UnidirectionalMovementValidator;
import validator.interfaces.MovementValidator;

import java.util.ArrayList;
import java.util.List;
//TODO CHANGE CREATE NOT STATIC
//MEJOR HACERLO PARA UTILS, LOGICA
public class MoveHandlerDirector {
    //Here we can add all types of movements of any piece.
    public static List<MovementValidator> createRookMovement(){
        List<MovementValidator> moves=new ArrayList<>();
        moves.add(new UnidirectionalMovementValidator(1,0));
        moves.add(new UnidirectionalMovementValidator(-1,0));
        moves.add(new UnidirectionalMovementValidator(0,1));
        moves.add(new UnidirectionalMovementValidator(-1,0));
        return moves;
    }

//Mirar clase engine, gameEngine.

    //Init manda un init state.
    //Usar los del idString...
    //devolver un initialState
    //Hacer un adapter, algo que traduce mi modelo al de el...
    //clase q respeta una interfaz, tenemos clases que implementen esa interfaz y se comunican con ese modelo.
    //Transformamos las cosas para que entiendan ese modelo.
    //Crear las clases necesarias para adaptarlo.
    //Apply move, recibe un movimiento.
    //Tiene un moveResult, no se pudo mover devolver un texto y se lo muestra al usuario.
    //Cmabio el estado del juego y le paso una lista de piezas, devolverselo con una id.
    //No asume nada..
    //GameOver...
    //Se manda un texto, black won...
    //Forkear el chess-app
    //No hay que tocar nada de la UI...
    //Hay que pasar el github_user, crear variables de entorno....
    //read.packages, ponerle un nombre.
    //Export variable de entorno, en la terminal, con el token.
    //./gradlew build, descarga todas las dependencias....
    //Adapter hacer para semana que viene, nos la copiamos...
    public static List<MovementValidator> setKnightMovement(){
        return new ArrayList<>(List.of(new ComposeMovementValidator(1,2),
                new ComposeMovementValidator(2,1),
                new ComposeMovementValidator(2,-1),
                new ComposeMovementValidator(1,-2),
                new ComposeMovementValidator(-1,-2),
                new ComposeMovementValidator(-2,-1),
                new ComposeMovementValidator(-2,1),
                new ComposeMovementValidator(-1,2)));
    }

    public static List<MovementValidator> setBishopMovement(){
        return new ArrayList<>(List.of(new UnidirectionalMovementValidator(1,1),
                new UnidirectionalMovementValidator(-1,-1),new UnidirectionalMovementValidator(-1,1),
                new UnidirectionalMovementValidator(1,-1)));
    }
}
