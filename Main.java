package de.laurenzgrote.bwinf35.sprichwort;

import de.laurenzgrote.bwinf35.sprichwort.datum.GregorianischesDatum;
import de.laurenzgrote.bwinf35.sprichwort.datum.JulianischesDatum;

public class Main {
    public static void main(String[] args) {
        long l1 = System.currentTimeMillis();
        String a1 = wannKatholischeOsternOrthodoxeWeihnacht();
        l1 = System.currentTimeMillis() - l1;

        long l2 = System.currentTimeMillis();
        String a2 = wannKatholischeWeihnachtOrthodoxeOstern();
        l2 = System.currentTimeMillis() - l2;

        System.out.printf("Kath./Ev. Ostern und Orth. Weihnachten finden am folgendem Datum gleichzeitig statt: %s. Zur Berechnung habe ich %dms gebraucht. \n",
                a1, l1);
        System.out.printf("Kath./Ev. Weihnachten und Orth. Ostern finden am folgendem Datum gleichzeitig statt: %s. Zur Berechnung habe ich %dms gebraucht. ",
                a2, l2);
    }

    private static String wannKatholischeOsternOrthodoxeWeihnacht() {
        int i = 2015;
        GregorianischesDatum katholischeOstern, orthodoxeWeihnacht;
        do {
            i++; // Los gehts also bei 2016
            katholischeOstern = GregorianischesDatum.getOsterdatum(i);
            // Das orth. Weihnachten müssen wir vom Vorjahr nehmen, da Weihnachten vom 25. Dez. in das neue Jahr hinein verschoben wird
            // Außerdem konvertieren wir es direkt zu einem gregorianischen Datum
            orthodoxeWeihnacht = new GregorianischesDatum(new JulianischesDatum(25, 12, i - 1));
        } while (!katholischeOstern.equals(orthodoxeWeihnacht));

        // Welche Variable wir ausgeben ist egal, sind ja inhaltsgleich....
        return katholischeOstern.toString();
    }
    private static String wannKatholischeWeihnachtOrthodoxeOstern() {
        int i = 2015;
        GregorianischesDatum katholischeWeihnacht, orthodoxeOstern;
        do {
            i++; // Los gehts also bei 2016
            katholischeWeihnacht = new GregorianischesDatum(25, 12, i);
            orthodoxeOstern = new GregorianischesDatum(JulianischesDatum.getOsterdatum(i));
        } while (!katholischeWeihnacht.equals(orthodoxeOstern));

        // Welche Variable wir ausgeben ist egal, sind ja inhaltsgleich....
        return katholischeWeihnacht.toString();
    }
}
