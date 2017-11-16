package com.example.smahe.carnotassignment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.smahe.carnotassignment.URLMainClasses.CommentsClass;
import com.example.smahe.carnotassignment.URLMainClasses.ImagesClass;
import com.example.smahe.carnotassignment.URLMainClasses.PostClass;
import com.example.smahe.carnotassignment.URLMainClasses.TodoListClass;
import com.example.smahe.carnotassignment.URLMainClasses.URLInfoClass;
import com.example.smahe.carnotassignment.databasefiles.CommentsModelClass;
import com.example.smahe.carnotassignment.databasefiles.DatabaseHelper;
import com.example.smahe.carnotassignment.databasefiles.ImagesModelClass;
import com.example.smahe.carnotassignment.databasefiles.PostsModelClass;
import com.example.smahe.carnotassignment.databasefiles.TodosModelClass;
import com.example.smahe.carnotassignment.databasefiles.VolleyJSONReaderClass;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static com.example.smahe.carnotassignment.SharedVariablesClass.URL1;
import static com.example.smahe.carnotassignment.SharedVariablesClass.URL2;
import static com.example.smahe.carnotassignment.SharedVariablesClass.URL3;
import static com.example.smahe.carnotassignment.SharedVariablesClass.URL4;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    ArrayList<URLInfoClass> urlInfoClass;
    VolleyJSONReaderClass volleyJSONReader;
    DatabaseHelper  databaseHelper;
    HashMap<Integer,ArrayList<PostClass>> postClass;
    HashMap<Integer,ArrayList<CommentsClass>> commentsClass;
    HashMap<Integer,ArrayList<ImagesClass>> imagesClass;
    HashMap<Integer,ArrayList<TodoListClass>> todoListClass;
    AppCompatButton btn1,btn2,btn3,btn4,btn5;
    RequestQueue requestQueue;
    URLGridViewHolder urlGridViewHolder;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_screen);
        // ACTIVE ANDROID LIBRARY INTIALIZATION
        Configuration.Builder configurationBuilder = new Configuration.Builder(this);
        configurationBuilder.addModelClasses(CommentsModelClass.class, ImagesModelClass.class, TodosModelClass.class, PostsModelClass.class);

        ActiveAndroid.initialize(this);

        btn1=(AppCompatButton)findViewById(R.id.btn1);
        btn2=(AppCompatButton)findViewById(R.id.btn2);
        btn3=(AppCompatButton)findViewById(R.id.btn3);
        btn4=(AppCompatButton)findViewById(R.id.btn4);
        btn5=(AppCompatButton)findViewById(R.id.btn5);
        gridView = (GridView) findViewById(R.id.urlList);
        urlInfoClass=new ArrayList<URLInfoClass>();
        volleyJSONReader=new VolleyJSONReaderClass(getApplicationContext());
        databaseHelper=new DatabaseHelper(getApplicationContext());
        imagesClass=new HashMap<Integer, ArrayList<ImagesClass>>();
        commentsClass=new HashMap<Integer,ArrayList<CommentsClass>>();
        postClass=new HashMap<Integer, ArrayList<PostClass>>();
        todoListClass=new HashMap<Integer,ArrayList<TodoListClass>>();
        urlInfoClass=getData(urlInfoClass);
        urlGridViewHolder=new URLGridViewHolder(getApplicationContext(),R.layout.urlitemlayoutgrid,R.id.title,urlInfoClass);
        gridView.setAdapter(urlGridViewHolder);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                setAllGrids();
            }
        }, 5000);
        requestQueue = Volley.newRequestQueue(this);
        //SET RIPPPLE CLICK LISTENER FOR THE SELECTED EVENTS
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setStartDate(1);


                //Toast.makeText(getApplicationContext(),response.,Toast.LENGTH_LONG).show();

                StringRequest arrayreq2 = new StringRequest(Request.Method.POST,URL2,new Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONArray array = null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                                array = new JSONArray(response);

                            }




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

                        setEndDate(1);
                        setSavedStartTime(1);
                        databaseHelper.setImageTransaction(imagesClass);
                        //Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_LONG).show();
                        setSavedEndTime(1);


                    }

            },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();

                            }

                });
                requestQueue=Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(arrayreq2);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    setStartDate(0);
                JsonArrayRequest arrayreq2 = new JsonArrayRequest(URL1,new Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {


                            JSONArray array= null;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                array=new JSONArray(response);
                            }
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

                        setEndDate(0);
                        setSavedStartTime(0);
                        databaseHelper.setCommentTransaction(commentsClass);
                        setSavedEndTime(0);


                    }}

                ,
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }

                        }
                        );
                requestQueue.add(arrayreq2);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        setStartDate(2);
                JsonArrayRequest arrayreq2 = new JsonArrayRequest(URL3,new Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                       //oast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                        try {


                            JSONArray array= null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                                array = new JSONArray(response);
                            }
                            //Toast.makeText(getApplicationContext(),arrzzzzz,Toast.LENGTH_LONG).show();
                            //Toast.makeText(getApplicationContext(),String.valueOf("hello"),Toast.LENGTH_LONG).show();
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

                            //Toast.makeText(getApplicationContext(),String.valueOf(todoListClass.keySet()),Toast.LENGTH_LONG).show();



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        setEndDate(2);
                        setSavedStartTime(2);
                        databaseHelper.setTodoTransaction(todoListClass);
                        setSavedEndTime(2);

                    }}

                        ,
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }

                        }
                );
                requestQueue.add(arrayreq2);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    setStartDate(3);
                JsonArrayRequest arrayreq2 = new JsonArrayRequest(URL4,new Listener<JSONArray>() {

                    @SuppressLint("NewApi")
                    @Override
                    public void onResponse(JSONArray response) {

                        try {


                            JSONArray array= null;
                           array=new JSONArray(response);
                            Toast.makeText(getApplicationContext(),array.toString(),Toast.LENGTH_LONG).show();
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
                        setEndDate(3);
                        setSavedStartTime(3);
                        databaseHelper.setPostsTransaction(postClass);
                        setSavedEndTime(3);

                    }}

                        ,
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }

                        }
                );

                requestQueue.add(arrayreq2);

            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setAllGrids();

            }
        });

    }

    public ArrayList<URLInfoClass> getData(ArrayList<URLInfoClass> urlInfoClass)

    {
        for (int i = 0; i < 4; i++) {
            URLInfoClass urlInfo = new URLInfoClass("Start", "End", "Start Save", "End Save");
            urlInfoClass.add(urlInfo);
        }
            return urlInfoClass;
    }

   public void setStartDate(int position)
   {
       if(urlInfoClass.size()!=0)
       {
           URLInfoClass urlInfo=urlInfoClass.get(position);
           {
               String timeStamp;
               timeStamp = new SimpleDateFormat("dd.MM.yyyy-HH.mm.ss").format(new Date());
              urlInfo.setStartTime("Start "+timeStamp);
              urlInfo.setEndTime("End");
              urlInfo.setSavedEndTime("End Save");
              urlInfo.setSavedStartTime("Start Save");
               urlGridViewHolder=new URLGridViewHolder(getApplicationContext(),R.layout.urlitemlayoutgrid,R.id.title,urlInfoClass);
               urlGridViewHolder.notifyDataSetChanged();
               gridView.setAdapter(urlGridViewHolder);
           }
       }
   }

    public void setEndDate(int position)
    {
        if(urlInfoClass.size()!=0)
        {
            URLInfoClass urlInfo=urlInfoClass.get(position);
            {
                String timeStamp;
                timeStamp = new SimpleDateFormat("dd.MM.yyyy-HH.mm.ss").format(new Date());
                urlInfo.setEndTime("End "+timeStamp);
                urlGridViewHolder=new URLGridViewHolder(getApplicationContext(),R.layout.urlitemlayoutgrid,R.id.title,urlInfoClass);
                urlGridViewHolder.notifyDataSetChanged();
                gridView.setAdapter(urlGridViewHolder);
            }
        }
    }

    public  void setSavedStartTime(int position)
    {
        if(urlInfoClass.size()!=0)
        {
            URLInfoClass urlInfo=urlInfoClass.get(position);
            {
                String timeStamp;
                timeStamp = new SimpleDateFormat("dd.MM.yyyy-HH.mm.ss").format(new Date());
                urlInfo.setSavedStartTime("Start Save "+ timeStamp);
                urlGridViewHolder=new URLGridViewHolder(getApplicationContext(),R.layout.urlitemlayoutgrid,R.id.title,urlInfoClass);
                urlGridViewHolder.notifyDataSetChanged();
                gridView.setAdapter(urlGridViewHolder);
            }
        }
    }
    public void setSavedEndTime(int position)
    {URLInfoClass urlInfo=urlInfoClass.get(position);
        {
            String timeStamp;
            timeStamp = new SimpleDateFormat("dd.MM.yyyy-HH.mm.ss").format(new Date());
            urlInfo.setSavedEndTime("End Save "+timeStamp);
            urlGridViewHolder=new URLGridViewHolder(getApplicationContext(),R.layout.urlitemlayoutgrid,R.id.title,urlInfoClass);
            urlGridViewHolder.notifyDataSetChanged();
            gridView.setAdapter(urlGridViewHolder);
        }
    }
    public void setAllStartDate()
    {
        for(int i=0;i<4;i++)
        {
            URLInfoClass urlInfo=urlInfoClass.get(i);
            urlInfo.setStartTime("Start ");
            urlInfo.setEndTime("End");
            urlInfo.setSavedEndTime("End Save");
            urlInfo.setSavedStartTime("Start Save");

        }
        String timeStamp;
        timeStamp = new SimpleDateFormat("dd.MM.yyyy-HH.mm.ss").format(new Date());
        for(int i=0;i<4;i++)
        {
            URLInfoClass urlInfo=urlInfoClass.get(i);
            urlInfo.setStartTime("Start "+timeStamp);
        }
        urlGridViewHolder=new URLGridViewHolder(getApplicationContext(),R.layout.urlitemlayoutgrid,R.id.title,urlInfoClass);
        urlGridViewHolder.notifyDataSetChanged();
        gridView.setAdapter(urlGridViewHolder);
    }




    class URLGridViewHolder extends ArrayAdapter<URLInfoClass> {

        ArrayList<URLInfoClass> urlInfoClass;
        public URLGridViewHolder(Context context, int row_item, int text1, ArrayList<URLInfoClass> urlInfoClass) {
            super(context, row_item,text1,urlInfoClass);
            this.urlInfoClass=new ArrayList<URLInfoClass>();
            this.urlInfoClass=urlInfoClass;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = super.getView(position,convertView,parent);
            URLGridViewHolder.ViewHolder holder = (URLGridViewHolder.ViewHolder)row.getTag();

            if(holder==null)
            {
                holder = new ViewHolder(row);
                row.setTag(holder);
            }
            URLInfoClass urlInfo=urlInfoClass.get(position);
            holder.title.setText("URL"+String.valueOf(position+1));
            holder.startTime.setText(urlInfo.getStartTime());
            holder.endTime.setText(urlInfo.getEndTime());
            holder.savedstartTime.setText(urlInfo.getSavedStartTime());
            holder.savedEndTime.setText(urlInfo.getSavedEndTime());




            return row;
        }

        public class ViewHolder {
            AppCompatTextView startTime,endTime,savedstartTime,savedEndTime,title;

            ViewHolder(View row) {
                startTime=(AppCompatTextView)row.findViewById(R.id.startTime);
                title=(AppCompatTextView)row.findViewById(R.id.title);

                endTime=(AppCompatTextView)row.findViewById(R.id.endTime);
                savedstartTime=(AppCompatTextView)row.findViewById(R.id.startsavedTime);
                savedEndTime=(AppCompatTextView)row.findViewById(R.id.endsavedTime);
            }
        }

    }
    public void setAllGrids()
    {
        setAllStartDate();
        commentsClass=volleyJSONReader.returnComments();
        setEndDate(0);
        imagesClass=volleyJSONReader.returnPhotos();
        setEndDate(1);
        todoListClass=volleyJSONReader.returntodolist();
        setEndDate(2);
        postClass=volleyJSONReader.returnposts();
        setEndDate(3);
        setSavedStartTime(0);
        databaseHelper.setCommentTransaction(commentsClass);

        setSavedEndTime(0);
        setSavedStartTime(1);
        databaseHelper.setImageTransaction(imagesClass);
        setSavedEndTime(1);
        setSavedStartTime(2);
        databaseHelper.setTodoTransaction(todoListClass);
        setSavedEndTime(2);
        setSavedStartTime(3);
        databaseHelper.setPostsTransaction(postClass);
        setSavedEndTime(3);
    }







}
