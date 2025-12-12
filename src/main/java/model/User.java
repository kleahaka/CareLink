package model;

import java.time.LocalDateTime;

public class User {
    private Integer userId;
    private String emri;
    private String mbiemri;
    private String email;
    private String password;
    private Integer roliId;
    private LocalDateTime createdAt;
    private boolean verified;

    public User(){}
    public User(Integer userId, String emri, String mbiemri, String email, String password, Integer roliId, LocalDateTime createdAt) {
        this.userId = userId;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.email = email;
        this.password = password;
        this.roliId = roliId;
        this.createdAt = createdAt;
    }

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getEmri() {
        return emri;
    }
    public void setEmri(String emri) {
        this.emri = emri;
    }
    public String getMbiemri() {
        return mbiemri;
    }
    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getRoliId() {
        return roliId;
    }
    public void setRoliId(Integer roliId) {
        this.roliId = roliId;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public boolean isVerified() {
        return verified;
    }
    public void setVerified(boolean verified) {
        this.verified = verified;
    }
    @Override
    public String toString() {
        return emri + " " + mbiemri + " (" + email + ")";
    }
}
