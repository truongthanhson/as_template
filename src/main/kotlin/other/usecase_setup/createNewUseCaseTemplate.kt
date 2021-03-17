package other.usecase_setup

import com.android.tools.idea.wizard.template.*

val createNewUseCaseTemplate
	get() = template {
		revision = 2
		name = "Create new usecase"
		description = "Creates a new usecase with impl"
		minApi = 16
		minBuildApi = 16
		category = Category.Other // Check other categories
		formFactor = FormFactor.Mobile
		screens = listOf(WizardUiContext.FragmentGallery, WizardUiContext.MenuEntry,
				WizardUiContext.NewProject, WizardUiContext.NewModule)

		val packageNameParam = defaultPackageNameParameter
		val useCaseName = stringParameter {
			name = "UseCase name"
			default = "DefaultUseCase"
			help = "Name of UseCase"
			constraints = listOf(Constraint.NONEMPTY)
		}
		widgets(
				TextFieldWidget(useCaseName),
				PackageNameWidget(packageNameParam)
		)

		recipe = {data: TemplateData ->
			createNewUseCaseSetup(
					data as ModuleTemplateData,
					packageNameParam.value,
					useCaseName.value,
			)

		}
	}
val defaultPackageNameParameter get() = stringParameter {
	name = "Default UseCase"
	visible = { !isNewModule }
	default = "com.ad.sakani"
	constraints = listOf(Constraint.PACKAGE)
	suggest = { packageName }
}
