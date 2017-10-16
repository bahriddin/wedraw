package Data;

import java.io.Serializable;

/**
 * Created by zy on 16/10/17.
 */
public class Message implements Serializable {

    private String usernameValue;

    private int idValue;

    private int typeValue;

    // contentValue should be immutable
    private Serializable contentValue;

    public String username() {
        return usernameValue;
    }

    public int id() {
        return idValue;
    }

    public int type() {
        return typeValue;
    }

    public Serializable content() {
        return contentValue;
    }
}
