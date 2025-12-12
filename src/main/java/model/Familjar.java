package model;

public class Familjar {
    private Integer familjarId;
    private String lidhjaFamiljare;
    private String kontakt;
    private Integer userId;

    public Familjar() {}

    public Familjar(Integer familjarId, String lidhjaFamiljare, String kontakt, Integer userId) {
        this.familjarId = familjarId;
        this.lidhjaFamiljare = lidhjaFamiljare;
        this.kontakt = kontakt;
        this.userId = userId;
    }

    public Integer getFamiljarId(){
        return familjarId;
    }

    public void setFamiljarId(Integer familjarId){
        this.familjarId = familjarId;
    }

    public String getLidhjaFamiljare(){
        return lidhjaFamiljare;
    }

    public void setLidhjaFamiljare(String lidhjaFamiljare){
        this.lidhjaFamiljare = lidhjaFamiljare;
    }

    public String getKontakt(){
        return kontakt;
    }

    public void setKontakt(String kontakt){
        this.kontakt = kontakt;
    }

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this.userId = userId;
    }

    @Override
    public String toString() {
        return lidhjaFamiljare + " (" + kontakt + ")";
    }
}