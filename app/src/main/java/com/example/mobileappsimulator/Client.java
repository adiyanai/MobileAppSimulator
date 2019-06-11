package com.example.mobileappsimulator;
import android.util.Log;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private Socket socket;

    public Client() {

    }

    public void connect(String ip, String port) {
        try {
            InetAddress serverAddress = InetAddress.getByName(ip);
            int portNumber;
            try {
                portNumber = Integer.parseInt(port);
            }
            catch (NumberFormatException e)
            {
                return;
            }

            //create a socket to make the connection with the server
            socket = new Socket(serverAddress, portNumber);
            System.out.println("Connect :)");
        } catch (Exception e) {
            Log.e("TCP", "C: Error", e);
        }
    }

    public void sendToServer(String message) {

        try {
            //sends the message to the server
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            bw.write(message);
            bw.flush();
        } catch (Exception e) {
            Log.e("TCP", "S: Error", e);
        }
    }

    public void close() {
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
