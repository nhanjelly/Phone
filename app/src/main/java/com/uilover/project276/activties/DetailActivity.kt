package com.uilover.project276.activties

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import com.bumptech.glide.Glide
import com.uilover.project276.Helper.ManagmentCart
import com.uilover.project276.R
import com.uilover.project276.databinding.ActivityDetailBinding
import com.uilover.project276.domain.ItemsModel

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        bundle()
        initSizeList()
    }

    private fun initSizeList() {
        binding.apply {
            smallBtn.setOnClickListener {
                smallBtn.setBackgroundResource(R.drawable.brown_full_corner)
                smallBtn.setTextColor(getResources().getColor(R.color.white))
                mediumBtn.setTextColor(getResources().getColor(R.color.black))
                largeBtn.setTextColor(getResources().getColor(R.color.black))
                mediumBtn.setBackgroundResource(0)
                largeBtn.setBackgroundResource(0)
            }
            mediumBtn.setOnClickListener {
                smallBtn.setBackgroundResource(0)
                smallBtn.setTextColor(getResources().getColor(R.color.black))
                mediumBtn.setTextColor(getResources().getColor(R.color.white))
                largeBtn.setTextColor(getResources().getColor(R.color.black))
                mediumBtn.setBackgroundResource(R.drawable.brown_full_corner)
                largeBtn.setBackgroundResource(0)
            }
            largeBtn.setOnClickListener {
                smallBtn.setBackgroundResource(0)
                smallBtn.setTextColor(getResources().getColor(R.color.black))
                mediumBtn.setTextColor(getResources().getColor(R.color.black))
                largeBtn.setTextColor(getResources().getColor(R.color.white))
                mediumBtn.setBackgroundResource(0)
                largeBtn.setBackgroundResource(R.drawable.brown_full_corner)
            }
        }
    }

    private fun bundle() {
        binding.apply {
            item = intent.getSerializableExtra("object") as ItemsModel

            Glide.with(this@DetailActivity)
                .load(item.picUrl[0])
                .into(binding.picMain)

            titleTxt.text = item.title
            descriptionTxt.text = item.description
            priceTxt.text = "$" + item.price
            ratingTxt.text = item.rating.toString()

            item.numberInCart = 1

            addToCartBtn.setOnClickListener {
                item.numberInCart = Integer.valueOf(
                    numberInCart.text.toString()
                )
                managmentCart.insertItems(item)
            }
            backBtn.setOnClickListener { finish() }

            plusBtn.setOnClickListener {
                numberInCart.text = (item.numberInCart + 1).toString()
                item.numberInCart++
            }
            minusBtn.setOnClickListener {
                if (item.numberInCart > 0) {
                    numberInCart.text = (item.numberInCart - 1).toString()
                    item.numberInCart--
                }
            }
        }
    }
}