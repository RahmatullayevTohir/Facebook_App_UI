package com.example.facebook_app_ui.model

class Feed {
    var isHeader:Boolean = false
    var post: Post? = null
    var stories:ArrayList<Story> = ArrayList<Story>()

    constructor(){
        this.isHeader = true
    }
    constructor(stories:ArrayList<Story>){
        this.stories = stories
        this.isHeader = false
    }
    constructor(post: Post){
        this.post = post
        this.isHeader = false
    }

}