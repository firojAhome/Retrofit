package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.UFormat;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private  JsonPlaceHolderApi jsonPlaceHolderApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

//        getPost();
        getComments();
    }

    private void getComments() {
        Call<List<Comments>> call = jsonPlaceHolderApi.getComments(3);

        call.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("code:"+ response.code());
                    return;
                }
                List<Comments> comments = response.body();
                for (Comments comment : comments){
                    String content = "";
                    content +=  "Id: "+  comment.getId() + "\n";
                    content +=  "Post: "+  comment.getPostId() + "\n";
                    content +=  "Name : "+  comment.getName()+ "\n";
                    content +=  "Email : "+  comment.getEmail() + "\n";
                    content +=  "Text : "+  comment.getText() + "\n";

                    textViewResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }

        });
    }

    private void getPost() {

        Call<List<Post>> call = jsonPlaceHolderApi.getPost(2);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("code:"+ response.code());
                    return;
                }
                List<Post> posts = response.body();

                for (Post post: posts){
                    String content = "";
                    content +=  "id: "+  post.getId() + "\n";
                    content +=  "userId: "+  post.getUserId() + "\n";
                    content +=  "title : "+  post.getTitle() + "\n";
                    content +=  "body : "+  post.getTitle() + "\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

}