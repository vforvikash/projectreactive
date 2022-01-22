package com.viks.learn.projectreactive.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import static com.viks.learn.projectreactive.domain.Access.getDefaultNewInstance;

@Data
@AllArgsConstructor
public class User {

    public static final User VIKASH = new User("vkaush", "Vikash", "Kaushik", getDefaultNewInstance());
    // manager
    public static final User HETAL = new User("hbha", "Hetal", "Bhatia", getDefaultNewInstance());
    // new guy
    public static final User JASH = new User("jkaush", "Jash", "Kaushik", getDefaultNewInstance());
    public static final User MITESH = new User("mbha", "Mitesh", "Bhatia", getDefaultNewInstance());
    public static final User VIDHI = new User("vsolanki", "Vidhi", "Solanki", getDefaultNewInstance());
    public static final User KAKU = new User("skaku", "Sundip", "Kaku", getDefaultNewInstance());


    private String username;
    private String firstname;
    private String lastname;

    private Access access;
}
