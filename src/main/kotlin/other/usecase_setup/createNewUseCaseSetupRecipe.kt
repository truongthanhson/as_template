package other.usecase_setup

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.github.truongthanhson.sakani_template.listeners.MyProjectManagerListener
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.psi.PsiManager
import other.fragmentviewmodelSetup.save
import other.usecase_setup.src.`package`.createUseCaseAPI
import other.usecase_setup.src.`package`.createUseCaseImpl

fun RecipeExecutor.createNewUseCaseSetup(
		moduleData: ModuleTemplateData,
		packageName: String,
		usecaseName: String
) {
	val (projectData) = moduleData
	val project = MyProjectManagerListener.projectInstance ?: return

	addAllKotlinDependencies(moduleData)

	val virtualFiles = ProjectRootManager.getInstance(project).contentSourceRoots
	val virtSrc = virtualFiles.first { it.path.contains("src") }
	val virtRes = virtualFiles.first { it.path.contains("res") }
	val directorySrc = PsiManager.getInstance(project).findDirectory(virtSrc)!!
	val directoryRes = PsiManager.getInstance(project).findDirectory(virtRes)!!

	val usecaseAPIClass = "${usecaseName}UseCase"
	val usecaselImpClass = "${usecaseName}UseCaseImpl"

	createUseCaseAPI(packageName, usecaseName, projectData)
			.save(directorySrc, packageName, "${usecaseAPIClass}.java")

	createUseCaseImpl(packageName, usecaseName, projectData)
			.save(directorySrc, packageName, "${usecaselImpClass}.java")
}
