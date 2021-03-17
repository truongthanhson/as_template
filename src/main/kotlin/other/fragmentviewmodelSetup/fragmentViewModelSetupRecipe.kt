package other.fragmentviewmodelSetup

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.github.truongthanhson.astemplate.listeners.MyProjectManagerListener.Companion.projectInstance
import com.intellij.lang.java.JavaLanguage
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.PsiManager
import other.src.app_package.createFragment
import other.src.app_package.createFragmentLayout
import other.src.app_package.createViewModel

fun RecipeExecutor.fragmentViewModelSetup(
		moduleData: ModuleTemplateData,
		packageName: String,
		entityName: String,
		layoutName: String
) {
	val (projectData) = moduleData
	val project = projectInstance ?: return

	addAllKotlinDependencies(moduleData)

	val virtualFiles = ProjectRootManager.getInstance(project).contentSourceRoots
	val virtSrc = virtualFiles.first { it.path.contains("src") }
	val virtRes = virtualFiles.first { it.path.contains("res") }
	val directorySrc = PsiManager.getInstance(project).findDirectory(virtSrc)!!
	val directoryRes = PsiManager.getInstance(project).findDirectory(virtRes)!!

	val fragmentClass = "${entityName}Fragment"
	val viewModelClass = "${entityName}ViewModel"

	createFragment(packageName, entityName, layoutName, projectData)
			.save(directorySrc, packageName, "${fragmentClass}.java")

	createViewModel(packageName, entityName, layoutName, projectData)
			.save(directorySrc, packageName, "${viewModelClass}.java")


	createFragmentLayout(packageName, entityName)
			.save(directoryRes, "layout", "${layoutName}.xml")
}

fun String.save(srcDir: PsiDirectory, subDirPath: String, fileName: String) {
	try {
		val destDir = subDirPath.split(".").toDir(srcDir)
		val psiFile = PsiFileFactory
				.getInstance(srcDir.project)
				.createFileFromText(fileName, JavaLanguage.INSTANCE, this)
		destDir.add(psiFile)
	}catch (exc: Exception) {
		exc.printStackTrace()
	}
}

fun List<String>.toDir(srcDir: PsiDirectory): PsiDirectory {
	var result = srcDir
	forEach {
		result = result.findSubdirectory(it) ?: result.createSubdirectory(it)
	}
	return result
}
