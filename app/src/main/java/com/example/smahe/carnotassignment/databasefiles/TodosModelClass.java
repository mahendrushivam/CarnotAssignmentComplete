package com.example.smahe.carnotassignment.databasefiles;

import android.widget.Toast;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by smahe on 11/15/2017.
 */
@Table(name = "Todos")
public class TodosModelClass extends Model {
    public  TodosModelClass()
    {
        super();
    }
    @Column(name = "todosId")
    public Integer todosId;
    @Column(name = "userId")
    public Integer userId;
    @Column(name = "title")
    public String title;
    @Column(name = "completed")
    public boolean completed;
}
