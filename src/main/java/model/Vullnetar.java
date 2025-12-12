package model;

public class Vullnetar {
    private Integer vullnetarId;
    private String zoniSherbimit;
    private String disponueshmeria;
    private Integer userId;

    public Vullnetar() {}

    public Vullnetar(Integer vullnetarId, String zoniSherbimit, String disponueshmeria, Integer userId) {
        this.vullnetarId = vullnetarId;
        this.zoniSherbimit = zoniSherbimit;
        this.disponueshmeria = disponueshmeria;
        this.userId = userId;
    }

    public Integer getVullnetarId(){
        return vullnetarId;
    }

    public void setVullnetarId( Integer vullnetarId ){
        this.vullnetarId = vullnetarId;
    }

    public String getZoniSherbimit(){
        return zoniSherbimit;
    }

    public void setZoniSherbimit( String zoniSherbimit ){
        this.zoniSherbimit = zoniSherbimit;
    }

    public String getDisponueshmeria(){
        return disponueshmeria;
    }

    public void setDisponueshmeria( String disponueshmeria ){
        this.disponueshmeria = disponueshmeria;
    }

    public Integer getUserId(){
        return userId;
    }

    public void setUserId( Integer userId ){
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Vullnetar{" + vullnetarId + ", zona=" + zoniSherbimit + '}';
    }
}