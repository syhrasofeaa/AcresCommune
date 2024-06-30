/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasks.bean;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class UpdateTaskBean implements Serializable{
    private String TaskID;
    private String TaskName;
    private String TaskDesc;
    private String AssignedTo;
    private String DueDate;
    private String TaskStatus;

    public UpdateTaskBean() {
        this.TaskID = "";
        this.TaskName = "";
        this.TaskDesc = "";
        this.AssignedTo = "";
        this.DueDate = "";
        this.TaskStatus = "";
    }
    
    public UpdateTaskBean(String TaskID, String TaskName, String TaskDesc, String AssignedTo, String DueDate, String TaskStatus) {
        this.TaskID = TaskID;
        this.TaskName = TaskName;
        this.TaskDesc = TaskDesc;
        this.AssignedTo = AssignedTo;
        this.DueDate = DueDate;
        this.TaskStatus = TaskStatus;
    }

    public String getTaskID() {
        return TaskID;
    }

    public String getTaskName() {
        return TaskName;
    }

    public String getTaskDesc() {
        return TaskDesc;
    }

    public String getAssignedTo() {
        return AssignedTo;
    }

    public String getDueDate() {
        return DueDate;
    }

    public String getTaskStatus() {
        return TaskStatus;
    }

    public void setTaskID(String TaskID) {
        this.TaskID = TaskID;
    }

    public void setTaskName(String TaskName) {
        this.TaskName = TaskName;
    }

    public void setTaskDesc(String TaskDesc) {
        this.TaskDesc = TaskDesc;
    }

    public void setAssignedTo(String AssignedTo) {
        this.AssignedTo = AssignedTo;
    }

    public void setDueDate(String DueDate) {
        this.DueDate = DueDate;
    }

    public void setTaskStatus(String TaskStatus) {
        this.TaskStatus = TaskStatus;
    }
    
    
}
