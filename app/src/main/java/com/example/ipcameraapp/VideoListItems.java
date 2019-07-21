package com.example.ipcameraapp;

public class VideoListItems {

    public String videoPath; //Video URL
    public String Login = "";
    public String Pwd = "";

    public VideoListItems(String videoPath) {
        this.videoPath = videoPath;
    }

    public VideoListItems(String videoPath, String login, String pwd) {
        this.videoPath = videoPath;
        Login = login;
        Pwd = pwd;
    }

    public String getVideo() {
        return videoPath;
    }

    public String getLogin() {
        return Login;
    }

    public String getPwd() {
        return Pwd;
    }
}
