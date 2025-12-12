package model;

import java.time.LocalDate;

public class KerkeseNdihme {
    private Integer kerkeseId;
    private LocalDate dataKerkeses;
    private String lloji;
    private String pershkrimi;
    private LocalDate dataMbarimi;
    private Integer imoshuarId;
    private Integer vullnetarId;
    private Integer statusiId;

    public KerkeseNdihme() {}

    public KerkeseNdihme(Integer kerkeseId, LocalDate dataKerkeses, String lloji, String pershkrimi, LocalDate dataMbarimi, Integer imoshuarId, Integer vullnetarId, Integer statusiId) {
        this.kerkeseId = kerkeseId;
        this.dataKerkeses = dataKerkeses;
        this.lloji = lloji;
        this.pershkrimi = pershkrimi;
        this.dataMbarimi = dataMbarimi;
        this.imoshuarId = imoshuarId;
        this.vullnetarId = vullnetarId;
        this.statusiId = statusiId;
    }


    public Integer getKerkeseId(){
        return kerkeseId;
    }

    public void setKerkeseId(Integer kerkeseId){
        this.kerkeseId = kerkeseId;
    }

    public LocalDate getDataKerkeses(){
        return dataKerkeses;
    }

    public void setDataKerkeses(LocalDate dataKerkeses){
        this.dataKerkeses = dataKerkeses;
    }

    public String getLloji(){
        return lloji;
    }

    public void setLloji(String lloji){
        this.lloji = lloji;
    }

    public String getPershkrimi(){
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi){
        this.pershkrimi = pershkrimi;
    }

    public LocalDate getDataMbarimi(){
        return dataMbarimi;
    }

    public void setDataMbarimi(LocalDate dataMbarimi){
        this.dataMbarimi = dataMbarimi;
    }

    public Integer getImoshuarId(){
        return imoshuarId;
    }

    public void setImoshuarId(Integer imoshuarId){
        this.imoshuarId = imoshuarId;
    }

    public Integer getVullnetarId(){
        return vullnetarId;
    }

    public void setVullnetarId(Integer vullnetarId){
        this.vullnetarId = vullnetarId;
    }

    public Integer getStatusiId(){
        return statusiId;
    }

    public void setStatusiId(Integer statusiId){
        this.statusiId = statusiId;
    }

    @Override
    public String toString() {
        return lloji + " (" + pershkrimi + ")";
    }
}