package Praktikum_06_Code;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.text.*;

public class Competitor_ToDo implements Comparable<Competitor_ToDo>{
    private String name;
    private String country;
    private long time;
    private int jg;
    private int startNr;
    private int rank;

    public Competitor_ToDo(int startNr, String name, int jg, String country, String time) {
        this.startNr = startNr;
        this.name = name;
        this.jg = jg;
        this.country = country;
        try {
            this.time = parseTime(time);
        } catch (ParseException e) {
            System.out.println("Couldn't parse time from String to Long.");
            e.printStackTrace();
        }
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public int getJg() {
        return jg;
    }

    private static long parseTime(String s) throws ParseException {
        DateFormat sdf = new SimpleDateFormat("HH:mm:ss.S");
        Date date = sdf.parse(s);
        return date.getTime();
    }

    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.S");
        StringBuilder sb = new StringBuilder();
        sb.append(rank);sb.append(" ");
        sb.append(name); sb.append(" ");
        sb.append(Integer.toString(jg)); sb.append(" ");
        sb.append(df.format(new Date(time)));
        return sb.toString();
    }

    @Override
    public int compareTo(@NotNull Competitor_ToDo o) {
        return Long.compare(this.time, o.time);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Competitor_ToDo)) return false;
        Competitor_ToDo that = (Competitor_ToDo) o;
        return Objects.equals(getName(), that.getName());
    }
}