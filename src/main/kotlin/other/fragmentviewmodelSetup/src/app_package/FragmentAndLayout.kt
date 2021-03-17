package other.src.app_package

import com.android.tools.idea.wizard.template.ProjectTemplateData

fun createFragment(
		packageName:String,
		entityName: String,
		layoutName: String,
		projectData: ProjectTemplateData
) = """package 	${packageName};

import com.ad.sakani.base.BaseFragment;
import ${projectData.applicationPackage}.R;

public class ${entityName}Fragment extends BaseFragment {
	@Override
	protected int layoutResource() {
		return R.layout.${layoutName.toLowerCase()};
	}
}

""".trimIndent()

fun createViewModel(
		packageName:String,
		entityName: String,
		layoutName: String,
		projectData: ProjectTemplateData
) = """package ${packageName};

import androidx.hilt.lifecycle.ViewModelInject;

import com.ad.sakani.base.BaseViewModel;

public class ${entityName}ViewModel extends BaseViewModel {
	//UseCase

	@ViewModelInject
	public ${entityName}ViewModel() {
		init();
	}
	//LiveData

	//Normal Data

	//Getters, Setters

	private void init() {
	}
}
""".trimIndent()

fun createFragmentLayout(
		packageName: String,
		entityName: String) = """<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="${packageName}.${entityName}Fragment">
</androidx.constraintlayout.widget.ConstraintLayout>
""".trimIndent()
