package benedykt.ziobro.cv.viewmodel.mapper

import benedykt.ziobro.cv.viewmodel.model.Cv
import benedykt.ziobro.cv.viewmodel.model.Education
import benedykt.ziobro.cv.viewmodel.model.Experience
import benedykt.ziobro.cv.viewmodel.model.Language
import benedykt.ziobro.cv.viewmodel.model.Project
import benedykt.ziobro.cv.repository.model.Project as RepositoryProject
import benedykt.ziobro.cv.repository.model.Language as RepositoryLanguage
import benedykt.ziobro.cv.repository.model.Experience as RepositoryExperience
import benedykt.ziobro.cv.repository.model.Education as RepositoryEducation
import benedykt.ziobro.cv.repository.model.Cv as RepositoryCv

fun RepositoryProject.toViewModelProject() = Project(
    description = description,
    name = name,
    technologies = technologies
)

fun RepositoryLanguage.toViewModelLanguage() = Language(
    level = level,
    name = name
)

fun RepositoryExperience.toViewModelExperience() = Experience(
    fromDate = fromDate,
    name = name,
    projects = projects.map { it.toViewModelProject() },
    toDate = toDate
)

fun RepositoryEducation.toViewModelEducation() = Education(
    degree = degree,
    fromDate = fromDate,
    name = name,
    toDate = toDate
)

fun RepositoryCv.toViewModelCv() =
    Cv(
        age = age,
        birthDate = birthDate,
        education = education.map { it.toViewModelEducation() },
        experience = experience.map { it.toViewModelExperience() },
        firstName = firstName,
        languages = languages.map { it.toViewModelLanguage() },
        phoneNumber = phoneNumber,
        secondName = secondName,
        trainings = trainings
    )