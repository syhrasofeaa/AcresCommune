/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipments.bean;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class CreateEquipmentBean implements Serializable{
    private String equipmentID;
    private String equipmentName;
    private String equipmentCondition;
    private String usedDate;
    
    public CreateEquipmentBean() {
        this.equipmentID = "";
        this.equipmentName = "";
        this.equipmentCondition = "";
        this.usedDate = "";
    }

    public CreateEquipmentBean(String EquipmentID, String EquipmentName, String EquipmentCondition, String UsedDate) {
        this.equipmentID = equipmentID;
        this.equipmentName = equipmentName;
        this.equipmentCondition = equipmentCondition;
        this.usedDate = usedDate;
    }

    public String getEquipmentID() {
        return equipmentID;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public String getEquipmentCondition() {
        return equipmentCondition;
    }

    public String getUsedDate() {
        return usedDate;
    }

    public void setEquipmentID(String equipmentID) {
        this.equipmentID = equipmentID;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public void setEquipmentCondition(String equipmentCondition) {
        this.equipmentCondition = equipmentCondition;
    }

    public void setUsedDate(String usedDate) {
        this.usedDate = usedDate;
    }
    
    
}
