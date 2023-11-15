package com.example.mvulatrustmobileapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView

class GalleryPage : AppCompatActivity() {

    var modalList = ArrayList<Modal>()

    var names = arrayOf(
        "image1",
        "image1",
        "image1",
        "image1",
        "image1",
        "image1",
        "image1",
        "image1",
        "image1",
        "image1",
    )

    var images = intArrayOf(
        R.drawable.image1,
        R.drawable.image1,
        R.drawable.image1,
        R.drawable.image1,
        R.drawable.image1,
        R.drawable.image1,
        R.drawable.image1,
        R.drawable.image1,
        R.drawable.image1,
        R.drawable.image1
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_page)

        for (i in names.indices){
            modalList.add(Modal(names[i],images[i]))
        }
        var customAdapter = CustomAdapter(modalList,this)


        //val uploadbutton = view.findViewById<Button>(R.id.uploadButton)
        val gridview = findViewById<GridView>(R.id.grid_view)
        gridview.adapter = customAdapter;

    }
    class CustomAdapter(
        var itemModal: ArrayList<Modal>,
        var context: Context
    ) : BaseAdapter(){
        var layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        override fun getCount(): Int {
            return itemModal.size
        }

        override fun getItem(p0: Int): Any {
            return itemModal[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
            var view = view;
            if(view == null){
                view = layoutInflater.inflate(R.layout.row_items,viewGroup,false);
            }
            var tvImageName = view?.findViewById<TextView>(R.id.ImageName)
            var imageView = view?.findViewById<ImageView>(R.id.imageView)

            tvImageName?.text = itemModal[position].name;
            imageView?.setImageResource(itemModal[position].image!!)

            return  view!!;
        }

    }
}