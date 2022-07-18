import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        //Realizar uma conexão HTTP e buscar os top 250 Filmes
        //String que guarda o endereço de acesso da API
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        //Criação de um objeto URI utilizando a string acima
        URI address = URI.create(url);
        //Criação do objeto Http
        HttpClient clientHttp = HttpClient.newHttpClient();
        //Criação do objeto request
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        //Criação do objeto response
        HttpResponse<String> response = clientHttp.send(request, HttpResponse.BodyHandlers.ofString());
        //Guardando o resultado da pesquisa em uma String
        String body = response.body();

        //Filtrar apenas os dados que são interessantes para a aplicação, titulo, poster, nota
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //Checando se a lista retornada possui 250 registros
        //System.out.println("Tamanho da Lista: "+ listaDeFilmes.size());

        //exibir e manipular os dados obtidos
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.print("Título: ");
            System.out.println(filme.get("title"));
            System.out.print("Nota: ");
            System.out.println(filme.get("imDbRating"));
            System.out.print("Link Poster: ");
            System.out.println(filme.get("image"));

            System.out.println(">------------------------------------------------------------->");
        }



    }

}
