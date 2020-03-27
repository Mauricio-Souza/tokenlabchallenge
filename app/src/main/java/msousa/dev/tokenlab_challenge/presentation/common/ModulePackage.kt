package msousa.dev.tokenlab_challenge.presentation.common

import org.koin.core.module.Module

interface ModulePackage {
    fun getModules(): List<Module>
}