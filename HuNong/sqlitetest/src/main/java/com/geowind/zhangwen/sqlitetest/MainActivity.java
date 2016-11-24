package com.geowind.zhangwen.sqlitetest;

import android.app.Activity;
import android.os.Bundle;

import com.geowind.zhangwen.sqlitetest.dao.impl.TaskDaoImpl;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Task task=new Task();
        task.setfUname("张三");
        task.setUname("李四");
        TaskDaoImpl taskDao=new TaskDaoImpl(getApplicationContext());
        //      taskDao.delete(0);
        long insert = taskDao.insert(task);
        System.out.println(insert);
        List<Task> tasks=taskDao.findAll();
        for(int i=0;i<tasks.size();i++){
            System.out.println(tasks.get(i).getfUname()+"  "+tasks.get(i).getUname());
        }
        taskDao.delete(0);
        taskDao.update(task);
        for(int i=0;i<tasks.size();i++){
            System.out.println(tasks.get(i).getfUname()+"  "+tasks.get(i).getUname());
        }
    }
}
