package pt.ipp.isep.esinf;

public class ComulativeAverage {
    private double ammount;
    private int times;

    public ComulativeAverage() {
        ammount=0;
        times=0;
    }

    public double getAverage(){
        return ammount/times;
    }

    public void increment(double ammount){
        this.ammount+=ammount;
        this.times++;
    }
}
