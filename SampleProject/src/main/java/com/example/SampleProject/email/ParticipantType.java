package com.example.SampleProject.email;

public enum ParticipantType {
    REQUIRED("Required"), OPTIONAL("Optional"), RESOURCE("Resource");

    private final String _type;

    ParticipantType(final String type) {
        _type = type;
    }

    @Override
    public String toString() {
        return _type;
    }

}
