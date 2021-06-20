package benedykt.ziobro.cv.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import benedykt.ziobro.cv.R
import benedykt.ziobro.cv.extension.inflate

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CvItemModel>() {
    override fun areItemsTheSame(oldItem: CvItemModel, newItem: CvItemModel): Boolean =
        oldItem.isSame(newItem)

    override fun areContentsTheSame(oldItem: CvItemModel, newItem: CvItemModel): Boolean =
        oldItem == newItem
}

class CvAdapter : DataBindingAdapter<CvItemModel>(DIFF_CALLBACK)