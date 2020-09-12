package benedykt.ziobro.cv.binding

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.BindingAdapter

@BindingAdapter("visible")
fun View.setVisible(show: Boolean?) {
    visibility = if (show == true) VISIBLE else GONE
}

