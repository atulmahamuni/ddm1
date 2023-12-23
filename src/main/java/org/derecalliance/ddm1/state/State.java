package org.derecalliance.ddm1.state;

import java.net.URI;
import java.util.ArrayList;

public class State {
    String name;
    URI uri;
    ArrayList<Secret> secrets = new ArrayList<>();
    ArrayList<Sharer> sharers = new ArrayList<>();
    String colorBackground = "#e0e0e0";
    String colorPrimary = "#ff0000";
    String colorSecondary = "#00ff00";

    boolean helperStarted = false;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public boolean isHelperStarted() {
        return helperStarted;
    }

    public void setHelperStarted(boolean helperStarted) {
        this.helperStarted = helperStarted;
    }
}
