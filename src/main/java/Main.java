import spark.Spark;

public class Main {
    public static void main(String[] args) {
        // Configurar a porta do webservice
        Spark.port(8080);

        // Definir as rotas do webservice
        Spark.get("/soma/:a/:b", (request, response) -> {
            int a = Integer.parseInt(request.params(":a"));
            int b = Integer.parseInt(request.params(":b"));
            String result = ClienteSocket.enviarOperacao("A", "soma", a, b);
            return result;
        });

        Spark.get("/subtracao/:a/:b", (request, response) -> {
            int a = Integer.parseInt(request.params(":a"));
            int b = Integer.parseInt(request.params(":b"));
            String result = ClienteSocket.enviarOperacao("A", "subtracao", a, b);
            return result;
        });

        Spark.get("/multiplicacao/:a/:b", (request, response) -> {
            int a = Integer.parseInt(request.params(":a"));
            int b = Integer.parseInt(request.params(":b"));
            String result = ClienteSocket.enviarOperacao("A", "multiplicacao", a, b);
            return result;
        });

        Spark.get("/divisao/:a/:b", (request, response) -> {
            int a = Integer.parseInt(request.params(":a"));
            int b = Integer.parseInt(request.params(":b"));
            String result = ClienteSocket.enviarOperacao("A", "divisao", a, b);
            return result;
        });

        Spark.get("/porcentagem/:a/:b", (request, response) -> {
            int a = Integer.parseInt(request.params(":a"));
            int b = Integer.parseInt(request.params(":b"));
            String result = ClienteSocket.enviarOperacao("B", "porcentagem", a, b);
            return result;
        });

        Spark.get("/raizQuadrada/:a", (request, response) -> {
            int a = Integer.parseInt(request.params(":a"));
            String result = ClienteSocket.enviarOperacao("B", "raizQuadrada", a, 0);
            return result;
        });

        Spark.get("/potenciacao/:a/:b", (request, response) -> {
            int a = Integer.parseInt(request.params(":a"));
            int b = Integer.parseInt(request.params(":b"));
            String result = ClienteSocket.enviarOperacao("B", "potenciacao", a, b);
            return result;
        });

        // Configurar a porta do webservice
        Spark.port(8080);
    }
}
