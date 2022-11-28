package br.edu.ifsuldeminas.mch.sd.sockets.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NovaThread extends Thread {

    private DataInputStream inputFlow;
    private DataOutputStream outputFlow;
    private Socket socket;

    public NovaThread(Socket socket) {
        this.socket = socket;
        try {
            this.inputFlow = new DataInputStream(this.socket.getInputStream());
            this.outputFlow = new DataOutputStream(
                    this.socket.getOutputStream());

            this.start();
        } catch (IOException ioException) {
            System.err.printf("Error on creating new cliet connection: %s",
                    ioException.getMessage());
        }
    }

    public void run() {
        boolean clienteAtivo = true;

        while (clienteAtivo) {
            try {
                String clientMensagem = inputFlow.readUTF();
                System.err.printf("[%d-%d] Message: %s \n", this.getId(),
                        socket.getPort(), clientMensagem);
                outputFlow.writeUTF(clientMensagem);
            } catch (IOException ioException) {
                clienteAtivo = false;
                System.err.printf("[%d-%d] Socket closed.\n", this.getId(),
                        socket.getPort());
            }
        }
    }
}
