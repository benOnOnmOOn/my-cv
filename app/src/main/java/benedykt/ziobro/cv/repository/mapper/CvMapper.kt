package benedykt.ziobro.cv.repository.mapper

import benedykt.ziobro.cv.repository.model.*
import benedykt.ziobro.cv.api.model.Project as ProjectResponse
import benedykt.ziobro.cv.api.model.Language as LanguageResponse
import benedykt.ziobro.cv.api.model.Experience as ExperienceResponse
import benedykt.ziobro.cv.api.model.Education as EducationResponse
import benedykt.ziobro.cv.api.model.Cv as CvResponse

fun ProjectResponse.toRepositoryProject() = Project(
    description = description,
    name = name,
    technologies = technologies
)

fun LanguageResponse.toRepositoryLanguage() = Language(
    level = level,
    name = name
)

fun ExperienceResponse.toRepositoryExperience() = Experience(
    fromDate = fromDate,
    name = name,
    projects = projects.map { it.toRepositoryProject() },
    toDate = toDate
)

fun EducationResponse.toRepositoryEducation() = Education(
    degree = degree,
    fromDate = fromDate,
    name = name,
    toDate = toDate
)

fun CvResponse.toRepositoryCv() =
    Cv(
        age = age,
        birthDate = birthDate,
        education = education.map { it.toRepositoryEducation() },
        experience = experience.map { it.toRepositoryExperience() },
        firstName = firstName,
        languages = languages.map { it.toRepositoryLanguage() },
        phoneNumber = phoneNumber,
        secondName = secondName,
        trainings = trainings
    )