package edu.pawm.lab4;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Animal implements Serializable {

    private int id;
    private String gatunek;
    private String kolor;
    private float wielkosc;
    private String opis;

    public Animal(){}

    public Animal(String gatunek, String kolor, float wielkosc, String opis){
        super();
        this.gatunek = gatunek;
        this.kolor = kolor;
        this.wielkosc = wielkosc;
        this.opis = opis;
    }

    public Animal(int id, String gatunek, String kolor, float wielkosc, String opis){
        super();
        this.id = id;
        this.gatunek = gatunek;
        this.kolor = kolor;
        this.wielkosc = wielkosc;
        this.opis = opis;
    }

    @NonNull
    @Override
    public String toString() {
        return "Zwierze: [id=" + id + ", gatunek=" + gatunek + ", kolor=" + kolor + " wielkosc=" + wielkosc + " ]";
    }

    public String getOpis() { return opis;}
    public String getGatunek() { return gatunek;}
    public String getKolor() { return kolor;}
    public float getWielkosc() { return wielkosc;}
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
}
