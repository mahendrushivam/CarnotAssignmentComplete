package com.example.smahe.carnotassignment.databasefiles;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.smahe.carnotassignment.URLMainClasses.CommentsClass;
import com.example.smahe.carnotassignment.URLMainClasses.ImagesClass;
import com.example.smahe.carnotassignment.URLMainClasses.PostClass;
import com.example.smahe.carnotassignment.URLMainClasses.TodoListClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.smahe.carnotassignment.SharedVariablesClass.URL2;

/**
 * Created by smahe on 11/16/2017.
 */

public class VolleyJSONReaderClass {
    Context context;
    HashMap<Integer, ArrayList<PostClass>> postClass;
    HashMap<Integer, ArrayList<CommentsClass>> commentsClass;
    HashMap<Integer, ArrayList<ImagesClass>> imagesClass;
    RequestQueue requestQueue;
    HashMap<Integer, ArrayList<TodoListClass>> todoListClass;

    public VolleyJSONReaderClass(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
        imagesClass=new HashMap<Integer, ArrayList<ImagesClass>>();
        commentsClass=new HashMap<Integer,ArrayList<CommentsClass>>();
        postClass=new HashMap<Integer, ArrayList<PostClass>>();
        todoListClass=new HashMap<Integer,ArrayList<TodoListClass>>();
    }

    public HashMap<Integer, ArrayList<ImagesClass>> returnPhotos() {
        JsonArrayRequest arrayreq2 = new JsonArrayRequest(URL2, new Response.Listener<JSONArray>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(JSONArray response) {
//Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                try {

                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject photos = array.getJSONObject(i);
                        ImagesClass imagesClas = new ImagesClass(photos.getString("title"), photos.getString("url"), photos.getString("thumbnailUrl"), photos.getInt("albumId"), photos.getInt("id"));
                        if (imagesClass.containsKey(photos.getInt("albumId"))) {
                            ArrayList<ImagesClass> imagesClasses = imagesClass.get(photos.getInt("albumId"));
                            imagesClasses.add(imagesClas);
                            imagesClass.remove(photos.getInt("albumId"));
                            imagesClass.put(photos.getInt("albumId"), imagesClasses);
                        } else {
                            ArrayList<ImagesClass> imagesClasses = new ArrayList<ImagesClass>();
                            imagesClasses.add(imagesClas);
                            imagesClass.put(photos.getInt("albumId"), imagesClasses);
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                });

requestQueue.add(arrayreq2);
        return imagesClass;
    }

    public HashMap<Integer, ArrayList<CommentsClass>> returnComments()
    {
        JsonArrayRequest arrayreq2 = new JsonArrayRequest(URL2,new Response.Listener<JSONArray>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(JSONArray response) {

                try {


                    JSONArray array=new JSONArray(response);
                    //Toast.makeText(getApplicationContext(),array.length(),Toast.LENGTH_LONG).show();
                    // Toast.makeText(getApplicationContext(),String.valueOf(array),Toast.LENGTH_LONG).show();
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject comments=array.getJSONObject(i);
                        //Toast.makeText(getApplicationContext(),String.valueOf(i),Toast.LENGTH_LONG).show();
                        CommentsClass commentClass=new CommentsClass(comments.getString("name"),comments.getString("email"),comments.getString("body"),comments.getInt("postId"),comments.getInt("id"));
                        if(commentsClass.containsKey(comments.getInt("postId")))
                        {
                            ArrayList<CommentsClass> commentArrayList=commentsClass.get(comments.getInt("postId"));
                            commentArrayList.add(commentClass);
                            commentsClass.remove(comments.getInt("postId"));
                            commentsClass.put(comments.getInt("postId"),commentArrayList);
                        }
                        else
                        {
                            ArrayList<CommentsClass> commentArrayList=new ArrayList<CommentsClass>();
                            commentArrayList.add(commentClass);
                            commentsClass.put(comments.getInt("postId"), commentArrayList);
                        }
                    }
                    //Toast.makeText(getApplicationContext(),String.valueOf(commentsClass.keySet()),Toast.LENGTH_LONG).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }}

                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                }
        );
        requestQueue.add(arrayreq2);
        return commentsClass;
    }

    public HashMap<Integer,ArrayList<TodoListClass>> returntodolist()
    {
        JsonArrayRequest arrayreq2 = new JsonArrayRequest(URL2,new Response.Listener<JSONArray>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(JSONArray response) {

                try {


                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++)
                    {JSONObject todos=array.getJSONObject(i);
                        TodoListClass todoList=new TodoListClass(todos.getString("title"),todos.getInt("userId"),todos.getInt("id"),todos.getBoolean("completed") );
                        if(todoListClass.containsKey(todos.getInt("userId")))
                        {
                            ArrayList<TodoListClass> todoListClassArrayList=todoListClass.get(todos.getInt("userId"));
                            todoListClassArrayList.add(todoList);
                            todoListClass.remove(todos.getInt("userId"));
                            todoListClass.put(todos.getInt("userId"),todoListClassArrayList);
                        }
                        else
                        {
                            ArrayList<TodoListClass> todoListClassArrayList = new ArrayList<TodoListClass>();
                            todoListClassArrayList.add(todoList);
                            todoListClass.put(todos.getInt("userId"), todoListClassArrayList);
                        }
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }}

                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                }
        );
        requestQueue.add(arrayreq2);
        return todoListClass;
    }

    public HashMap<Integer,ArrayList<PostClass>> returnposts()
    {
        JsonArrayRequest arrayreq2 = new JsonArrayRequest(URL2,new Response.Listener<JSONArray>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(JSONArray response) {

                try {


                    JSONArray array=new JSONArray(response);

                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject posts=array.getJSONObject(i);
                        PostClass postClas=new PostClass(posts.getString("title"),posts.getString("body"),posts.getInt("userId"),posts.getInt("id"));
                        if(postClass.containsKey(posts.getInt("userId")))
                        {
                            ArrayList<PostClass> postClassArrayList=postClass.get(posts.getInt("userId"));
                            postClassArrayList.add(postClas);
                            postClass.remove(posts.getInt("userId"));
                            postClass.put(posts.getInt("userId"),postClassArrayList);
                        }
                        else
                        {
                            ArrayList<PostClass> postClassArrayList=new ArrayList<PostClass>();
                            postClassArrayList.add(postClas);
                            postClass.put(posts.getInt("userId"),postClassArrayList);
                        }}



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }}

                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                }
        );

        requestQueue.add(arrayreq2);
        return  postClass;
    }


}
