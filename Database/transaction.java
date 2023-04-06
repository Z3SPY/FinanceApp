package Database;

public class transaction {
    // ID . AMOUNT . DATE . TRANSACTION TYPE . FROM

    int id; 
    double amnt; 
    String date, from; 
    Swing.valueFrame.trnsType type;

    

    public int getId() {
        return id;
    }

    public double getAmnt() {
        return amnt;
    }

    public String getDate() {
        return  date;
    }

    public String getFrom() {
        return from;
    }

    public Swing.valueFrame.trnsType getType() {
        return type;
    }


    public transaction(int ID, double AMOUNT, String DATE, String FROM, Swing.valueFrame.trnsType TYPE) {
        id = ID;
        amnt = AMOUNT;
        date = DATE;
        from = FROM;
        type = TYPE;
    }
}
