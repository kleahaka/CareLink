package model;

import java.time.LocalDateTime;

public class Roli {
    private Integer roliId;
    private String roli;
    private LocalDateTime createdAt;

    public Roli() {}

    public Roli( Integer roliId,String roli,LocalDateTime createdAt ){
        this.roliId = roliId;
        this.roli = roli;
        this.createdAt = createdAt;
    }

    public Integer getRoliId(){
        return roliId;
    }

    public void setRoliId( Integer roliId ){
        this.roliId = roliId;
    }

    public String getRoli(){
        return roli;
    }

    public void setRoli( String roli ){
        this.roli = roli;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt( LocalDateTime createdAt ){
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return roli;
    }
}