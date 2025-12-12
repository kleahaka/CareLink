package model;

public class IMoshuar {
    private Integer imoshuarId;
    private Integer mosha;
    private String adresa;
    private String kontaktEmergjence;
    private Integer userId;

    public IMoshuar() {}

    public IMoshuar(Integer imoshuarId, Integer mosha, String adresa, String kontaktEmergjence, Integer userId) {
        this.imoshuarId = imoshuarId;
        this.mosha = mosha;
        this.adresa = adresa;
        this.kontaktEmergjence = kontaktEmergjence;
        this.userId = userId;
    }

    public Integer getImoshuarId(){
        return imoshuarId;
    }

    public void setImoshuarId( Integer imoshuarId ){
        this.imoshuarId = imoshuarId;
    }

    public Integer getMosha(){
        return mosha;
    }

    public void setMosha( Integer mosha ){
        this.mosha = mosha;
    }

    public String getAdresa(){
        return adresa;
    }

    public void setAdresa( String adresa ){
        this.adresa = adresa;
    }

    public String getKontaktEmergjence(){
        return kontaktEmergjence;
    }

    public void setKontaktEmergjence( String kontaktEmergjence ){
        this.kontaktEmergjence = kontaktEmergjence;
    }

    public Integer getUserId(){
        return userId;
    }

    public void setUserId( Integer userId ){
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "IMoshuar{" + "id=" + imoshuarId + ", mosha=" + mosha + ", adresa='" + adresa + '\'' + '}';
    }
}