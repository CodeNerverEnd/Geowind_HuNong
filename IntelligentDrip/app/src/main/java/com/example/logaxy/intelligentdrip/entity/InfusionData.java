package com.example.logaxy.intelligentdrip.entity;

import java.io.Serializable;

/**
 * Created by logaxy on 2017/3/12.
 */

public class InfusionData implements Serializable {
    private int building_num;    //楼号
    private int room_num;        //房间号
    private int bed_num;        //床号
    private int flag;        //标志
    //显示小数点后两位，单位 ml
    private float total_soup;    //药液总量
    private float residue_soup;    //药液剩余量
    private float speed;        //速度

    public InfusionData(int building_num, int room_num, int bed_num, int flag,
                        float total_soup, float residue_soup, float speed) {
        this.building_num = building_num;
        this.room_num = room_num;
        this.bed_num = bed_num;
        this.flag = flag;
        this.total_soup = total_soup;
        this.residue_soup = residue_soup;
        this.speed = speed;
    }


    public int getBuilding_num() {
        return building_num;
    }

    public int getRoom_num() {
        return room_num;
    }

    public int getBed_num() {
        return bed_num;
    }

    public int getFlag() {
        return flag;
    }

    public float getTotal_soup() {
        return total_soup;
    }

    public float getResidue_soup() {
        return residue_soup;
    }

    public float getSpeed() {
        return speed;
    }
}
