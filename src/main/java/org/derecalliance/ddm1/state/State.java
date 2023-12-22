package org.derecalliance.ddm1.state;

import java.util.ArrayList;

public class State {
    ArrayList<Secret> secrets = new ArrayList<>();
    ArrayList<Sharer> sharers = new ArrayList<>();
    String colorBackground = "#e0e0e0";
    String colorPrimary = "#ff0000";
    String colorSecondary = "#00ff00";

    private static final State instance = new State();

    // private constructor to avoid client applications using the constructor
    private State(){}

    public static State getInstance() {
        return instance;
    }

    public ArrayList<Secret> getSecrets() {
        return secrets;
    }
    public void addSecret(Secret secret) {
        this.secrets.add(secret);
    }

    public ArrayList<Sharer> getSharers() {
        return sharers;
    }
    public void addSharer(Sharer sharer) {
        this.sharers.add(sharer);
    }

    public String getColor(String which) {
        if ("primary".compareToIgnoreCase(which) == 0) {
            return colorPrimary;
        } else if ("secondary".compareToIgnoreCase(which) == 0) {
            return colorSecondary;
        }   else if ("background".compareToIgnoreCase(which) == 0) {
            return colorBackground;
        } else {
            return colorPrimary;
        }
    }
}
