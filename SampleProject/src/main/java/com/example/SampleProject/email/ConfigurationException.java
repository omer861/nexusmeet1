package com.example.SampleProject.email;

public class ConfigurationException extends  Exception{
    private static final long serialVersionUID = -3349167754706175359L;

    public ConfigurationException() {
        super();
    }

    /**
     * @param message
     */
    public ConfigurationException(final String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public ConfigurationException(final Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public ConfigurationException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
