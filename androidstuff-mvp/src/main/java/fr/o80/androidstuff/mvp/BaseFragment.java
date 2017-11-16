package fr.o80.androidstuff.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

/**
 * @author Olivier Perez
 */
public abstract class BaseFragment extends Fragment implements BaseView {

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container,
                                   Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResource(), container, false);
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getPresenter() != null) {
            getPresenter().attachView(this);
        }
    }

    @Override
    public void onDestroyView() {
        if (getPresenter() != null) {
            getPresenter().detachView();
        }
        View view = getView();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        super.onDestroyView();
    }

    @LayoutRes
    protected abstract int getLayoutResource();

    protected abstract BasePresenter getPresenter();
}
