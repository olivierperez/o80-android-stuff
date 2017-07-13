package fr.o80.o80androidstuff

import fr.o80.androidstuff.mvp.BaseView

/**
 * @author Olivier Perez
 */
interface MainView : BaseView {
    fun setText(content: String)
}