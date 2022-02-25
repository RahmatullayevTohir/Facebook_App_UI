package com.example.facebook_app_ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.*
import com.airbnb.lottie.LottieAnimationView
import com.example.facebook_app_ui.helper.Utils
import com.example.facebook_app_ui.model.Link
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.jsoup.nodes.Document

class LinkPostActivity : AppCompatActivity() {
    lateinit var editText: EditText

    lateinit var ll_contener:LinearLayout
    lateinit var iv_image:ImageView
    lateinit var tv_site:TextView
    lateinit var tv_url_name:TextView
    var link = Link()

    lateinit var im_clear: ImageView
    lateinit var b_post : Button
    private var isFindLink = false
//    lateinit var laLoading: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_link_post)
        initViews()
    }


    private fun initViews() {
        editText = findViewById(R.id.et_text)
        im_clear = findViewById(R.id.im_clear)
        b_post = findViewById(R.id.b_post)

        ll_contener = findViewById(R.id.ll_contener)
        iv_image = findViewById(R.id.im_url_image)
        tv_site = findViewById(R.id.tv_site)
        tv_url_name = findViewById(R.id.tv_url_name)


        im_clear.setOnClickListener {
            finish()
        }


        b_post.setOnClickListener {
            val intent = Intent()
            intent.putExtra("object",link)
            setResult(Activity.RESULT_OK,intent)
            Log.d("RRR", "Create -> ${link.toString()}")
            finish()
        }

        editText.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (containsLink(p0.toString())) ll_contener.visibility = View.VISIBLE
                else ll_contener.visibility = View.GONE

                if (p0.toString() == "") b_post.setBackgroundResource(R.drawable.button_border_radius)
                else b_post.setBackgroundResource(R.drawable.button_border_radius)
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun getElementsJsoup(url: String) {
        Utils.getJsoupData(url)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result: Document ->
                val metaTags = result.getElementsByTag("meta")
                for (element in metaTags) {
                    when {
                        element.attr("property").equals("og:image") -> {
                            Picasso.get().load(element.attr("content")).into(iv_image)
                        }
                        element.attr("property").equals("og:description") -> {
                            tv_url_name.text = element.attr("content")
                        }
                        element.attr("property").equals("og:title") -> {
                            tv_site.text = element.attr("content")
                        }
                    }
                }
//                laLoading.visibility = View.GONE
            }
    }

    private fun containsLink(input: String): Boolean {
        val parts = input.split(" ")
        for (item in parts) {
            if (Patterns.WEB_URL.matcher(item).matches()) {
                getElementsJsoup(item)
                isFindLink = true
                return true
            }
        }
        return false
    }




}