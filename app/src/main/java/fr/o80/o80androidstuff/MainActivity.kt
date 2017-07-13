package fr.o80.o80androidstuff

import android.os.Bundle
import fr.o80.androidstuff.mvp.BaseActivity
import fr.o80.androidstuff.mvp.BasePresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainView {

    val presenter = MainPresenter()

    override fun getPresenter(): BasePresenter<*> = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.init()
    }

    override fun setText(content: String) {
        myTextView.text = content
    }
}