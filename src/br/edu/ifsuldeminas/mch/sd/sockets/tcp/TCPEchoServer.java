package br.edu.ifsuldeminas.mch.sd.sockets.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPEchoServer {

    public static void main(String[] args) {
        int serverPort = 3000;
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
// Cria um socket de servidor para esperar por conex ̃ao dos clientes
            serverSocket = new ServerSocket(serverPort);
            System.out.printf("Servidor rodando...\n");
            while (true) {
// Espera e aceita conex ̃ao dos clientes
                socket = serverSocket.accept();
                System.out.printf("Cliente %s conectando na porta %d ",socket.getInetAddress().getHostAddress(),socket.getPort());
               
                new NovaThread(socket);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException ioExceptionOnClose) {
                ioExceptionOnClose.printStackTrace();
            }
        }
    }
}
