package pt.ipp.isep.esinf;

import java.util.Objects;

public class DoublyYearRate {

    private String year1;
    private String year2;

    private int ammount1;
    private int ammount2;

    public DoublyYearRate(String year1, String year2) {
        this.year1 = year1;
        this.year2 = year2;
    }

    public void setAmmount1(int ammount1) {
        this.ammount1 = ammount1;
    }

    public void setAmmount2(int ammount2) {
        this.ammount2 = ammount2;
    }

    public String getYear1() {
        return year1;
    }

    public String getYear2() {
        return year2;
    }

    public int getAmmount1() {
        return ammount1;
    }

    public int getAmmount2() {
        return ammount2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoublyYearRate that = (DoublyYearRate) o;
        return Objects.equals(year1, that.year1) && Objects.equals(year2, that.year2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year1, year2);
    }


    public double rate(){
        return (ammount2-ammount1)/ ((double) ammount1);
    }
}
