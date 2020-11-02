package contract

interface BaseContract {

    interface Presenter<V : View> {

        fun attachView(view: V)

        fun detachView()
    }

    interface View
}
