package com.example.android.testingapp.other

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.android.testingapp.data.response_entity.CompanyResponseItem

@BindingAdapter("companyImage")
fun ImageView.setCompanyImage(item:CompanyResponseItem){
    Glide.with(this).load(Constants.BASE_URL + "/" + item.img).into(this)
}

