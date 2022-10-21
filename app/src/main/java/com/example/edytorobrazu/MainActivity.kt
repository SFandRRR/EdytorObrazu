package com.example.edytorobrazu

import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.app.ActivityCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.drawable.toIcon


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val Image_Mainone: ImageView =findViewById(R.id.MainImage)
        val Image_Backup: ImageView =findViewById(R.id.BackupImage)

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

            var editImage = Image_Backup.drawable.toBitmap();

            var WidthImg = editImage.width.toFloat()
            var HeightImg = editImage.height.toFloat()

            var paint = Paint()
            var paint2 = Paint()
            paint2.alpha=255

            if (ToggleR.isChecked) {
                var bitmapFilterR = Bitmap.createBitmap(Image_Mainone.width, Image_Mainone.height, Bitmap.Config.ARGB_8888)
                var canvasRed = Canvas(bitmapFilterR)
                canvasRed.drawARGB(0,0,0,0)

                paint.alpha=(SliderR.progress-255)*-1

                canvasRed.drawBitmap(bitmapRed,null,
                    RectF(0f,0f,WidthImg,HeightImg),paint2)
                canvasRed.drawBitmap(editImage,0f,0f,paint)

                editImage=bitmapFilterR
                Image_Mainone.setImageBitmap(editImage)
            }

            if (ToggleG.isChecked) {
                var bitmapFilterG = Bitmap.createBitmap(Image_Mainone.width, Image_Mainone.height, Bitmap.Config.ARGB_8888)
                var canvasGreen = Canvas(bitmapFilterG)
                canvasGreen.drawARGB(0,0,0,0)

                paint.alpha=(SliderG.progress-255)*-1

                canvasGreen.drawBitmap(bitmapGrn,null,
                    RectF(0f,0f,WidthImg,HeightImg),paint2)
                canvasGreen.drawBitmap(editImage,0f,0f,paint)

                editImage=bitmapFilterG
                Image_Mainone.setImageBitmap(editImage)
            }

            if (ToggleB.isChecked) {
                var bitmapFilterB = Bitmap.createBitmap(Image_Mainone.width, Image_Mainone.height, Bitmap.Config.ARGB_8888)
                var canvasBlue = Canvas(bitmapFilterB)
                canvasBlue.drawARGB(0,0,0,0)

                paint.alpha=(SliderB.progress-255)*-1

                canvasBlue.drawBitmap(bitmapBlu,null,
                    RectF(0f,0f,WidthImg,HeightImg),paint2)
                canvasBlue.drawBitmap(editImage,0f,0f,paint)

                editImage=bitmapFilterB
                Image_Mainone.setImageBitmap(editImage)
            }

        }


        ToggleR.setOnCheckedChangeListener(){ _, isChecked ->
            if (isChecked) {

                SliderR.visibility = View.VISIBLE
                ColorImage()
            }else {
                SliderR.visibility = View.GONE
                ColorImage()
            }
        }
        ToggleG.setOnCheckedChangeListener(){ _, isChecked ->
            if (isChecked) {

                SliderG.visibility = View.VISIBLE
                ColorImage()
            }else {
                SliderG.visibility = View.GONE
                ColorImage()
            }
        }
        ToggleB.setOnCheckedChangeListener(){ _, isChecked ->
            if (isChecked) {

                SliderB.visibility = View.VISIBLE
                ColorImage()
            }else {
                SliderB.visibility = View.GONE
                ColorImage()
            }
        }


        SliderX.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                Image_Mainone.setScaleType(ImageView.ScaleType.CENTER_INSIDE)
                Image_Mainone.setRotation(progress.toFloat())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })

        SliderY.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                Image_Mainone.setScaleType(ImageView.ScaleType.CENTER_INSIDE)
                Image_Mainone.setRotationY(progress.toFloat())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })

        SliderZ.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                Image_Mainone.setScaleType(ImageView.ScaleType.CENTER_INSIDE)
                Image_Mainone.setRotationX(progress.toFloat())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })

        SliderAlpha.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                Image_Mainone.setAlpha(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })

        SliderR.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                ColorImage()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
        SliderG.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                ColorImage()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
        SliderB.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                ColorImage()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
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
            findViewById<ImageView>(R.id.BackupImage).setImageBitmap(ObrazKamery)
        }
    }
}