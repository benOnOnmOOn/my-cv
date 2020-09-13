package benedykt.ziobro.cv.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import benedykt.ziobro.cv.R

sealed class CvItemViewHolder<T : CvItemModel>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    class HeaderHolder(view: View) : CvItemViewHolder<CvItemModel.HeaderItem>(view) {
        private val title: TextView = itemView.findViewById(R.id.header_title)

        override fun bind(item: CvItemModel.HeaderItem) {
            title.text = title.context.getString(item.titleResId)
        }
    }

    class PersonHolder(view: View) : CvItemViewHolder<CvItemModel.PersonItem>(view) {

        private val firstName: TextView = view.findViewById(R.id.first_name)
        private val secondName: TextView = view.findViewById(R.id.second_name)
        private val birthDate: TextView = view.findViewById(R.id.birth_date)
        private val age: TextView = view.findViewById(R.id.age)

        override fun bind(item: CvItemModel.PersonItem) {
            firstName.text = item.firstName
            secondName.text = item.secondName
            birthDate.text = item.birthDate
            age.text = item.age.toString()
        }

    }

    class EducationHolder(view: View) : CvItemViewHolder<CvItemModel.EducationItem>(view) {
        private val name: TextView = view.findViewById(R.id.name)
        private val degree: TextView = view.findViewById(R.id.degree)
        private val fromDate: TextView = view.findViewById(R.id.from_date)
        private val toDate: TextView = view.findViewById(R.id.to_date)

        override fun bind(item: CvItemModel.EducationItem) {
            name.text = item.name
            degree.text = item.degree
            fromDate.text = item.fromDate
            toDate.text = item.toDate
        }

    }

    class ExperienceHolder(view: View) : CvItemViewHolder<CvItemModel.ExperienceItem>(view) {
        private val name: TextView = view.findViewById(R.id.name)
        private val fromDate: TextView = view.findViewById(R.id.from_date)
        private val toDate: TextView = view.findViewById(R.id.to_date)

        override fun bind(item: CvItemModel.ExperienceItem) {
            name.text = item.name
            fromDate.text = item.fromDate
            toDate.text = item.toDate
        }

    }

    class LanguageHolder(view: View) : CvItemViewHolder<CvItemModel.LanguageItem>(view) {

        private val languageName: TextView = itemView.findViewById(R.id.language_name)
        private val languageLevel: TextView = itemView.findViewById(R.id.language_level)

        override fun bind(item: CvItemModel.LanguageItem) {
            languageName.text = item.name
            languageLevel.text = item.level
        }

    }

    abstract fun bind(item: T)
}
