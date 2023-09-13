package com.viona.categoriesfilm.util

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestOptions
import com.viona.categoriesfilm.R
import com.viona.categoriesfilm.core.domain.model.Genre
import com.viona.categoriesfilm.util.Constants.getRuntimeDateFormat
import java.text.SimpleDateFormat
import java.util.Locale

fun <T> LiveData<T>.observableData(
    owner: LifecycleOwner,
    action: (T) -> Unit,
) {
    this.observe(owner) { data ->
        action.invoke(data)
    }
}

fun ImageView.loadWithGlide(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_loading_image)
        .error(R.drawable.ic_broken_image)
        .apply(RequestOptions.downsampleOf(DownsampleStrategy.AT_MOST))
        .into(this)
}

fun TextView.movieEpisode(runtimeInMinutes: Int?) {
    runtimeInMinutes?.let {
        val hoursText: String = appendZeroBeforeNumber((it / 60f).toInt())
        val minutesText: String = appendZeroBeforeNumber((it % 60f).toInt())
        val text = "$hoursText:$minutesText / $runtimeInMinutes min"
        this.text = text
    }
}

fun appendZeroBeforeNumber(num: Int): String {
    return if (num < 10) "0$num" else num.toString()
}

fun TextView.movieLanguage(languageCode: String?) {
    languageCode?.let { this.text = Locale(languageCode).getDisplayLanguage(Locale("en")) }
}

@SuppressLint("SimpleDateFormat")
fun TextView.movieEpisode(dateString: String?) {
    if (dateString.isNullOrBlank()) return
    val date = SimpleDateFormat(getRuntimeDateFormat()).parse(dateString)
    val pat =
        SimpleDateFormat().toLocalizedPattern().replace("\\W?[HhKkmsSzZXa]+\\W?".toRegex(), "")
    val localFormatter = SimpleDateFormat(pat, Locale.getDefault())
    this.text = localFormatter.format(date)
}

fun TextView.genresText(genres: List<Genre>?) {
    if (genres == null) return

    val maxNumOfGenres = 3
    var text = ""
    val appendText = " / "

    val loopCount = if (genres.size <= maxNumOfGenres) genres.size else maxNumOfGenres
    for (i in 0 until loopCount) {
        text = text + genres[i].name + appendText
    }
    this.text = text.dropLast(appendText.length)
}