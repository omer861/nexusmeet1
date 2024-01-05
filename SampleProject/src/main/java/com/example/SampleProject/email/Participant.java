package com.example.SampleProject.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Participant {
    private String name;
    private String email;
    private ParticipantType type;


}
