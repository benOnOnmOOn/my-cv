package benedykt.ziobro.cv.adapter

import androidx.recyclerview.widget.DiffUtil

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CvItemModel>() {
    override fun areItemsTheSame(oldItem: CvItemModel, newItem: CvItemModel): Boolean =
        oldItem.isSame(newItem)

    override fun areContentsTheSame(oldItem: CvItemModel, newItem: CvItemModel): Boolean =
        oldItem == newItem
}

class CvAdapter : DataBindingAdapter<CvItemModel>(DIFF_CALLBACK)

class CvPageAdapter : DataBindingPagingAdapter<CvItemModel>(DIFF_CALLBACK)