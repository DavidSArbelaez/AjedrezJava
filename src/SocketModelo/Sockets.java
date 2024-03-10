package SocketModelo;

public class Sockets {

    // Función pa ra serializar el array de strings
    public String serializeStringArray(String[][] array) {
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
        System.out.println(serialized.toString());
        return serialized.toString();
    }

    // Función para deserializar y convertir la cadena al array original
    public String[][] deserializeStringArray(String serialized) {
        String[] rows = serialized.split(";");
        String[][] result = new String[8][8];
        for (int i = 0; i < rows.length; i++) {
            String[] columns = rows[i].split(",");
            for (int j = 0; j < columns.length; j++) {
                if(columns[j].equals(null)){
                    result[i][j] = "";
                }else{
                    result[i][j] = columns[j];
                }
                
            }
        }
        return result;
    }
}
