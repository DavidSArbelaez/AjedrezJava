package SocketModelo;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Socket {

    private static final String PASSWORD = "MaxVerstappen";

    public static String hashStrings(String[][] input) {
        try {
            // Concatenar todos los elementos del array en una sola cadena
            StringBuilder concatenatedInput = new StringBuilder();
            for (String[] row : input) {
                for (String element : row) {
                    concatenatedInput.append(element);
                }
            }

            // Concatenar la contraseña a la cadena resultante
            String inputWithPassword = concatenatedInput.toString() + PASSWORD;

            // Selecciona el algoritmo de hash SHA-3-256
            MessageDigest md = MessageDigest.getInstance("SHA3-256");

            // Convierte la cadena a bytes y actualiza el objeto MessageDigest
            md.update(inputWithPassword.getBytes());

            // Obtiene el hash en bytes
            byte[] hashedBytes = md.digest();

            // Convierte los bytes a formato hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            // Devuelve el hash como una cadena
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String verifyHash(String[][] input, String storedHash) {
        try {
            // Concatenar todos los elementos del array en una sola cadena
            StringBuilder concatenatedInput = new StringBuilder();
            for (String[] row : input) {
                for (String element : row) {
                    concatenatedInput.append(element);
                }
            }

            // Concatenar la contraseña a la cadena resultante
            String inputWithPassword = concatenatedInput.toString() + PASSWORD;

            // Selecciona el algoritmo de hash SHA-3-256
            MessageDigest md = MessageDigest.getInstance("SHA3-256");

            // Convierte la cadena a bytes y actualiza el objeto MessageDigest
            md.update(inputWithPassword.getBytes());

            // Obtiene el hash en bytes
            byte[] hashedBytes = md.digest();

            // Convierte los bytes a formato hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            // Compara el nuevo hash generado con el almacenado
            if (sb.toString().equals(storedHash)) {
                // La contraseña es correcta, devolver el hash deshasheado
                return concatenatedInput.toString();
            } else {
                // La contraseña es incorrecta, devolver un mensaje de error
                return "Error: El hash ha sido alterado.";
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Manejo de la excepción (puedes lanzarla o devolver un mensaje de error)
            return "Error al verificar el hash.";
        }
    }

    public static void main(String[] args) {
        // Ejemplo de uso con un array bidimensional
        String[][] inputArray = {{"Hola", ", ", "esto"}, {" es ", "un ", "ejemplo"}};
        String hashedString = hashStrings(inputArray);
        System.out.println("Array bidimensional original: " + Arrays.deepToString(inputArray));
        System.out.println("Hash SHA-3-256 con contraseña: " + hashedString);

        // Verificación del hash
        String result = verifyHash(inputArray, hashedString);
        System.out.println("Verificación del hash: " + result);
    }
}
