package com.necis.firebasechat2.item;

/**
 * Created by Jarcode on 2016-03-27.
 */
public class Chat_Item {
    String id,name,message,date;

    public Chat_Item(){

    }
    public Chat_Item(String id,String name,String message,String date){
        this.id = id;
        this.name = name;
        this.message = message;
        this.date = date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {

        return date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
