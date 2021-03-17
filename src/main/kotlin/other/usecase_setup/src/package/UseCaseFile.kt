package other.usecase_setup.src.`package`

import com.android.tools.idea.wizard.template.ProjectTemplateData

fun createUseCaseAPI(
		packageName:String,
		useCase: String,
		projectData: ProjectTemplateData
) = """
package ${packageName};

public interface ${useCase}UseCase {
}
""".trimIndent()


fun createUseCaseImpl(
		packageName:String,
		useCase: String,
		projectData: ProjectTemplateData
) = """
package ${packageName};

import javax.inject.Inject;

public class ${useCase}UseCaseImpl implements ${useCase}UseCase{

	@Inject
    public ${useCase}UseCaseImpl() {}
}
""".trimIndent()
