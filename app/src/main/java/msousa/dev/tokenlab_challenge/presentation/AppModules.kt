package msousa.dev.tokenlab_challenge.presentation

import msousa.dev.tokenlab_challenge.data.di.DataModule
import msousa.dev.tokenlab_challenge.domain.di.DomainModule
import msousa.dev.tokenlab_challenge.presentation.common.ModulePackage
import msousa.dev.tokenlab_challenge.presentation.di.PresentationModule
import org.koin.core.module.Module

object AppModules : ModulePackage {
    override fun getModules(): List<Module> = listOf(
        DataModule.module,
        DomainModule.module,
        PresentationModule.module
    )
}