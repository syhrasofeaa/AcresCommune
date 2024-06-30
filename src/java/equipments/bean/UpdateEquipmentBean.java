/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipments.bean;

/**
 *
 * @author user
 */
public class UpdateEquipmentBean {
    private String EquipmentID;
    private String EquipmentName;
    private String EquipmentCondition;
    private String UsedDate;

    public UpdateEquipmentBean() {
        this.EquipmentID = "";
        this.EquipmentName = "";
        this.EquipmentCondition = "";
        this.UsedDate = "";
    }
    
    public UpdateEquipmentBean(String EquipmentID, String EquipmentName, String EquipmentCondition, String UsedDate) {
        this.EquipmentID = EquipmentID;
        this.EquipmentName = EquipmentName;
        this.EquipmentCondition = EquipmentCondition;
        this.UsedDate = UsedDate;
    }

    public String getEquipmentID() {
        return EquipmentID;
    }

    public String getEquipmentName() {
        return EquipmentName;
    }

    public String getEquipmentCondition() {
        return EquipmentCondition;
    }

    public String getUsedDate() {
        return UsedDate;
    }

    public void setEquipmentID(String EquipmentID) {
        this.EquipmentID = EquipmentID;
    }

    public void setEquipmentName(String EquipmentName) {
        this.EquipmentName = EquipmentName;
    }

    public void setEquipmentCondition(String EquipmentCondition) {
        this.EquipmentCondition = EquipmentCondition;
    }

    public void setUsedDate(String UsedDate) {
        this.UsedDate = UsedDate;
    }
    
    
}
