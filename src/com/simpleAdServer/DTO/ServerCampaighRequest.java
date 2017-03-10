package com.simpleAdServer.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ServerCampaighRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7379962850583899593L;
	

    @NotNull(message = "{partnerId.notnull.message}")
    private String partnerId;

    @Min(value = 1,message = "{duration.min.message}")
    private int duration;

    @NotNull(message = "{adContent.notnull.message}")
    private String adContent;

    @JsonProperty(value = "partner_id")
    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
    @JsonProperty(value = "duration")
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @JsonProperty(value = "ad_content")
    public String getAdContent() {
        return adContent;
    }

    public void setAdContent(String adContent) {
        this.adContent = adContent;
    }

    @Override
    public String toString() {
        return "partner_id:" + partnerId +", duration: " + duration + ", adContent: " + adContent;
    }
}
