package com.example.hotel;

public class User {
   String Id ;
    String UserName;
    String Email;
 String RoomNo;

    public User(String id, String userName, String email, String roomNo) {
        Id = id;
        UserName = userName;
        Email = email;
        RoomNo = roomNo;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getRoomNo() {
        return RoomNo;
    }

    public void setRoomNo(String roomNo) {
        RoomNo = roomNo;
    }
}
