package org.example.personalfinanceapp.exception;

import java.time.LocalDateTime;

public class ErrorResponse {

    private LocalDateTime timeStamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ErrorResponse(LocalDateTime timeStamp, int status, String error, String message, String path){

        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;

    }

    public void setTimeStamp(LocalDateTime timeStamp){

        this.timeStamp = timeStamp;
    }

    public LocalDateTime getTimeStamp(){

        return timeStamp;
    }

    public void setStatus(int status){

        this.status = status;
    }


    public int getStatus(){

        return status;
    }

    public void setError(String error){

        this.error = error;
    }

    public String getError(){

        return error;
    }

    public void setMessage(String message){

        this.message = message;
    }

    public String getMessage(){

        return message;
    }

    public void setPath(String path){

        this.path = path;
    }

    public String getPath(){

        return path;
    }

}
