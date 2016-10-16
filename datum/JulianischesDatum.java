package de.laurenzgrote.bwinf35.osterrechner.datum;

public class JulianischesDatum extends Datum{
    public JulianischesDatum(int tag, int monat, int jahr) {
        super(tag, monat, jahr);
    }

    @Override
    public boolean isSchaltjahr() {
        return getJahr() % 4 == 0;
    }

    public static JulianischesDatum getOsterdatum(int jahr) {
        int m = 15;
        int s = 0;
        int a = jahr % 19;
        int d = (19 * a + m) % 30;
        int r = (d + a / 11) / 29;
        int og = 21 + d - r;
        int sz = 7- (jahr + jahr / 4 + s) % 7;
        int oe = 7- (og - sz) % 7;
        int os = og + oe;

        if (os > 31) {
            return new JulianischesDatum(os - 31, 4, jahr);
        } else {
            return new JulianischesDatum(os, 3, jahr);
        }
    }
}
