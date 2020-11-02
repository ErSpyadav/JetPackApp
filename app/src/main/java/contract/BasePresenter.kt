package contract

import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V> {

    var view: WeakReference<V>? = null
    val compositeDisposable by lazy { CompositeDisposable() }

    override fun attachView(view: V) {
        this.view = WeakReference(view)
    }

    override fun detachView() {
        compositeDisposable.clear()
        view?.clear()
        view = null
    }

    protected fun getView() = view?.get()
}
