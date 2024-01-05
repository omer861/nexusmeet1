package com.example.SampleProject.email;

import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonParser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class EmailInviteImpl implements Invite{
    Properties properties;
    String username;
    String password;

    private final Log LOG = LogFactory.getLog(this.getClass());

    /**
     * Constructs an instance of this class. It accepts mail configuration
     * properties.
     *
     * @param properties
     *            the mail configuration properties. Properties should contain
     *            <code>mail.smtp.host</code>,
     *            <code>mail.smtp.socketFactory.port</code>,
     *            <code>mail.smtp.socketFactory.class</code>,
     *            <code>mail.smtp.auth</code>, <code>mail.smtp.port</code>,
     *            <code>username</code>, <code>password</code>.
     */
    public EmailInviteImpl(final Properties properties) {
        this.properties = properties;
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    @Override
    public void validate() throws ConfigurationException {
        LOG.info("Validating configuration properties for email");
        if (properties == null) {
            throw new ConfigurationException("Properties can not be null");
        }
        if (properties.get("mail.smtp.host") == null) {
            throw new ConfigurationException(
                    "Property mail.smtp.host is not given");
        }
        if (properties.get("mail.smtp.port") == null) {
            throw new ConfigurationException(
                    "Property mail.smtp.port is not given");
        }
        if ("true".equals(properties.get("mail.smtp.auth"))) {
            if (properties.get("username") == null) {
                throw new ConfigurationException("Username is not given");
            }
            if (properties.get("password") == null) {
                throw new ConfigurationException("Password is not given");
            }
        }
    }

    @Override
    public JsonParser createJsonParser(InputStream inputStream, Charset charset) throws IOException {
        return null;
    }

    @Override
    public JsonParser createJsonParser(String s) throws IOException {
        return null;
    }

    @Override
    public JsonParser createJsonParser(Reader reader) throws IOException {
        return null;
    }

    @Override
    public JsonGenerator createJsonGenerator(OutputStream outputStream, Charset charset) throws IOException {
        return null;
    }

    @Override
    public JsonGenerator createJsonGenerator(Writer writer) throws IOException {
        return null;
    }

    @Override
    public void sendInvite(final String subject, final String description,
                           final Participant from, final List<Participant> attendees,
                           final Date startDate, final Date endDate, final String location)
            throws Exception {

        this.properties.put("mail.smtp.socketFactory.port",
                properties.get("mail.smtp.port"));
        this.properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        this.properties.put("mail.smtp.socketFactory.fallback", "false");

        validate();

        LOG.info("Sending meeting invite");
        LOG.debug("Mail Properties :: " + this.properties);

       Session session;
        if (password != null) {
            session = Session.getInstance(this.properties,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username,
                                    password);
                        }
                    });
        } else {
            session = Session.getInstance(this.properties);
        }

        ICal cal = new ICal(subject, description, from, attendees, startDate,
                endDate, location);
        cal.init();

        StringBuffer sb = new StringBuffer();
        sb.append(from.getEmail());
        for (Participant bean : attendees) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(bean.getEmail());
        }
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from.getEmail()));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(sb.toString()));
        message.setSubject(subject);
        Multipart multipart = new MimeMultipart();
        MimeBodyPart iCal = new MimeBodyPart();
        iCal.setDataHandler(new DataHandler(new ByteArrayDataSource(
                new ByteArrayInputStream(cal.toByteArray()),
                "text/calendar;method=REQUEST;charset=\"UTF-8\"")));

        LOG.debug("Calender Request :: \n" + cal);

        multipart.addBodyPart(iCal);
        message.setContent(multipart);
        Transport.send(message);
    }

}
