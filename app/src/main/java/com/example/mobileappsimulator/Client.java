package com.example.mobileappsimulator;
import android.util.Log;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket socket;
    private InetAddress serverAddress;
    private int portNumber;

    public Client() {

    }

    public void connect(String ip, String port) {

        try {
            this.serverAddress = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            portNumber = Integer.parseInt(port);
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //create a socket to make the connection with the server
                try {
                    socket = new Socket(serverAddress, portNumber);
                    System.out.println("Connect :)");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void sendToServer(final String message) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
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
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void close() {
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
