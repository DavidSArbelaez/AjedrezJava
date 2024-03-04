import java.io.*;

public class Socket {
        // Función para serializar un String[][]
    public static byte[] serializarStringArray(String[][] arreglo) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            
            oos.writeObject(arreglo);
            return bos.toByteArray();
            
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al serializar el arreglo de cadenas.", e);
        }
    }
}
