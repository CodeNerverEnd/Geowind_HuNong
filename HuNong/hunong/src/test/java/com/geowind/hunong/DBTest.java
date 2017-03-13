package com.geowind.hunong;


import android.content.ContentValues;
import android.content.Context;
import com.geowind.hunong.dao.DBHelper;
import com.geowind.hunong.dao.impl.TaskDaoImpl;
import com.geowind.hunong.entity.SystemMsg;
import com.geowind.hunong.entity.Task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;


/**
 * Created by zhangwen on 2016/11/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class DBTest {

    @Mock
    Context mMockContext;
    @Test
    public void is_table_Created() {

        DBHelper helper=new DBHelper(mMockContext);
        helper.getWritableDatabase();

    }
    @Test
    public void testUpdate(){
        TaskDaoImpl impl=new TaskDaoImpl(mMockContext);
        Task task=new Task();

//        task.setFaddr("南华大学");
//        task.setfUname("geowind");
        impl.update(task);
    }
    @Test
    public void testInsert(){
        TaskDaoImpl impl=new TaskDaoImpl(mMockContext);
        Task task=new Task();
        task.setTid(1);
        task.setMuser("张文");
        task.setAddress("湖南衡阳");
        impl.insert(task);
    }
    @Test
    public void testDelete(){
        TaskDaoImpl impl=new TaskDaoImpl(mMockContext);
        Task instance = impl.getInstance();

    }
    @Test
    public void testFindAll(){
     TaskDaoImpl impl=new TaskDaoImpl(mMockContext);
     List<Task> tasks= impl.findAll();
       if(tasks!=null){
           for (Task task:tasks){
               System.out.print(task.toString());
           }
       }
    }
}
