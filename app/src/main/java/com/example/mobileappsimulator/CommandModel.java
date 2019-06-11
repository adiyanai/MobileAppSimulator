package com.example.mobileappsimulator;


public class CommandModel {

    private Client client = new Client();

    // static variable single_instance of type CommandModel
    private static CommandModel instance = null;

    // private constructor restricted to this class itself
    private CommandModel() {
    }

    // static method to create instance of CommandModel class
    public static CommandModel getInstance() {
        if (instance == null)
            instance = new CommandModel();
        return instance;
    }

    public void connect(String ip, String port) {
        this.client.connect(ip, port);
    }


    public void sendMessage(String message) {
        this.client.sendToServer(message);
    }

    public void close() {
        this.client.close();
    }
}
