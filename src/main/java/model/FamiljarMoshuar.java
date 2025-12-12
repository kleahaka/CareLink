package model;

public class FamiljarMoshuar {
    private Integer familjarId;
    private Integer imoshuarId;
    private String lidhjaFM;

    public FamiljarMoshuar() {}

    public FamiljarMoshuar(Integer familjarId, Integer imoshuarId, String lidhjaFM) {
        this.familjarId = familjarId;
        this.imoshuarId = imoshuarId;
        this.lidhjaFM = lidhjaFM;
    }

    public Integer getFamiljarId(){
        return familjarId;
    }

    public void setFamiljarId(Integer familjarId){
        this.familjarId = familjarId;
    }

    public Integer getImoshuarId(){
        return imoshuarId;
    }

    public void setImoshuarId(Integer imoshuarId){
        this.imoshuarId = imoshuarId;
    }

    public String getLidhjaFM(){
        return lidhjaFM;
    }

    public void setLidhjaFM(String lidhjaFM){
        this.lidhjaFM = lidhjaFM;
    }

    @Override
    public String toString() {
        return "FamiljarMoshuar{" + familjarId + ", " + imoshuarId + ", " + lidhjaFM + '}';
    }
}
