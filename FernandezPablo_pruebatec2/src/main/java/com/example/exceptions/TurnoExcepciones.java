package com.example.exceptions;

public class TurnoExcepciones {

    // Excepción para cuando un turno ya existe (duplicado)

    public static class TurnoDuplicadoException extends Exception {
        public TurnoDuplicadoException(String mensaje) {
            super(mensaje);
        }
    }

    // Excepción para cuando un turno no existe (por ejemplo, al intentar actualizar o eliminar)

    public static class TurnoNoExistenteException extends Exception {
        public TurnoNoExistenteException(String mensaje) {
            super(mensaje);
        }
    }

    // Excepción para cuando hay un error a la hora de actualizar los datos del turno

    public static class TurnoActualizadoException extends Exception {
        public TurnoActualizadoException(String mensaje) {
            super(mensaje);
        }
    }

    // Excepción para cuando hay un error a la hora de eliminar el turno
    public static class TurnoEliminadoException extends Exception {
        public TurnoEliminadoException(String mensaje) {
            super(mensaje);
        }
    }

    // Excepción para cuando hay un error a la hora de crear el turno
    public static class TurnoCreadoException extends Exception {
        public TurnoCreadoException(String mensaje) {
            super(mensaje);
        }
    }
}
