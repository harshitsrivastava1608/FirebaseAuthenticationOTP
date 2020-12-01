package com.example.labourassist;

public class Store {
    private String q,a;int in;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    public Store(String q, String a, int in) {
        this.q = q;
        this.a = a;
        this.in = in;
    }
}
