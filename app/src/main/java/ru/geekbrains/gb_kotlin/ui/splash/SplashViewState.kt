package ru.geekbrains.gb_kotlin.ui.splash

import ru.geekbrains.gb_kotlin.ui.base.BaseViewState

class SplashViewState(isAuth: Boolean? = null, error: Throwable? = null) :
    BaseViewState<Boolean?>(isAuth, error)