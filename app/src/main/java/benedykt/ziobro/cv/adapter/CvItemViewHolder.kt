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
        private val phoneNumber: TextView = view.findViewById(R.id.phoneNumber)

        override fun bind(item: CvItemModel.PersonItem) {
            val context = firstName.context
            firstName.text = context.getString(R.string.person_first_name, item.firstName)
            secondName.text = context.getString(R.string.person_second_name, item.secondName)
            birthDate.text = context.getString(R.string.person_birth_date, item.birthDate)
            age.text = context.getString(R.string.person_age, item.age)
            phoneNumber.text = context.getString(R.string.person_phone_number, item.phoneNumber)
        }

    }

    class EducationHolder(view: View) : CvItemViewHolder<CvItemModel.EducationItem>(view) {
        private val name: TextView = view.findViewById(R.id.name)
        private val degree: TextView = view.findViewById(R.id.degree)
        private val fromDate: TextView = view.findViewById(R.id.from_date)
        private val toDate: TextView = view.findViewById(R.id.to_date)

        override fun bind(item: CvItemModel.EducationItem) {
            val context = name.context
            name.text = context.getString(R.string.education_school_name, item.name)
            degree.text = context.getString(R.string.education_degree, item.degree)
            fromDate.text = context.getString(R.string.education_from_date, item.fromDate)
            toDate.text = context.getString(R.string.education_to_date, item.toDate)
        }
    }

    class ExperienceHolder(view: View) : CvItemViewHolder<CvItemModel.ExperienceItem>(view) {
        private val name: TextView = view.findViewById(R.id.name)
        private val fromDate: TextView = view.findViewById(R.id.from_date)
        private val toDate: TextView = view.findViewById(R.id.to_date)

        override fun bind(item: CvItemModel.ExperienceItem) {
            val context = name.context
            name.text = context.getString(R.string.experience_company_name, item.name)
            fromDate.text = context.getString(R.string.experience_from_date, item.fromDate)
            toDate.text = context.getString(R.string.experience_to_date, item.toDate)
        }
    }

    class LanguageHolder(view: View) : CvItemViewHolder<CvItemModel.LanguageItem>(view) {

        private val languageName: TextView = itemView.findViewById(R.id.language_name)
        private val languageLevel: TextView = itemView.findViewById(R.id.language_level)

        override fun bind(item: CvItemModel.LanguageItem) {
            val context = languageName.context
            languageName.text = context.getString(R.string.language_name, item.name)
            languageLevel.text = context.getString(R.string.language_level, item.level)
        }
    }

    abstract fun bind(item: T)
}
