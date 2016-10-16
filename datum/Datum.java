package de.laurenzgrote.bwinf35.osterrechner.datum;

public abstract class Datum {
    // Datum
    private int tag, monat, jahr;

    public Datum(int tag, int monat, int jahr) {
        this.tag = tag;
        this.monat = monat;
        this.jahr = jahr;
    }

    public int getJahr() { return jahr; }
    public void setJahr(int jahr) { this.jahr = jahr; }
    public int getTag() { return tag; }
    public void setTag(int tag) { this.tag = tag; }
    public int getMonat() { return monat; }
    public void setMonat(int monat) { this.monat = monat; }

    // Das ist spezifisch --> abstrakte Methode
    public abstract boolean isSchaltjahr();

    // Datum als Zeichenkette im deutschen Stil (TT.MM.JJJJ)
    @Override
    public String toString() {
        return tag + "." + monat + "." + jahr;
    }

    // Gleiches Datum bedeutet, dass Tag, Monat und Jahr übereinstimmen.
    @Override
    public boolean equals(Object o) {
        // Ein Julianisches Datum kann außerdem nie gleich einem Gregorianisches sein,
        // dafür muss zunächst das julianische Umgerechnet sein
        // NullPointer wär auch echt blöd
        if (o == null || getClass() != o.getClass()) return false;

        // Jetzt T, M und J vergleichen
        Datum datum = (Datum) o;
        return tag == datum.tag && monat == datum.monat && jahr == datum.jahr;
    }
}
