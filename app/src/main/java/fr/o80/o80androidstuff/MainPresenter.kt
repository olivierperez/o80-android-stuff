package fr.o80.o80androidstuff

import fr.o80.androidstuff.mvp.BasePresenter

/**
 * @author Olivier Perez
 */
class MainPresenter : BasePresenter<MainView>() {
    fun init() {
        view.setText("It works!")
    }
}