package com.geowind.hunong.dao.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.geowind.hunong.annotation.Colnum;
import com.geowind.hunong.annotation.ID;
import com.geowind.hunong.annotation.TableName;
import com.geowind.hunong.dao.DBHelper;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * DAO抽象类
 * Created by zhangwen on 2016/11/18.
 *
 */

public abstract class DAOSupport<M> implements DAO<M>{
protected SQLiteDatabase db;
    protected   DBHelper mDBHelper;
    protected Context mContext;
    private String DAOSUPPORT="DAOSupport";

    @Override
    public long insert(M m) {
        //填充数据库中的Colnum
        //m:数据源 values:导入目标
         ContentValues values=new ContentValues();
        fillColumn(m,values);
        long result= db.insert(getTableName(),null,values);
        return result;
    }


    private void fillColumn(M m , ContentValues values) {

        Field[] fields=m.getClass().getDeclaredFields();
        for (Field item:fields){
            item.setAccessible(true);
            Colnum colnum=item.getAnnotation(Colnum.class);
            if(colnum!=null){
                String key=colnum.value();
                try {
                    String value;
                    if(item.get(m)!=null){
                        value=  item.get(m).toString();
                        //如果该field是主键且自增，不能添加到集合中
                        ID id = item.getAnnotation(ID.class);
                        if(id!=null&&id.autoincrement()){
                            continue;
                        }else {
                            values.put(key,value);
                        }
                    }


                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public int delete(Serializable id) {
        return db.delete(getTableName(),id+"=?",new String[]{id.toString()});
    }

    @Override
    public void deleteAll() {
        db.delete(getTableName(),null,null);
    }


    @Override
    public int update(M m) {
        ContentValues values=new ContentValues();
        fillColumn(m,values);
        return db.update(getTableName(),values ,getId(m)+"=?",new String[]{getId(m)});
    }


    //获取到主键封装的值
    private String getId(M m) {
        Field[] fields=m.getClass().getDeclaredFields();
        for(Field item : fields){
            item.setAccessible(true);
            ID id=item.getAnnotation(ID.class);
            if(id!=null){
                try {
                    return item.get(m).toString();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }

    @Override
    public List<M> findAll() {
        List<M> result=null;
        Cursor cursor=db.query(getTableName(),null,null,null,null,null,null);
        if(cursor!=null){
            result=new ArrayList<M>();
            while (cursor.moveToNext()){
                M m=getInstance();
                fillField(cursor,m);
                result.add(m);
            }
            cursor.close();
        }
        return result;
    }

    @Override
    public List<M> findByCondition(String[] columns, String selection, String[] selectionArgs, String orderBy, String limit) {

        List<M> result=null;
        Cursor cursor=db.query(getTableName(),columns,selection,selectionArgs,null,null,orderBy,limit);
        if(cursor!=null){
            result=new ArrayList<M>();
            while (cursor.moveToNext()){
                M m=getInstance();
                fillField(cursor,m);
                result.add(m);
            }
            cursor.close();
        }
        return result;
    }

    //将表中的数据导入到实体
    public void fillField(Cursor cursor, M m) {
        Field[] fields=m.getClass().getDeclaredFields();
        for (Field item : fields){
            item.setAccessible(true);
            Colnum colnum=item.getAnnotation(Colnum.class);
            if(colnum!=null){
                int columnIndex=cursor.getColumnIndex(colnum.value());
                String  value=cursor.getString(columnIndex);
                try {
                    if(item.getType()==int.class){
                        item.set(m,Integer.parseInt(value));
                    }else if(item.getType()== Date.class){
                        //转换成date类型
                    }
                    else if(item.getType()==double.class){
                        item.set(m,Double.parseDouble(value));
                    }
                    else {
                        item.set(m,value);
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public  DAOSupport(Context context){
        this.mContext=context;
        mDBHelper=new DBHelper(mContext);
        db=mDBHelper.getWritableDatabase();

    }
    //获取表名
    public String getTableName(){
        //每一个表对应一个具体实体
        //1.获取到实体的简单名称，首字母小写
        //2.利用注解，实体名称和数据库表的名称脱离关系

        //获取到实体
        M m=getInstance();
        //获取到实体的注解

        //如果需要在运行的时候获取到注解的信息，设置存活的时间
        TableName tableName=m.getClass().getAnnotation(TableName.class);//注解的类型
        if(tableName!=null){
            return tableName.value();
        }
        return "";
    }

    /**
     * 实体的创建
     * @return
     */
    public M getInstance(){
        //明确那个孩子调用此方法，获取该孩子的（支持泛型）父类
        Class c=getClass();
        Log.i("DAOSupport",c.getSimpleName());
        c.getSuperclass();
      Type superclass= c.getGenericSuperclass();
        Log.i("DAOSupport",superclass.toString());
        //获取泛型中的DAOSupport参数
        //泛型实现接口（参数化的类型），规定了所有泛型的通用接口
        if(superclass!=null&&superclass instanceof  ParameterizedType){
            Type[] arguments = ((ParameterizedType) superclass).getActualTypeArguments();
            try {
                return  (M)((Class)arguments[0]).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

        return  null;
    }
}
