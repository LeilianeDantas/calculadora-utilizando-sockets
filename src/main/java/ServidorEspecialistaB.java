import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEspecialistaB {
    public static void main(String[] args) {
        try {
            // Configurar o servidor para ouvir em uma porta específica (por exemplo, 9002)
            ServerSocket serverSocket = new ServerSocket(9002);

            while (true) {
                // Aceitar conexões de clientes
                Socket clientSocket = serverSocket.accept();

                // Criar um novo thread para lidar com a solicitação do cliente
                Thread thread = new Thread(new ClientHandler(clientSocket, "B"));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private String serverName;

        public ClientHandler(Socket clientSocket, String serverName) {
            this.clientSocket = clientSocket;
            this.serverName = serverName;
        }

        @Override
        public void run() {
            try (
                    ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                    ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream())
            ) {
                // Receber a operação do cliente (porcentagem, raiz quadrada, potenciação)
                String operation = (String) inputStream.readObject();
                int a = (int) inputStream.readObject();
                int b = (int) inputStream.readObject();

                // Realizar a operação solicitada
                double result = 0;
                if (operation.equals("porcentagem")) {
                    result = (double) a / 100 * b;
                } else if (operation.equals("raizQuadrada")) {
                    result = Math.sqrt(a);
                } else if (operation.equals("potenciacao")) {
                    result = Math.pow(a, b);
                }

                // Enviar a resposta de volta para o cliente via socket
                outputStream.writeObject(result);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
