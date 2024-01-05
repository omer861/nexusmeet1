package com.example.SampleProject.email;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.Cn;
import net.fortuna.ical4j.model.parameter.Role;
import net.fortuna.ical4j.model.property.*;
import net.fortuna.ical4j.util.CompatibilityHints;
import net.fortuna.ical4j.util.UidGenerator;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.Date;
import java.util.List;

public class ICal {



    private final String subject;
    private final String content;
    private final Date start;
    private final Date end;
    private final Participant from;
    String location;
    private final List<Participant> attendees;
    net.fortuna.ical4j.model.Calendar cal;

    /**
     * @param subject
     *            the invitation subject
     * @param content
     *            the invitation description
     * @param from
     *            the organizer
     * @param attendees
     *            list of attendees of this events
     * @param startDate
     *            the start date time of an event
     * @param endDate
     *            the end date time of an event
     * @param location
     *            the location of event
     */
    public ICal(final String subject, final String content,
                final Participant from, final List<Participant> attendees,
                final Date startDate, final Date endDate, final String location) {
        this.subject = subject;
        this.content = content;
        this.start = startDate;
        this.end = endDate;
        this.from = from;
        this.attendees = attendees;
        this.location = location;
        cal = new net.fortuna.ical4j.model.Calendar();
    }

    @Override
    public String toString() {
        return cal.toString();
    }

    public byte[] toByteArray() throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        CalendarOutputter outputter = new CalendarOutputter();
        outputter.output(cal, bout);
        return bout.toByteArray();
    }

    /**
     * creates a ICS calendar request
     *
     * @throws Exception
     */
    public void init() throws Exception {
        cal.getProperties().add(new ProdId("-//iloveoutlook//iCal4j 1.0//EN"));
        cal.getProperties().add(
                net.fortuna.ical4j.model.property.Version.VERSION_2_0);
        cal.getProperties().add(CalScale.GREGORIAN);
        cal.getProperties().add(
                net.fortuna.ical4j.model.property.Method.REQUEST);

        CompatibilityHints.setHintEnabled(
                CompatibilityHints.KEY_OUTLOOK_COMPATIBILITY, true);
        VEvent meeting = new VEvent();
        meeting.getProperties().add(new Summary(subject));
        meeting.getProperties().add(new Description(content));
        meeting.getProperties().add(new DtStart(new DateTime(start)));
        meeting.getProperties().add(new DtEnd(new DateTime(end)));
        if (location != null) {
            meeting.getProperties().add(new Location(location));
        }


        // generate unique identifier..
        //UidGenerator ug = new UidGenerator("3pillar");
        UidGenerator ug = new UidGenerator() {
            @Override
            public Uid generateUid() {
                return new Uid();
            }
        };
        Uid uid = ug.generateUid();
        meeting.getProperties().add(uid);

        // add organizer..
        Organizer organizer = new Organizer(URI.create("mailto:"
                + from.getEmail()));
        meeting.getProperties().add(organizer);

        // add attendees..
        Attendee attn = new Attendee(URI.create("mailto:" + from.getEmail()));
        if (ParticipantType.REQUIRED.equals(from.getType())) {
            attn.getParameters().add(Role.REQ_PARTICIPANT);
        } else if (ParticipantType.REQUIRED.equals(from.getType())) {
            attn.getParameters().add(Role.OPT_PARTICIPANT);
        } else {
            attn.getParameters().add(Role.NON_PARTICIPANT);
        }
        attn.getParameters().add(new Cn("3Pillar Labs"));
        meeting.getProperties().add(attn);

        for (Participant attendee : attendees) {
            Attendee dev2 = new Attendee(URI.create("mailto:"
                    + attendee.getEmail()));
            if (ParticipantType.REQUIRED.equals(attendee.getType())) {
                dev2.getParameters().add(Role.REQ_PARTICIPANT);
            } else if (ParticipantType.REQUIRED.equals(attendee.getType())) {
                dev2.getParameters().add(Role.OPT_PARTICIPANT);
            } else {
                dev2.getParameters().add(Role.NON_PARTICIPANT);
            }
            dev2.getParameters().add(new Cn("3Pillar Labs"));
            meeting.getProperties().add(dev2);
        }
        cal.getComponents().add(meeting);
    }
}
