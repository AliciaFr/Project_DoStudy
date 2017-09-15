package com.example.alicia.dostudy;


public class Task {

    private String taskContent;
    private boolean isDone;

    public Task(String taskContent, boolean isDone) {
        this.taskContent = taskContent;
        this.isDone = isDone;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
