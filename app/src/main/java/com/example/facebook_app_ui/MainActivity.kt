package com.example.facebook_app_ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.facebook_app_ui.adapter.FeedAdapter
import com.example.facebook_app_ui.model.Feed
import com.example.facebook_app_ui.model.Post
import com.example.facebook_app_ui.model.Story

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 1)

        refreshAdapter(getAllFeeds())
    }

    fun refreshAdapter(feeds:ArrayList<Feed>){
        val adapter = FeedAdapter(this,feeds)
        recyclerView!!.adapter = adapter
    }

    fun getAllFeeds():ArrayList<Feed>{
        val stories:ArrayList<Story> = ArrayList<Story>()
        stories.add(Story(R.drawable.im_profile2,"Tohir"))
        stories.add(Story(R.drawable.im_photo1,"James Webb"))
        stories.add(Story(R.drawable.im_profile6,"Akmal"))
        stories.add(Story(R.drawable.im_profile7,"Abbos"))
        stories.add(Story(R.drawable.im_profile4,"Nodir"))
        stories.add(Story(R.drawable.im_profile2,"Tohir"))
        stories.add(Story(R.drawable.im_profile5,"Sarvar"))
        val feeds: ArrayList<Feed> = ArrayList<Feed>()
        //Head
        feeds.add(Feed())
        //Story
        feeds.add(Feed(stories))
        //Post
        feeds.add(Feed(Post(R.drawable.im_profile,"Akmal",R.drawable.im_photo1)))
        feeds.add(Feed(Post(R.drawable.im_profile5,"Sarvar",R.drawable.im_photo2)))
        feeds.add(Feed(Post(R.drawable.im_profile3,"Akmal",R.drawable.im_photo3)))
        feeds.add(Feed(Post(R.drawable.im_profile4,"Botir",R.drawable.im_photo4)))
        feeds.add(Feed(Post(R.drawable.im_profile,"Shaxzod",R.drawable.im_photo5)))
        feeds.add(Feed(Post(R.drawable.im_profile2,"Nodir",R.drawable.im_photo6)))
        feeds.add(Feed(Post(R.drawable.im_profile6,"Akmal",R.drawable.im_photo7)))
        feeds.add(Feed(Post(R.drawable.im_profile7,"Abbos",R.drawable.im_photo8)))
        feeds.add(Feed(Post(R.drawable.im_profile8,"Akmal",R.drawable.im_photo5)))
        feeds.add(Feed(Post(R.drawable.im_profile9,"Akmal",R.drawable.im_profile9)))
        feeds.add(Feed(Post(R.drawable.im_profile9,"Akmal",R.drawable.im_photo2)))
        feeds.add(Feed(Post(R.drawable.im_profile9,"Akmal",R.drawable.im_photo8)))

        return feeds
    }
}