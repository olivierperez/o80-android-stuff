package fr.o80.androidstuff.mvp;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author Olivier Perez
 */
public abstract class BasePresenter<T extends BaseView> {

    protected final CompositeDisposable subscriptions = new CompositeDisposable();

    protected T view;

    public void attachView(T view) {
        this.view = view;
    }

    public void detachView() {
        subscriptions.dispose();
        view = null;
    }

    protected void addDisposable(Disposable disposable) {
        subscriptions.add(disposable);
    }
}
