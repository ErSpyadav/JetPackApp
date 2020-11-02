package bindingadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseDataBindingAdapter : RecyclerView.Adapter<DataBindingViewHolder>() {
    protected var binding :ViewDataBinding?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder {
     val inflater:LayoutInflater = LayoutInflater.from(parent.context)
        binding =DataBindingUtil.inflate(inflater,viewType,parent, false)
        return DataBindingViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
       val item =getItemForPosition(position)
        holder.bind(item,getListener())
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    protected abstract fun getItemForPosition(position: Int): Any

    protected abstract fun getListener(): Any?

    protected abstract fun getLayoutIdForPosition(position: Int): Int
}
