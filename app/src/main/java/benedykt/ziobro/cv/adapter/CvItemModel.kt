package benedykt.ziobro.cv.adapter

import androidx.annotation.StringRes
import benedykt.ziobro.cv.R
import benedykt.ziobro.cv.viewmodel.model.Cv

const val HEADER_VIEW_TYPE = 1
const val PERSON_VIEW_TYPE = 2
const val EDUCATION_VIEW_TYPE = 3
const val EXPERIENCE_VIEW_TYPE = 4
const val LANGUAGE_VIEW_TYPE = 5

sealed class CvItemModel(val viewType: Int) {

    class HeaderItem(
        @StringRes val titleResId: Int
    ) : CvItemModel(HEADER_VIEW_TYPE)

    class PersonItem(
        val age: Int,
        val birthDate: String,
        val firstName: String,
        val phoneNumber: String,
        val secondName: String
    ) : CvItemModel(PERSON_VIEW_TYPE)

    class EducationItem(
        val degree: String,
        val fromDate: String,
        val name: String,
        val toDate: String
    ) : CvItemModel(EDUCATION_VIEW_TYPE)

    class ExperienceItem(
        val fromDate: String,
        val name: String,
        val toDate: String
    ) : CvItemModel(EXPERIENCE_VIEW_TYPE)

    class LanguageItem(
        val level: String,
        val name: String
    ) : CvItemModel(LANGUAGE_VIEW_TYPE)
}

fun Cv.toCvItemModelList(): List<CvItemModel> {
    val result = mutableListOf<CvItemModel>()
    result.add(CvItemModel.HeaderItem(R.string.cv_item_header_personal_data))
    result.add(CvItemModel.PersonItem(age, birthDate, firstName, phoneNumber, secondName))

    if (education.isNotEmpty()) {
        result.add(CvItemModel.HeaderItem(R.string.cv_item_header_education))
        val educationItems = education.map { education ->
            CvItemModel.EducationItem(
                degree = education.degree,
                fromDate = education.fromDate,
                name = education.name,
                toDate = education.toDate
            )
        }
        result.addAll(educationItems)
    }

    if (experience.isNotEmpty()) {
        result.add(CvItemModel.HeaderItem(R.string.cv_item_header_experience))
        val experienceItems = experience.map { experience ->
            CvItemModel.ExperienceItem(
                fromDate = experience.fromDate,
                name = experience.name,
                toDate = experience.toDate
            )
        }
        result.addAll(experienceItems)
    }

    if (languages.isNotEmpty()) {
        result.add(CvItemModel.HeaderItem(R.string.cv_item_header_language))
        val languageItems = languages.map { language ->
            CvItemModel.LanguageItem(language.level, language.name)
        }
        result.addAll(languageItems)
    }

    return result
}