package bindingadapter

import com.example.myjetpackdemo.R
import model.ProfileItem

class ProfileAdapter<T> : BaseDataBindingAdapter() {

    private val items: MutableList<ProfileItem> = mutableListOf()

    var listener: ProfileListener<T>? = null

    override fun getItemForPosition(position: Int): Any = items[position]

    override fun getListener(): Any? = listener

    override fun getLayoutIdForPosition(position: Int): Int = R.layout.item_profile_layout

    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int): Long {
        return 0
    }

    fun setItems(newItems: List<ProfileItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun updateElement(index: Int, element: ProfileItem) {
        items[index] = element
        notifyItemChanged(index)
    }

    interface ProfileListener<T> {
        fun onProfileCellListClicked(item: T)
        fun onRightButtonClick(item: T)
    }
}
