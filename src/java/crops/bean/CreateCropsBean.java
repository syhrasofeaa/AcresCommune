/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crops.bean;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class CreateCropsBean implements Serializable {
    private String CropID;
    private String CropName;
    private String CropType;
    private String PlantingDate;
    private String HarvestDate;
    
    public CreateCropsBean() {
        this.CropID = "";
        this.CropName = "";
        this.CropType = "";
        this.PlantingDate = "";
        this.HarvestDate = "";
    }


    public CreateCropsBean(String CropID, String CropName, String CropType, String PlantingDate, String HarvestDate) {
        this.CropID = CropID;
        this.CropName = CropName;
        this.CropType = CropType;
        this.PlantingDate = PlantingDate;
        this.HarvestDate = HarvestDate;
    }

    public String getCropID() {
        return CropID;
    }

    public String getCropName() {
        return CropName;
    }

    public String getCropType() {
        return CropType;
    }

    public String getPlantingDate() {
        return PlantingDate;
    }

    public String getHarvestDate() {
        return HarvestDate;
    }

    public void setCropID(String CropID) {
        this.CropID = CropID;
    }

    public void setCropName(String CropName) {
        this.CropName = CropName;
    }

    public void setCropType(String CropType) {
        this.CropType = CropType;
    }

    public void setPlantingDate(String PlantingDate) {
        this.PlantingDate = PlantingDate;
    }

    public void setHarvestDate(String HarvestDate) {
        this.HarvestDate = HarvestDate;
    }
    
    
}
