package com.example.facebook_app_ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.facebook_app_ui.adapter.FeedAdapter
import com.example.facebook_app_ui.model.*

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var result :Link
    lateinit var adapter : FeedAdapter

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

    fun openLinkPostActivity(){
        val intent = Intent(this, LinkPostActivity::class.java)
        startActivity(intent)
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
        feeds.add(Feed(Pictures(R.drawable.im_profile9,R.drawable.im_photo2,R.drawable.im_photo3,R.drawable.im_photo4,R.drawable.im_photo5)))
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

    fun openCreateActivity() {
        val intent = Intent(this, LinkPostActivity::class.java)
        startActivityForResult(intent, -3)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("RRR", "Main --> $requestCode -- $resultCode")
        if (requestCode == -3) {
            Log.d("RRR", "$requestCode -- $resultCode")
            if (resultCode == RESULT_OK) {
                result = data?.getParcelableExtra<Parcelable>("object") as Link
//                result = intent.getParcelableExtra("object")!!
                Log.d("@@@", result.toString())
                adapter.addItem(result)
            }
        }
    }
}