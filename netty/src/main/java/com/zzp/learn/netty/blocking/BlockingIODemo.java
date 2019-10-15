package com.zzp.learn.netty.blocking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BlockingIODemo {

    private static final int portNumber = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(portNumber);//1
        Socket clientSocket = serverSocket.accept();             //2
        BufferedReader in = new BufferedReader(                     //3
                new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
        String request, response;
        while ((request = in.readLine()) != null) {                 //4
            if ("Done".equals(request)) {                         //5
                break;
            }
        }
        response = processRequest(request);                        //6
        out.println(response);                                    //7
    }                                                        //8

    private static String processRequest(String request) {
        return "hello world!!!";
    }
}
