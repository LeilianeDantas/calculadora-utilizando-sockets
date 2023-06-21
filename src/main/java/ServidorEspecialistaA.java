import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEspecialistaA {
    public static void main(String[] args) {
        try {
            // Configurar o servidor para ouvir em uma porta específica (por exemplo, 9001)
            ServerSocket serverSocket = new ServerSocket(9001);

            while (true) {
                // Aceitar conexões de clientes
                Socket clientSocket = serverSocket.accept();

                // Criar um novo thread para lidar com a solicitação do cliente
                Thread thread = new Thread(new ClientHandler(clientSocket, "A"));
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
                // Receber a operação do cliente (adição, subtração, multiplicação, divisão)
                String operation = (String) inputStream.readObject();
                int a = (int) inputStream.readObject();
                int b = (int) inputStream.readObject();

                // Realizar a operação solicitada
                int result = 0;
                if (operation.equals("soma")) {
                    result = a + b;
                } else if (operation.equals("subtracao")) {
                    result = a - b;
                } else if (operation.equals("multiplicacao")) {
                    result = a * b;
                } else if (operation.equals("divisao")) {
                    result = a / b;
                }

                // Enviar a resposta de volta para o cliente via socket
                outputStream.writeObject(result);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
