package org.derecalliance.ddm1;

import java.util.Date;

public class Version {
    public String getName() {
        return "Me";
    }

    public String getDescription() {
        return "Ver 1";
    }

    public String getDate() {
        return new Date().toString();
    }
}
