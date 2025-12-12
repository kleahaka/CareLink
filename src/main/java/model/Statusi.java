package model;

public class Statusi {
    private Integer statusiId;
    private String statusi;
    private String ndryshoNeOrarit;

    public Statusi() {}

    public Statusi(Integer statusiId, String statusi, String ndryshoNeOrarit) {
        this.statusiId = statusiId;
        this.statusi = statusi;
        this.ndryshoNeOrarit = ndryshoNeOrarit;
    }

    public Integer getStatusiId(){
        return statusiId;
    }

    public void setStatusiId(Integer statusiId){
        this.statusiId = statusiId;
    }

    public String getStatusi(){
        return statusi;
    }

    public void setStatusi(String statusi){
        this.statusi = statusi;
    }

    public String getNdryshoNeOrarit(){
        return ndryshoNeOrarit;
    }

    public void setNdryshoNeOrarit(String ndryshoNeOrarit){
        this.ndryshoNeOrarit = ndryshoNeOrarit;
    }

    @Override
    public String toString() {
        return statusi;
    }
}