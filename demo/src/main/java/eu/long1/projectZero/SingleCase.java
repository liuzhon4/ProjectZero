package eu.long1.projectZero;

/**
 * Created by Edward on 2018/2/22.
 * Designed for SQLite Database
 *
 */

public class SingleCase {
    Integer id;
    String caseCharacter;
    String caseReason;

    public SingleCase() {

    }

    public SingleCase(Integer id) {
        this.id = id;
    }

    public void setID(Integer id) {
        this.id = id;
    }

    public Integer getID() {
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
