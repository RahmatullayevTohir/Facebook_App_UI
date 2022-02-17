package com.example.facebook_app_ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.facebook_app_ui.R
import com.example.facebook_app_ui.model.Story
import com.google.android.material.imageview.ShapeableImageView

class StoryAdapter(context: Context, var items:ArrayList<Story>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_HEAD = 0
    private val TYPE_STORY = 1

    override fun getItemViewType(position: Int): Int {
        if (position ==0)
            return TYPE_HEAD
        return TYPE_STORY
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_HEAD){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_create_story,parent,false)
            return CreateStoryViewHolder(view)
        }
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_story_view, parent, false)
        return StoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val story = items[position]

        if (holder is StoryViewHolder){
            var iv_background = holder.iv_background
            var iv_profile = holder.iv_profile
            var tv_fullname = holder.tv_fullname

            iv_background.setImageResource(story.profile)
            iv_profile.setImageResource(story.profile)
            tv_fullname.text = story.fullname
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class StoryViewHolder(view: View):RecyclerView.ViewHolder(view){
        var iv_background:ShapeableImageView
        var iv_profile:ShapeableImageView
        var tv_fullname:TextView

        init {
            iv_background = view.findViewById(R.id.iv_background)
            iv_profile = view.findViewById(R.id.iv_profile)
            tv_fullname = view.findViewById(R.id.tv_fullname)
        }
    }

    class CreateStoryViewHolder(view: View):RecyclerView.ViewHolder(view){

    }
}