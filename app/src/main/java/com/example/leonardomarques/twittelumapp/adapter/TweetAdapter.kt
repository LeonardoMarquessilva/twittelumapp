package com.example.leonardomarques.twittelumapp.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.leonardomarques.twittelumapp.R
import com.example.leonardomarques.twittelumapp.modelo.Tweet
import kotlinx.android.synthetic.main.item_tweet.view.*

class TweetAdapter(private val tweets: List<Tweet>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val tweet = tweets[position]
        val inflater = LayoutInflater.from(parent?.context)
        val view = inflater.inflate(R.layout.item_tweet,parent,false)
        view.item_conteudo.text = tweet.mensagem

        tweet.foto?.let {
            view.item_foto.visibility = View.VISIBLE
            view.item_foto.setImageBitmap(Carregador.decodifica(it))
        }

        return view
    }

    override fun getItem(position: Int): Any {
    return tweets[position]

    }

    override fun getItemId(position: Int): Long {
        return tweets[position].id.toLong()

    }

    override fun getCount(): Int {
        return tweets.size
    }

    object Carregador {

        fun decodifica(foto: String): Bitmap{

            val decode: ByteArray = Base64.decode(foto,Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(decode, 0,decode.size)
            val matrix = Matrix()
            matrix.postRotate(90.0F)

            return Bitmap.createBitmap(bitmap,0,0,bitmap.width,bitmap.height,matrix,true)
        }
    }

}