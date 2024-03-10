package SocketModelo;

public class SocketS {

    // Función para serializar el array de strings
    public static String serializeStringArray(String[][] array) {
        StringBuilder serialized = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                serialized.append(array[i][j]);
                if (j < array[i].length - 1) {
                    serialized.append(",");
                }
            }
            if (i < array.length - 1) {
                serialized.append(";");
            }
        }
        return serialized.toString();
    }

    // Función para deserializar y convertir la cadena al array original
    public static String[][] deserializeStringArray(String serialized) {
        String[] rows = serialized.split(";");
        String[][] result = new String[8][8];
        for (int i = 0; i < rows.length; i++) {
            String[] columns = rows[i].split(",");
            for (int j = 0; j < columns.length; j++) {
                result[i][j] = columns[j];
            }
        }
        return result;
    }
}
