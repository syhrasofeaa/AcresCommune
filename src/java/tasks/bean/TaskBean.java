/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasks.bean;

/**
 *
 * @author user
 */

import java.io.Serializable;

public class TaskBean implements Serializable {
    private String taskID;
    private String taskName;
    private String taskDesc;
    private String dueDate;
    private String taskStatus;
    
    public TaskBean (){
        this.taskID = "";
        this.taskName = "";
        this.taskDesc = "";
        this.dueDate = "";
        this.taskStatus = "";
    }

    public TaskBean(String taskID, String taskName, String taskDesc, String dueDate, String taskStatus) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.taskDesc = taskDesc;
        this.dueDate = dueDate;
        this.taskStatus = taskStatus;
    }

    public String getTaskID() {
        return taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }


    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }
}
