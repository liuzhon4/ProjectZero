package demo.projectZero;

/**
 * Created by Edward on 2018/2/22.
 * Designed for SQLite Database
 *
 */

public class SingleCase {
    String id;
    String caseCharacter;
    String caseReason;

    public SingleCase() {

    }

    public SingleCase(String id, String caseCharacter, String caseReason) {
        this.id = id;
        this.caseCharacter = caseCharacter;
        this.caseReason = caseReason;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getID() {
        return this.id;
    }

    public void setCharacter(String character) {
        this.caseCharacter = character;
    }

    public String getCharacter() {
        return this.caseCharacter;
    }

    public void setReason(String reason) {
        this.caseReason = reason;
    }

    public String getReason() {
        return this.caseReason;
    }
}
