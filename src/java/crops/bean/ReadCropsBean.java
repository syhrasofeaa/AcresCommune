/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crops.bean;

/**
 *
 * @author user
 */

import java.io.Serializable;

public class ReadCropsBean implements Serializable {
    private String cropID;
    private String cropName;
    private String cropType;
    private String plantingDate;
    private String harvestDate;
    
    public ReadCropsBean() {
        this.cropID = "";
        this.cropName = "";
        this.cropType = "";
        this.plantingDate = "";
        this.harvestDate = "";
    }

    public ReadCropsBean(String cropID, String cropName, String cropType, String plantingDate, String harvestDate) {
        this.cropID = cropID;
        this.cropName = cropName;
        this.cropType = cropType;
        this.plantingDate = plantingDate;
        this.harvestDate = harvestDate;
    }

    public String getCropID() {
        return cropID;
    }

    public String getCropName() {
        return cropName;
    }

    public String getCropType() {
        return cropType;
    }

    public String getPlantingDate() {
        return plantingDate;
    }

    public String getHarvestDate() {
        return harvestDate;
    }

    public void setCropID(String cropID) {
        this.cropID = cropID;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public void setPlantingDate(String plantingDate) {
        this.plantingDate = plantingDate;
    }

    public void setHarvestDate(String harvestDate) {
        this.harvestDate = harvestDate;
    }
}
