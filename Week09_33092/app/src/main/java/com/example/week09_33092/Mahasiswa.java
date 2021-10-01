package com.example.week09_33092;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "tblMahasiswa")
public class Mahasiswa implements Serializable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "nim")
    private String nim;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "phonenumber")
    private String phoneNumber;

    public Mahasiswa(String nim, String name, String email, String phoneNumber){
        this.nim = nim;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void setNim(@NonNull String nim) {
        this.nim = nim;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @NonNull
    public String getNim() {
        return nim;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
