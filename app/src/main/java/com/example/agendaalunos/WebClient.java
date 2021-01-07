package com.example.agendaalunos;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class WebClient {
    public String post(String json) throws IOException {
        URL url = new URL("https://www.caelum.com.br/mobile");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //avisa que esta enviando um json
        connection.setRequestProperty("Content-type","application/json");
        //avisa que só aceita json
        connection.setRequestProperty("Accept","application/json");
        //escreve na saida padrao
        connection.setDoOutput(true);

        // escreve oque tem no json
        PrintStream output = new PrintStream(connection.getOutputStream());
        output.println(json);

        //estabelece conexao
        connection.connect();

        Scanner scanner = new Scanner(connection.getInputStream());
        String resposta = scanner.next();

        return resposta;
        
    }

}
