package com.adithya.worldchat;

public class ChatModel {

    public String name;
    public String message;

    public ChatModel() {

    }

    public ChatModel(String name, String message) {
        this.name = name;
        this.message = message;

    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
