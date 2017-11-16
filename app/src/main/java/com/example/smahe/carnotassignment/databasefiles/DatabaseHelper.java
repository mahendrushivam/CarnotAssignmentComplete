package com.example.smahe.carnotassignment.databasefiles;

import android.content.Context;
import android.provider.MediaStore;

import com.activeandroid.ActiveAndroid;
import com.example.smahe.carnotassignment.URLMainClasses.CommentsClass;
import com.example.smahe.carnotassignment.URLMainClasses.ImagesClass;
import com.example.smahe.carnotassignment.URLMainClasses.PostClass;
import com.example.smahe.carnotassignment.URLMainClasses.TodoListClass;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by smahe on 11/14/2017.
 */

public class DatabaseHelper {

    Context context;

    public DatabaseHelper(Context context) {
        this.context = context;
    }

    public void setCommentTransaction(HashMap<Integer, ArrayList<CommentsClass>> commentTransaction) {
        ActiveAndroid.beginTransaction();
        try {
            for (Map.Entry<Integer, ArrayList<CommentsClass>> entry : commentTransaction.entrySet()) {

                ArrayList<CommentsClass> commentsClass = entry.getValue();
                for (CommentsClass comment : commentsClass) {
                    CommentsModelClass commentsModelClass = new CommentsModelClass();
                    commentsModelClass.body = comment.getBody();
                    commentsModelClass.commentId = comment.getId();
                    commentsModelClass.postId = comment.getPostid();
                    commentsModelClass.email = comment.getEmailid();
                    commentsModelClass.name = comment.getName();
                    commentsModelClass.save();
                }

            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }

    }


    public void setTodoTransaction(HashMap<Integer,ArrayList<TodoListClass>> todoTransaction)
    {
        ActiveAndroid.beginTransaction();
        try {
            for(Map.Entry<Integer,ArrayList<TodoListClass>> entry:todoTransaction.entrySet()){

                ArrayList<TodoListClass> todoListClass=entry.getValue();
                for ( TodoListClass todolist: todoListClass) {
                   TodosModelClass todosModel=new TodosModelClass();
                   todosModel.completed=todolist.getCompleted();
                   todosModel.title=todolist.getTitle();
                   todosModel.todosId=todolist.getTodoid();
                   todosModel.userId=todolist.getUserid();
                   todosModel.save();
                }

            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
    }
    public void setImageTransaction(HashMap<Integer,ArrayList<ImagesClass>> imagesTransaction)
    {
        ActiveAndroid.beginTransaction();
        try {
            for(Map.Entry<Integer,ArrayList<ImagesClass>> entry:imagesTransaction.entrySet()){

                ArrayList<ImagesClass> imagesClass=entry.getValue();
                for ( ImagesClass imagelist: imagesClass) {
                    ImagesModelClass imagesModelClass=new ImagesModelClass();
                    imagesModelClass.albumId=imagelist.getAlbumid();
                    imagesModelClass.phpotoId=imagelist.getId();
                    imagesModelClass.thumbnailUrl=imagelist.getThumbnailurl();
                    imagesModelClass.title=imagelist.getTitle();
                    imagesModelClass.url=imagelist.getUrl();
                    imagesModelClass.save();
                }

            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
    }

    public void setPostsTransaction(HashMap<Integer,ArrayList<PostClass>> postsTransaction)
    {
        ActiveAndroid.beginTransaction();
        try {
            for(Map.Entry<Integer,ArrayList<PostClass>> entry:postsTransaction.entrySet()){

                ArrayList<PostClass> postClass=entry.getValue();
                for ( PostClass postlist: postClass) {
                    PostsModelClass postsModelClass=new PostsModelClass();
                    postsModelClass.body=postlist.getBody();
                    postsModelClass.postId=postlist.getId();
                    postsModelClass.userId=postlist.getUserId();
                    postsModelClass.title=postlist.getTitle();
                    postsModelClass.save();
                }

            }
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
    }



}