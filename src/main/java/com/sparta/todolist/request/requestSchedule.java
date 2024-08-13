package com.sparta.todolist.request;

public class requestSchedule {
    public String managerName;
    public int pwd;
    public String todo;

    public requestSchedule(String managerName, int pwd, String todo) {
        this.managerName = managerName;
        this.pwd = pwd;
        this.todo = todo;
    }
}
