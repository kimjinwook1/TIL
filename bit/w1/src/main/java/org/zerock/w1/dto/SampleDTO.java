package org.zerock.w1.dto;

public class SampleDTO {

    private String first;
    private String last;

    public String getFirst() {
        System.out.println("getFirst....... " + first);
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        System.out.println("getLast........ " + last);
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public String toString() {
        return "SampleDTO{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                '}';
    }
}
