package fr.o80.androidstuff.rx;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.operators.single.SingleToObservable;

public final class RxFilter {

    private RxFilter() {
    }

    @NonNull
    public static <T> SingleTransformer<List<T>, List<T>> filterListSingle(final Predicate<T> filter) {
        return new SingleTransformer<List<T>, List<T>>() {
            @Override
            public SingleSource<List<T>> apply(Single<List<T>> upstream) {
                return upstream
                        .flatMap(new Function<List<T>, SingleSource<List<T>>>() {
                            @Override
                            public SingleSource<List<T>> apply(List<T> list) throws Exception {
                                return Observable.fromIterable(list)
                                        .filter(filter)
                                        .toList();
                            }
                        });
            }
        };
    }

    @NonNull
    public static <T> ObservableTransformer<List<T>, List<T>> filterListObservable(final Predicate<T> filter) {
        return new ObservableTransformer<List<T>, List<T>>() {
            @Override
            public ObservableSource<List<T>> apply(Observable<List<T>> upstream) {
                return new SingleToObservable<>(
                        upstream
                                .flatMap(new Function<List<T>, ObservableSource<T>>() {
                                    @Override
                                    public ObservableSource<T> apply(List<T> list) throws Exception {
                                        return Observable.fromIterable(list)
                                                .filter(filter);
                                    }
                                })
                                .toList()
                );
            }
        };
    }
}
