package de.laurenzgrote.bwinf35.osterrechner.datum;

public class GregorianischesDatum extends Datum {

    // Später zum Umrechnen wichtig
    private final int[] monatslaengen = {0, 31, 28, 31, 30 , 31, 30, 31, 31, 30, 31, 30, 31};
    private final int[] monatslaengenSchaltjahr = {0, 31, 29, 31, 30 , 31, 30, 31, 31, 30, 31, 30, 31};

    public GregorianischesDatum(int tag, int monat, int jahr) {
        super(tag, monat, jahr);
    }

    @Override
    public boolean isSchaltjahr() {
        return (getJahr() % 4 == 0 && getJahr() % 100 != 0) || (getJahr() % 400 == 0);
    }

    public static GregorianischesDatum getOsterdatum(int jahr) {
        int k = jahr / 100;
        int m = 15 + (3 * k + 3) / 4 - (8 * k + 13) / 25;
        int s = 2 - (3 * k + 3) / 4;
        int a = jahr % 19;
        int d = (19 * a + m) % 30;
        int r = (d + a / 11) / 29;
        int og = 21 + d - r;
        int sz = 7- (jahr + jahr / 4 + s) % 7;
        int oe = 7- (og - sz) % 7;
        int os = og + oe;

        if (os > 31) {
            return new GregorianischesDatum(os - 31, 4, jahr);
        } else {
            return new GregorianischesDatum(os, 3, jahr);
        }
    }
    public GregorianischesDatum(JulianischesDatum julianischesDatum) {
        super(julianischesDatum.getTag(), julianischesDatum.getMonat(), julianischesDatum.getJahr());

        int abstandInTagen = (getJahr() / 100) - (getJahr() / 400) - 2;

        setTag(getTag() + abstandInTagen);

        if (isSchaltjahr()) {
            if (getTag() > monatslaengenSchaltjahr[getMonat()]) {
                korrigiereUeberhang(monatslaengenSchaltjahr);
            }
        } else {
            if (getTag() > monatslaengen[getMonat()]) {
                korrigiereUeberhang(monatslaengen);
            }
        }
    }
    private void korrigiereUeberhang (int[] gueltigeMonatslaengen) {
        final int erlaubteTage = gueltigeMonatslaengen[getMonat()];

        int ueberhang = getTag() - erlaubteTage;
        setTag(erlaubteTage);

        while (ueberhang > 0) {
            if (getMonat() == 12) {
                setJahr(getJahr() + 1);
                setMonat(1);

                // gueltigeMonatslaengen festlegen
                if (isSchaltjahr()) {
                    gueltigeMonatslaengen = monatslaengenSchaltjahr;
                } else {
                    gueltigeMonatslaengen = monatslaengen;
                }
            } else {
                setMonat(getMonat() + 1);
            }
            if (ueberhang < gueltigeMonatslaengen[getMonat()]) {
                setTag(ueberhang);
                ueberhang = 0;
            } else {
                ueberhang -= gueltigeMonatslaengen[getMonat()];
            }
        }
    }
}
