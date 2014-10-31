package com.mesquitestudio.models;

/**
 * Created by paulmoreno on 9/26/14.
 */
public class Campaign {

    int campaignId;
    String campaignName;
    int campaignRating;

    boolean selected;

    public Campaign(int campaignId, String campaignName, int campaignRating) {
        this.campaignId = campaignId;
        this.campaignName = campaignName;
        this.campaignRating = campaignRating;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public int getCampaignRating() {
        return campaignRating;
    }

    public void setCampaignRating(int campaignRating) {
        this.campaignRating = campaignRating;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
