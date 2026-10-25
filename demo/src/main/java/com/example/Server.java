package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(3000);
            System.out.println("connessione al server locale...");
            Socket s = ss.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            String message = "";
            do{
                message = in.readLine();
                double number1 = Double.parseDouble(in.readLine());
                double number2 = Double.parseDouble(in.readLine());
                String operatore = in.readLine();
                boolean errore;
                double result = 0;
                String messaggioErrore =  "Errore divisione per 0, operatore non disponibile o errore generico.";
                if(message.equals("accept")){
                    switch(operatore){
                        case "+":
                            result = number1 + number2;
                            break;
                        case "-":
                            result = number1 - number2;
                            break;
                        case "/":
                            if(number2 == 0){
                                errore = true;
                                System.out.println(messaggioErrore);
                            }else{
                                result = number1 / number2;
                            }
                            break;
                        case "*":
                            result = number1 * number2;
                            break;
                        default:
                            errore = true;
                            System.out.println(messaggioErrore);
                            break;
                    }

                    String risposta = "Il risultato della tua operazione è: " + result;
                    out.println(risposta);
                }else{
                    break;
                }
            }while(message.equals("accept"));
            ss.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}