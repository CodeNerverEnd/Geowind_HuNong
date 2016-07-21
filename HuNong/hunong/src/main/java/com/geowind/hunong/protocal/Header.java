package com.geowind.hunong.protocal;

/**
 * Created by zhangwen on 16-7-19.
 */
public class Header {
    private  Leaf UserId=new Leaf("UserId");
    private  Leaf compress=new Leaf("compress");
    private  Leaf digest=new Leaf("digest");
    private  Leaf timeStamp=new Leaf("timeStamp");
    private  Leaf translateType=new Leaf("translateType");
}
