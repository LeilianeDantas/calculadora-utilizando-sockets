import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClienteSocket {
    public static String enviarOperacao(String serverName, String operation, int a, int b) {
        try (
                Socket socket = new Socket("localhost", getServerPort(serverName));
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())
        ) {
            // Enviar a operação para o servidor
            outputStream.writeObject(operation);
            outputStream.writeObject(a);
            outputStream.writeObject(b);

            // Receber o resultado do servidor
            String result = inputStream.readObject().toString();
            return result;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Erro na comunicação com o servidor";
        }
    }

    private static int getServerPort(String serverName) {
        if (serverName.equals("A")) {
            return 9001; // Porta do servidor especialista A
        } else if (serverName.equals("B")) {
            return 9002; // Porta do servidor especialista B
        } else {
            throw new IllegalArgumentException("Servidor inválido");
        }
    }
}
