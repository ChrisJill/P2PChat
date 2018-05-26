package com.rdc.p2p.bean;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lin Yaotian on 2018/5/16.
 */
public class MessageBean {

    private String userIp;
    private int userImageId;
    private String nickName;
    private String text;
    private String time;
    private String imagePath;
    private String audioPath;
    private String filePath;
    private String fileName;
    private String fileSize;
    private int msgType;//消息类型 音频/图片/文字
    private boolean isMine;//是否是本人的消息

    @Override
    public String toString() {
        return "MessageBean{" +
                "userIp='" + userIp + '\'' +
                ", userImageId=" + userImageId +
                ", nickName='" + nickName + '\'' +
                ", text='" + text + '\'' +
                ", time='" + time + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", audioPath='" + audioPath + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", msgType=" + msgType +
                ", isMine=" + isMine +
                '}';
    }

    public PeerBean transformToPeerBean(){
        PeerBean peerBean = new PeerBean();
        peerBean.setRecentMessage(getText());
        peerBean.setNickName(nickName);
        peerBean.setUserImageId(userImageId);
        peerBean.setUserIp(userIp);
        return peerBean;
    }

    public UserBean transformToUserBean(){
        UserBean userBean = new UserBean();
        userBean.setUserImageId(userImageId);
        userBean.setNickName(nickName);
        return userBean;
    }

    public String getFilePath() {
        return filePath == null ? "" : filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? "" : filePath;
    }

    public String getFileName() {
        return fileName == null ? "" : fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? "" : fileName;
    }

    public String getFileSize() {
        return fileSize == null ? "" : fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize == null ? "" : fileSize;
    }

    public String getUserIp() {
        return userIp == null ? "" : userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp == null ? "" : userIp;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public int getUserImageId() {
        return userImageId;
    }

    public void setUserImageId(int userImageId) {
        this.userImageId = userImageId;
    }

    public String getNickName() {
        return nickName == null ? "" : nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? "" : nickName;
    }

    public String getText() {
        if (text == null || text.equals("")){
            if (audioPath != null){
                return "音频";
            }else if (imagePath != null){
                return "图片";
            }else if (filePath != null){
                return "文件";
            }
        }
        return text == null ? "" : text;
    }

    public void setText(String text) {
        this.text = text == null ? "" : text;
    }

    public String getTime() {
        if (time == null){
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date date = new Date();
            time = sdf.format(date);
        }
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? "" : time;
    }

    public String getImagePath() {
        return imagePath == null ? "" : imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? "" : imagePath;
    }

    public String getAudioPath() {
        return audioPath == null ? "" : audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath == null ? "" : audioPath;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }
}
