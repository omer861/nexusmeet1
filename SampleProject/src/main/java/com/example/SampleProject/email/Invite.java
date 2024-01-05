package com.example.SampleProject.email;

import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonParser;

import javax.naming.ConfigurationException;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

public interface Invite {


    void validate() throws ConfigurationException, com.example.SampleProject.email.ConfigurationException;

    JsonParser createJsonParser(InputStream inputStream, Charset charset) throws IOException;

    JsonParser createJsonParser(String s) throws IOException;

    JsonParser createJsonParser(Reader reader) throws IOException;

    JsonGenerator createJsonGenerator(OutputStream outputStream, Charset charset) throws IOException;

    JsonGenerator createJsonGenerator(Writer writer) throws IOException;

    void sendInvite(String subject, String description,
                    Participant from, List<Participant> attendees, Date startDate,
                    Date endDate, String location) throws Exception;
}
