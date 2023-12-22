package org.derecalliance.ddm1.state;

import java.util.ArrayList;

public class Secret {
    String name;
    String secretText;
    String apiId;
    ArrayList<Helper> helpers;
    ArrayList<Version> versions;
    public Secret(String _name, String _secretText) {
        name = _name;
        secretText = _secretText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecretText() {
        return secretText;
    }

    public void updateSecretText(String secretText) {
        this.secretText = secretText;
    }

    public ArrayList<Helper> getHelpers() {
        return helpers;
    }
    public void addHelper(Helper helper) {
        helpers.add(helper);
    }

    public ArrayList<Version> getVersions() {
        return versions;
    }

    public void addVersion(Version version) {
        versions.add(version);
    }
}
