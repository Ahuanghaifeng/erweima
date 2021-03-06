package com.example.request.api.download.manager;

public class OtherHttpErrorException extends DownloadException {

    private static final long serialVersionUID = 1L;

    public OtherHttpErrorException(String message) {
        super(message);
    }

    public OtherHttpErrorException(String message, String extra) {
        super(message, extra);
    }
}
