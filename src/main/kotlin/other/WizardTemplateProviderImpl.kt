package other

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import other.fragmentviewmodelSetup.fragmentViewModelSetupTemplate
import other.usecase_setup.createNewUseCaseTemplate

class WizardTemplateProviderImpl : WizardTemplateProvider() {
	override fun getTemplates(): List<Template> = listOf(fragmentViewModelSetupTemplate, createNewUseCaseTemplate)

}
