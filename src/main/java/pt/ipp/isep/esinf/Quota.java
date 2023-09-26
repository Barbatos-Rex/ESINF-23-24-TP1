package pt.ipp.isep.esinf;

public class Quota {
    private int stalls;
    private int evs;

    private double ratio;

    private String country;

    public Quota(int stalls, int evs, double ratio, String country) {
        this.stalls = stalls;
        this.evs = evs;
        this.ratio = ratio;
        this.country = country;
    }

    public int getStalls() {
        return stalls;
    }

    public int getEvs() {
        return evs;
    }

    public double getRatio() {
        return ratio;
    }

    public String getCountry() {
        return country;
    }

    public double quota() {
        return ((stalls * ratio) / evs) * 100.0;
    }
}
