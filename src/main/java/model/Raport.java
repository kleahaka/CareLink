package model;

import java.time.LocalDate;

public class Raport {
    private Integer raportId;
    private LocalDate dataRaportit;
    private String rezultati;
    private String pershkrimi;
    private Integer kerkeseId;

    public Raport() {}

    public Raport(Integer raportId,LocalDate dataRaportit,String rezultati,String pershkrimi,Integer kerkeseId) {
        this.raportId = raportId;
        this.dataRaportit = dataRaportit;
        this.rezultati = rezultati;
        this.pershkrimi = pershkrimi;
        this.kerkeseId = kerkeseId;
    }

    public Integer getRaportId(){
        return raportId;
    }

    public void setRaportId( Integer raportId ){
        this.raportId = raportId;
    }

    public LocalDate getDataRaportit(){
        return dataRaportit;
    }

    public void setDataRaportit( LocalDate dataRaportit ){
        this.dataRaportit = dataRaportit;
    }

    public String getRezultati(){
        return rezultati;
    }

    public void setRezultati( String rezultati ){
        this.rezultati = rezultati;
    }

    public String getPershkrimi(){
        return pershkrimi;
    }

    public void setPershkrimi( String pershkrimi ){
        this.pershkrimi = pershkrimi;
    }

    public Integer getKerkeseId(){
        return kerkeseId;
    }

    public void setKerkeseId( Integer kerkeseId ){
        this.kerkeseId = kerkeseId;
    }

    @Override
    public String toString() {
        return "Raport{" + rezultati + "}";
    }
}