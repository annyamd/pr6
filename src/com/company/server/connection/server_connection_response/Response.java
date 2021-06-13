package com.company.server.connection.server_connection_response;

import java.io.Serializable;

public class Response<T extends Serializable> implements Serializable { //класс с полем T
    private static final long serialVersionUID = 1L;

    //ответ результатом успешно выполненной команды: responseObj = ParamBox(может быть нулл, если команда ничего не возвращает),
    //isSuccessful = true, message = "Successful execution of command: ..."
    //
    //ответ сообщением об ошибке выполнения: responseObj = null, isSuccessful = false, message = сообщение об ошибке
    //

    private final T responseObj;
    private final boolean isSuccessful;
    private final String message;

    public Response(T responseObj, boolean isSuccessful, String message){
        this.responseObj = responseObj;
        this.isSuccessful = isSuccessful;
        this.message = message;
    }

    public T geResponseObj(){
        return responseObj;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "responseObj=" + responseObj +
                ", isSuccessful=" + isSuccessful +
                ", message='" + message + '\'' +
                '}';
    }
}
