package com.xsenlin.android.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.xsenlin.android.R
import com.xsenlin.android.widget.BasisScaleTransformation
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

/**
 * Created by Dylan on 2017/10/14.
 */
class DetailsFragment : BaseFragment() {

    companion object {
        val TAG = "DetailsFragment"
        fun newInstance(): DetailsFragment {
            return DetailsFragment()
        }
    }

    private val mHandler by lazy { Handler() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupContentLoading(view)

//        Observable.create<ImageWrap> { e ->
//            e.onNext(ImageWrap("file:///android_asset/demo/details_0_0.webp", view.findViewById<ImageView>(R.id.image1)))
//            e.onNext(ImageWrap("file:///android_asset/demo/details_0_1.webp", view.findViewById<ImageView>(R.id.image2)))
//            e.onNext(ImageWrap("file:///android_asset/demo/details_0_2.webp", view.findViewById<ImageView>(R.id.image3)))
//            e.onComplete()
//        }.subscribe { value ->
//            Picasso.with(context).load(value.url)
//                    .transform(BasisScaleTransformation(value.url, value.image.width))
//                    .into(value.image, object : Callback.EmptyCallback() {
//                        override fun onSuccess() {
//                            hideContentLoading()
//                        }
//                    })
//        }
        val image1 = view.findViewById<ImageView>(R.id.image1)
        val image2 = view.findViewById<ImageView>(R.id.image2)
        val image3 = view.findViewById<ImageView>(R.id.image3)

        mHandler.post {
            Picasso.with(context).load("file:///android_asset/demo/details_0_0.webp")
                    .transform(BasisScaleTransformation("file:///android_asset/demo/details_0_0.webp", image1.width))
                    .into(image1)
            Picasso.with(context).load("file:///android_asset/demo/details_0_1.webp")
                    .transform(BasisScaleTransformation("file:///android_asset/demo/details_0_1.webp", image2.width))
                    .into(image2)
            Picasso.with(context).load("file:///android_asset/demo/details_0_2.webp")
                    .transform(BasisScaleTransformation("file:///android_asset/demo/details_0_2.webp", image3.width))
                    .into(image3, object : Callback.EmptyCallback() {
                        override fun onSuccess() {
                            hideContentLoading()
                        }
                    })
        }
    }

    override fun onResume() {
        super.onResume()
    }

//    data class ImageWrap(val url: String, val image: ImageView)
}