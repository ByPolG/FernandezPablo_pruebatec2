package com.example.exceptions;

public class CiudadanoExcepciones {

    // Excepción para cuando un ciudadano con el mismo DNI ya existe (duplicado)

    public static class CiudadanoDuplicadoException extends Exception {
        public CiudadanoDuplicadoException(String mensaje) {
            super(mensaje);
        }
    }

    // Excepción para cuando hay un error a la hora de crear al ciudadano

    public static class CiudadanoCreadoException extends Exception {
        public CiudadanoCreadoException(String mensaje) {
            super(mensaje);
        }
    }

    // Excepción para cuando hay un error a la hora de actualizar los datos del ciudadano

    public static class CiudadanoActualizadoException extends Exception {
        public CiudadanoActualizadoException(String mensaje) {
            super(mensaje);
        }
    }

    // Excepción para cuando hay un error a la hora de eliminar al ciudadano

    public static class CiudadanoEliminadoException extends Exception {
        public CiudadanoEliminadoException(String mensaje) {
            super(mensaje);
        }
    }

    // Excepción para cuando un ciudadano no es encontrado en la base de datos (por ejemplo, al intentar actualizarlo o eliminarlo)

    public static class CiudadanoNoExistenteException extends Exception {
        public CiudadanoNoExistenteException(String mensaje) {
            super(mensaje);
        }
    }
}
