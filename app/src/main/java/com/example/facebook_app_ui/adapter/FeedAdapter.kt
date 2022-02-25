package com.example.facebook_app_ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.facebook_app_ui.MainActivity
import com.example.facebook_app_ui.R
import com.example.facebook_app_ui.model.Feed
import com.example.facebook_app_ui.model.Link
import com.example.facebook_app_ui.model.Story
import com.google.android.material.imageview.ShapeableImageView

class FeedAdapter(var context: MainActivity, var items:ArrayList<Feed>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var linearLayout: LinearLayout



    private val TYPE_ITEM_HEAD = 0
    private val TYPE_ITEM_STORY = 1
    private val TYPE_ITEM_POST = 2
    private val TYPE_ITEM_POST_PROFILE = 3
    private val TYPE_ITEM_POST_PICTURE = 4
    private val TYPE_ITEM_POST_LINK = 5

    override fun getItemViewType(position: Int): Int {
        var feed = items[position]

        if (feed.isHeader)
            return TYPE_ITEM_HEAD
        else if (feed.stories.size>0)
            return TYPE_ITEM_STORY
        else if (position == 5)
            return TYPE_ITEM_POST_PROFILE
        else if (feed.post!=null){
            return TYPE_ITEM_POST
        }
        else if (feed.link!=null){
            TYPE_ITEM_POST_LINK
        }
        return TYPE_ITEM_POST_PICTURE
    }
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_ITEM_HEAD){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed_head,parent,false)
            return HeadViewHolder(context, view)
        }else if (viewType == TYPE_ITEM_STORY){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed_story,parent,false)
            return StoryViewHolder(context,view)
        }
        else if (viewType ==TYPE_ITEM_POST_PROFILE){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed_post_profile,parent,false)
            return PostProfileViewHolder(view)
        }
        else if (viewType == TYPE_ITEM_POST_PICTURE){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed_five_picture,parent,false)
            return PicturesViewHolder(view)
        }
        else if (viewType ==TYPE_ITEM_POST_LINK){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_link_post,parent, false)
            return LinkPostViewHolder(view)
        }
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed_post,parent,false)
        return PostViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val feed = items[position]

        if (holder is HeadViewHolder){

        }
        if (holder is StoryViewHolder){
            holder.apply {
                if (adapter == null){
                    adapter = StoryAdapter(context,feed.stories)
                    recyclerView.adapter = adapter
                }
            }
//            var recyclerView = holder.recyclerView
//            reshreshAdapter(feed.stories, recyclerView)
        }

        if (holder is PicturesViewHolder){
            var picture1 = holder.picture1
            var picture2 = holder.picture2
            var picture3 = holder.picture3
            var picture4 = holder.picture4
            var picture5 = holder.picture5

            picture1.setImageResource(feed.picture!!.pictures1)
            picture2.setImageResource(feed.picture!!.pictures2)
            picture3.setImageResource(feed.picture!!.pictures3)
            picture4.setImageResource(feed.picture!!.pictures4)
            picture5.setImageResource(feed.picture!!.pictures5)
        }

        if (holder is PostViewHolder){
            var iv_profile = holder.iv_profile
            var iv_photo = holder.iv_photo
            var tv_fullname = holder.tv_fullname

            iv_profile.setImageResource(feed.post!!.profile)
            iv_photo.setImageResource(feed.post!!.photo)
            tv_fullname.text = feed.post!!.fullname
        }


    }

    fun addItem(newItem: Link) {
        items.add(Feed(newItem))
        Log.d("RRR", "----updated---- ${newItem.title}")
        notifyDataSetChanged()
    }

    fun reshreshAdapter(stories:ArrayList<Story>, recyclerView: RecyclerView){
        val adapter = StoryAdapter(context, stories)
        recyclerView.adapter = adapter
    }

    class HeadViewHolder(context: MainActivity,view: View):RecyclerView.ViewHolder(view){
       var textView : TextView

       init {
           textView= view.findViewById(R.id.et_enter)
           textView.setOnClickListener {
               context.openLinkPostActivity()
           }
       }
    }

    class StoryViewHolder(context: Context, view: View):RecyclerView.ViewHolder(view){
        var recyclerView:RecyclerView

        var adapter : StoryAdapter? = null

        init {
            recyclerView = view.findViewById(R.id.recyclerView)
            val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.layoutManager = manager

        }
    }
    class PostViewHolder(view: View):RecyclerView.ViewHolder(view){
        var iv_profile:ShapeableImageView
        var iv_photo:ImageView
        var tv_fullname:TextView

        init {
            iv_photo = view.findViewById(R.id.iv_photo)
            iv_profile = view.findViewById(R.id.iv_profile)
            tv_fullname = view.findViewById(R.id.tv_fullname)
        }
    }

    class PostProfileViewHolder(view: View):RecyclerView.ViewHolder(view){

    }

    class PicturesViewHolder(view: View):RecyclerView.ViewHolder(view){
        var picture1 :ImageView
        var picture2 :ImageView
        var picture3 :ImageView
        var picture4 :ImageView
        var picture5 :ImageView

        init {
            picture1 = view.findViewById(R.id.iv_picture1)
            picture2 = view.findViewById(R.id.iv_picture2)
            picture3 = view.findViewById(R.id.iv_picture3)
            picture4 = view.findViewById(R.id.iv_picture4)
            picture5 = view.findViewById(R.id.iv_picture5)
        }
    }

    class LinkPostViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val etPost: EditText = view.findViewById(R.id.et_text)
        val llContener: LinearLayout = view.findViewById(R.id.ll_contener)
        val ivPost: ImageView = view.findViewById(R.id.im_url_image)
        val tvAddress: TextView = view.findViewById(R.id.tv_url_name)
        val tvTitle: TextView = view.findViewById(R.id.tv_site)
    }


}