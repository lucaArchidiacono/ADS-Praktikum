package Praktikum_09_Code;

import java.util.*;
import java.text.*;

/**
 * @author flurin gishamer
 * holds information about a single competitor from
 * the zurich marathon
 */
public class Competitor implements Comparable<Competitor>  {
    private String name;
    private String country;
    private long time;
    private int jg;
    private int startNr;
    private int rank;

    public Competitor(String name, int jg) {
        this.name = name;
        this.jg = jg;
    }

    public Competitor(int startNr, String name, int jg, String country, String time) throws Exception {
        this.name = name;
        this.country = country;
        this.jg = jg;
        this.startNr = startNr;
        this.time = parseTime(time);
    }

    public String getName() {
        return name;
    }

    public int getJg() {
        return jg;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    private static long parseTime(String s) throws ParseException {
        DateFormat sdf = new SimpleDateFormat("HH:mm:ss.S");
        Date date = sdf.parse(s);
        return date.getTime();
    }

    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.S");
        StringBuilder sb = new StringBuilder();
        sb.append(" Start Nr: ");
        sb.append(startNr);
        sb.append(" ");
        sb.append(name);
        sb.append(" ");
        sb.append(Integer.toString(jg));
        sb.append(" ");
        sb.append(country);
        sb.append(" ");
        sb.append(df.format(new Date(time)));
        return sb.toString();
    }

    @Override
    public int compareTo(Competitor toCompare) {
        int c = name.compareTo(toCompare.name);
        c = (c != 0)?c:jg - toCompare.jg;
        return c;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 13 + name.hashCode();
        hash = hash * 17 + jg;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        return (compareTo((Competitor)obj) == 0);
    }


}