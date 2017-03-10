package com.simpleAdServer.DTO;


import java.util.Date;

public class ServerCampaigh {
    private String adContent;
    private String partnerId;
    private Date expiration;

    public ServerCampaigh() {
    }

    public ServerCampaigh(String adContent, String partnerId, Date expiration) {
        this.adContent = adContent;
        this.partnerId = partnerId;
        this.expiration = expiration;
    }

    public String getAdContent() {
        return adContent;
    }

    public void setAdContent(String adContent) {
        this.adContent = adContent;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

}
