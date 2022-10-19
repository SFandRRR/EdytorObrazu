package com.example.edytorobrazu

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ButtonCamera: Button =findViewById(R.id.ButtonCamera)
        val Image_Mainone: ImageView =findViewById(R.id.MainImage)

        val SliderX: SeekBar =findViewById(R.id.SeekBar_ObrotX)
        val SliderY: SeekBar =findViewById(R.id.SeekBar_ObrotY)
        val SliderZ: SeekBar =findViewById(R.id.SeekBar_ObrotZ)
        val SliderAlpha: SeekBar =findViewById(R.id.SeekBar_Alpha)

        val bitmapDog = BitmapFactory.decodeResource(resources,R.drawable.pies)
        val bitmapRed = BitmapFactory.decodeResource(resources,R.drawable.czerwony)
        val bitmapGrn = BitmapFactory.decodeResource(resources,R.drawable.zielony)
        val bitmapBlu = BitmapFactory.decodeResource(resources,R.drawable.niebieski)

        val bitmapFilterR = Bitmap.createBitmap(bitmapDog.width, bitmapDog.height, Bitmap.Config.ARGB_8888)
        val bitmapFilterG = Bitmap.createBitmap(bitmapDog.width, bitmapDog.height, Bitmap.Config.ARGB_8888)
        val bitmapFilterB = Bitmap.createBitmap(bitmapDog.width, bitmapDog.height, Bitmap.Config.ARGB_8888)

        SliderX.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                Image_Mainone.setScaleType(ImageView.ScaleType.CENTER_INSIDE)
                Image_Mainone.setRotation(progress.toFloat())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }
        })

        SliderY.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                Image_Mainone.setScaleType(ImageView.ScaleType.CENTER_INSIDE)
                Image_Mainone.setRotationY(progress.toFloat())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }
        })

        SliderZ.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                Image_Mainone.setScaleType(ImageView.ScaleType.CENTER_INSIDE)
                Image_Mainone.setRotationX(progress.toFloat())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }
        })

        SliderAlpha.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                Image_Mainone.setAlpha(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }
        })

        ButtonCamera.isEnabled=false
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA )!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),111)
        }else{
            ButtonCamera.isEnabled=true
        }
        ButtonCamera.setOnClickListener(){
            var x = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(x,101)
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode ==111 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
        {
            findViewById<ImageButton>(R.id.ButtonCamera).isEnabled=true
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode,resultCode,data)
        if(requestCode==101)
        {
            var ObrazKamery  = data?.getParcelableExtra<Bitmap>("data")
            findViewById<ImageView>(R.id.MainImage).setImageBitmap(ObrazKamery)
        }
    }
}