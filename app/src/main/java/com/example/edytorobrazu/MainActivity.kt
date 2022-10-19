package com.example.edytorobrazu

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
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

        val ToggleR: Switch =findViewById(R.id.Switch_Red)
        val ToggleG: Switch =findViewById(R.id.Switch_Green)
        val ToggleB: Switch =findViewById(R.id.Switch_Blue)

        fun ColorImage(){

        }
        var bitmapImage = BitmapFactory.decodeResource(resources,R.drawable.default_img)

        val bitmapRed = BitmapFactory.decodeResource(resources,R.drawable.red)
        val bitmapGrn = BitmapFactory.decodeResource(resources,R.drawable.green)
        val bitmapBlu = BitmapFactory.decodeResource(resources,R.drawable.blue)

        var bitmapFilterR = Bitmap.createBitmap(Image_Mainone.width, Image_Mainone.height, Bitmap.Config.ARGB_8888)
        var bitmapFilterG = Bitmap.createBitmap(Image_Mainone.width, Image_Mainone.height, Bitmap.Config.ARGB_8888)
        var bitmapFilterB = Bitmap.createBitmap(Image_Mainone.width, Image_Mainone.height, Bitmap.Config.ARGB_8888)

        var paint = Paint()
        paint.alpha=100
        var canvasRed = Canvas(bitmapFilterR)
        var canvasGreen = Canvas(bitmapFilterG)
        var canvasBlue = Canvas(bitmapFilterB)
        canvasRed.drawARGB(0,0,0,0)
        canvasBlue.drawARGB(0,0,0,0)
        canvasGreen.drawARGB(0,0,0,0)

        canvasRed.drawBitmap(bitmapRed,null,
            RectF(0f,0f,bitmapImage.width.toFloat(),bitmapImage.height.toFloat()),paint)
        canvasRed.drawBitmap(bitmapImage,0f,0f,paint)

        canvasGreen.drawBitmap(bitmapGrn,null,
            RectF(0f,0f,bitmapImage.width.toFloat(),bitmapImage.height.toFloat()),paint)
        canvasGreen.drawBitmap(bitmapImage,0f,0f,paint)

        canvasBlue.drawBitmap(bitmapBlu,null,
            RectF(0f,0f,bitmapImage.width.toFloat(),bitmapImage.height.toFloat()),paint)
        canvasBlue.drawBitmap(bitmapImage,0f,0f,paint)

        ToggleR.setOnCheckedChangeListener(){ _, isChecked ->
            if (isChecked) {
                Image_Mainone.setImageBitmap(bitmapFilterR)
            } else {
                Image_Mainone.setImageBitmap(bitmapImage)
            }
        }
        ToggleG.setOnCheckedChangeListener(){ _, isChecked ->
            if (isChecked) {
                Image_Mainone.setImageBitmap(bitmapFilterG)
            } else {
                Image_Mainone.setImageBitmap(bitmapImage)
            }
        }
        ToggleB.setOnCheckedChangeListener(){ _, isChecked ->
            if (isChecked) {
                Image_Mainone.setImageBitmap(bitmapFilterB)
            } else {
                Image_Mainone.setImageBitmap(bitmapImage)
            }
        }


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
            var bitmapImage = ObrazKamery
        }
    }
}