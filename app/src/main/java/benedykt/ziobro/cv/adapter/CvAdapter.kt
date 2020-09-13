package benedykt.ziobro.cv.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import benedykt.ziobro.cv.R
import benedykt.ziobro.cv.extension.inflate

class CvAdapter : RecyclerView.Adapter<CvItemViewHolder<*>>() {

    private val items: MutableList<CvItemModel> = mutableListOf()

    fun setItems(items: List<CvItemModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CvItemViewHolder<*> {
        return when (viewType) {
            HEADER_VIEW_TYPE ->
                CvItemViewHolder.HeaderHolder(parent.inflate(R.layout.item_header))
            PERSON_VIEW_TYPE ->
                CvItemViewHolder.PersonHolder(parent.inflate(R.layout.item_person))
            EDUCATION_VIEW_TYPE ->
                CvItemViewHolder.EducationHolder(parent.inflate(R.layout.item_education))
            EXPERIENCE_VIEW_TYPE ->
                CvItemViewHolder.ExperienceHolder(parent.inflate(R.layout.item_experince))
            LANGUAGE_VIEW_TYPE ->
                CvItemViewHolder.LanguageHolder(parent.inflate(R.layout.item_langugae))
            else -> throw IllegalStateException("Invalid viewType.")
        }
    }

    override fun onBindViewHolder(holder: CvItemViewHolder<*>, position: Int) {
        when (holder) {
            is CvItemViewHolder.HeaderHolder -> holder.bind(items[position] as CvItemModel.HeaderItem)
            is CvItemViewHolder.PersonHolder -> holder.bind(items[position] as CvItemModel.PersonItem)
            is CvItemViewHolder.EducationHolder -> holder.bind(items[position] as CvItemModel.EducationItem)
            is CvItemViewHolder.ExperienceHolder -> holder.bind(items[position] as CvItemModel.ExperienceItem)
            is CvItemViewHolder.LanguageHolder -> holder.bind(items[position] as CvItemModel.LanguageItem)
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = items[position].viewType

}