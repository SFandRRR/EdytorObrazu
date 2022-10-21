package com.example.edytorobrazu

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable


class MainActivity : AppCompatActivity() {

    var ImageImage = R.drawable.default_img.toDrawable().toBitmap()
    val Image_Mainone: ImageView =findViewById(R.id.MainImage)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ButtonCamera: Button =findViewById(R.id.ButtonCamera)

        val SliderX: SeekBar =findViewById(R.id.SeekBar_ObrotX)
        val SliderY: SeekBar =findViewById(R.id.SeekBar_ObrotY)
        val SliderZ: SeekBar =findViewById(R.id.SeekBar_ObrotZ)
        val SliderAlpha: SeekBar =findViewById(R.id.SeekBar_Alpha)
        val SliderR: SeekBar =findViewById(R.id.SeekBar_Red)
        val SliderG: SeekBar =findViewById(R.id.SeekBar_Green)
        val SliderB: SeekBar =findViewById(R.id.SeekBar_Blue)

        val ToggleR: Switch =findViewById(R.id.Switch_Red)
        val ToggleG: Switch =findViewById(R.id.Switch_Green)
        val ToggleB: Switch =findViewById(R.id.Switch_Blue)

        val bitmapRed = BitmapFactory.decodeResource(resources,R.drawable.red)
        val bitmapGrn = BitmapFactory.decodeResource(resources,R.drawable.green)
        val bitmapBlu = BitmapFactory.decodeResource(resources,R.drawable.blue)



        fun ColorImage(){

            ImageImage  = Image_Mainone.drawable.toBitmap();
            var editImage = Image_Mainone.drawable.toBitmap();

            var bitmapFilterR = Bitmap.createBitmap(Image_Mainone.width, Image_Mainone.height, Bitmap.Config.ARGB_8888)
            var bitmapFilterG = Bitmap.createBitmap(Image_Mainone.width, Image_Mainone.height, Bitmap.Config.ARGB_8888)
            var bitmapFilterB = Bitmap.createBitmap(Image_Mainone.width, Image_Mainone.height, Bitmap.Config.ARGB_8888)

            var paint = Paint()

            var canvasRed = Canvas(bitmapFilterR)
            var canvasGreen = Canvas(bitmapFilterG)
            var canvasBlue = Canvas(bitmapFilterB)
            canvasRed.drawARGB(0,0,0,0)
            canvasBlue.drawARGB(0,0,0,0)
            canvasGreen.drawARGB(0,0,0,0)

            if (ToggleR.isChecked) {
                paint.alpha=SliderR.progress

                canvasRed.drawBitmap(bitmapRed,null,
                    RectF(0f,0f,editImage.width.toFloat(),editImage.height.toFloat()),paint)
                canvasRed.drawBitmap(editImage,0f,0f,paint)

                editImage=bitmapFilterR
                Image_Mainone.setImageBitmap(editImage)
            }

            if (ToggleG.isChecked) {
                paint.alpha=SliderG.progress

                canvasGreen.drawBitmap(bitmapGrn,null,
                    RectF(0f,0f,editImage.width.toFloat(),editImage.height.toFloat()),paint)
                canvasGreen.drawBitmap(editImage,0f,0f,paint)

                editImage=bitmapFilterG
                Image_Mainone.setImageBitmap(editImage)
            }

            if (ToggleB.isChecked) {

                paint.alpha=SliderB.progress

                canvasBlue.drawBitmap(bitmapBlu,null,
                    RectF(0f,0f,editImage.width.toFloat(),editImage.height.toFloat()),paint)
                canvasBlue.drawBitmap(editImage,0f,0f,paint)

                editImage=bitmapFilterB
                Image_Mainone.setImageBitmap(editImage)
            }

        }


        ToggleR.setOnCheckedChangeListener(){ _, isChecked ->
            if (isChecked) {
                ColorImage()
                SliderR.visibility = View.VISIBLE
            }else {
                Image_Mainone.setImageBitmap(ImageImage)
                SliderR.visibility = View.GONE
            }
        }
        ToggleG.setOnCheckedChangeListener(){ _, isChecked ->
            if (isChecked) {
                ColorImage()
                SliderG.visibility = View.VISIBLE
            }else {
                Image_Mainone.setImageBitmap(ImageImage)
                SliderG.visibility = View.GONE
            }
        }
        ToggleB.setOnCheckedChangeListener(){ _, isChecked ->
            if (isChecked) {
                ColorImage()
                SliderB.visibility = View.VISIBLE
            }else {
                Image_Mainone.setImageBitmap(ImageImage)
                SliderB.visibility = View.GONE
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
        }
    }
}