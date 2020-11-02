package bindingadapter

import com.example.myjetpackdemo.R
import model.ProfileItem
import model.StudentItem

class StudentAdapter<T> : BaseDataBindingAdapter() {

    private val items: MutableList<StudentItem> = mutableListOf()

    var listener: StudentAdapter<T>? = null

    override fun getItemForPosition(position: Int): Any = items[position]

    override fun getListener(): Any? = listener

    override fun getLayoutIdForPosition(position: Int): Int = R.layout.student_item

    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int): Long {
        return 0
    }

    fun setItems(newItems: List<StudentItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun updateElement(index: Int, element: StudentItem) {
        items[index] = element
        notifyItemChanged(index)
    }

    interface StudentListener<T> {
        fun onStudentCellListClicked(item: T)
    }
}
