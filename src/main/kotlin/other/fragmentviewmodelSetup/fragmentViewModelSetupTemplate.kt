package other.fragmentviewmodelSetup

import com.android.tools.idea.wizard.template.*

val fragmentViewModelSetupTemplate
	get() = template {
		revision = 2
		name = "Create Fragment With ViewModel"
		description = "Creates a new fragment along with view model + layout file."
		minApi = 16
		minBuildApi = 16
		category = Category.Other // Check other categories
		formFactor = FormFactor.Mobile
		screens = listOf(WizardUiContext.FragmentGallery, WizardUiContext.MenuEntry,
				WizardUiContext.NewProject, WizardUiContext.NewModule)

		val packageNameParam = defaultPackageNameParameter
		val entityName = stringParameter {
			name = "Fragment name"
			default = "DefaultFragment"
			help = "Name of fragment/screen"
			constraints = listOf(Constraint.NONEMPTY)
		}

		val layoutName = stringParameter {
			name = "Layout name"
			default = "fragment_default"
			help = "Name of xml layout file created for fragment"
			constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
			suggest = { fragmentToLayout(entityName.value.toLowerCase()) }
		}

		widgets(
				TextFieldWidget(entityName),
				TextFieldWidget(layoutName),
				PackageNameWidget(packageNameParam)
		)

		recipe = { data: TemplateData ->
			fragmentViewModelSetup(
					data as ModuleTemplateData,
					packageNameParam.value,
					entityName.value,
					layoutName.value
			)
		}
	}
val defaultPackageNameParameter get() = stringParameter {
	name = "DefaultFragment"
	visible = { !isNewModule }
	default = "com.ad.sakani"
	constraints = listOf(Constraint.PACKAGE)
	suggest = { packageName }
}
