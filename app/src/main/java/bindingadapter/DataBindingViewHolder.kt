package bindingadapter

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import extension.toCurrencyFormat
import utils.CircleTransform
import utils.RecipientImage
import utils.TransactionImage
import androidx.databinding.library.baseAdapters.BR

class DataBindingViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Any, listener: Any?) {
        binding.setVariable(BR.item, item)
        binding.setVariable(BR.listener, listener)
        binding.executePendingBindings()
            }

    companion object {
        private const val DECIMAL_REDUCTION_RATIO: Float = 0.78f

        @BindingAdapter("srcCompat")
        @JvmStatic
        fun setImage(imageView: ImageView, resourceId: Int?) {
            if (resourceId != null) {
                val drawable = AppCompatResources.getDrawable(imageView.context, resourceId)
                imageView.setImageDrawable(drawable)
            } else {
                imageView.setImageResource(0)
            }
        }

        @BindingAdapter("background")
        @JvmStatic
        fun setBackground(view: View, resourceId: Int?) {
            if (resourceId != null) {
                view.setBackgroundResource(resourceId)
            } else {
                view.setBackgroundResource(0)
            }
        }

        @BindingAdapter("colorText")
        @JvmStatic
        fun setTextColor(textView:TextView,resourceId:Int?){
            if(resourceId!=null){
                textView.setTextColor(ContextCompat.getColor(textView.context,resourceId))
            }
            else{
                textView.setTextColor(0)
            }
        }

        @BindingAdapter("quantity")
        @JvmStatic
        fun setQuantity(textView: TextView, quantity: DashboardQuantity?) {
            if (quantity != null) {
                val formatted = quantity.value.toCurrencyFormat(quantity.currency, quantity.symbol)

                // Calculates the decimal part font size from the integer part font size
                // Integer part must be 18sp and decimal part 14sp, so 14sp/18sp gives 0.7 periodic, saved
                // to DECIMAL_REDUCTION_RATIO constant
                val desiredSize = (textView.textSize * DECIMAL_REDUCTION_RATIO).toInt()

                val builder = SpannableStringBuilder(formatted)
                if (formatted.length > 2) {
                    builder.setSpan(
                        AbsoluteSizeSpan(desiredSize),
                        formatted.length - 2,
                        formatted.length,
                        0
                    )
                }

                textView.text = builder
            } else {
                textView.text = ""
            }
        }

        @BindingAdapter("circleImageUrl")
        @JvmStatic
        fun setCircleImageUrl(imageView: ImageView,url: String?){
            if(url!=null){
               Picasso.get()
                   .load(url)
                   .noFade()
                   .transform(CircleTransform())
                   .into(imageView)
            }
        }

        @BindingAdapter("imageUrl")
        @JvmStatic
        fun setImage(imageView: ImageView,url: String?){
            if(!url.isNullOrEmpty()){
                Picasso.get()
                    .load(url)
                    .noFade()
                    .transform(CircleTransform())
                    .into(imageView)
            }
        }

        @BindingAdapter("circleImage")
        @JvmStatic
        fun setCircleImage(imageView: ImageView, image: TransactionImage?) {
            image?.let {
                if (!it.imageUrl.isNullOrEmpty()) {
                    Picasso.get()
                        .load(it.imageUrl)
                        .noFade()
                        .transform(CircleTransform())
                        .into(imageView)
                } else if (it.drawable != null) {
                    imageView.setImageDrawable(it.drawable)
                }
            }
        }

        @BindingAdapter("circleImage")
        @JvmStatic
        fun setCircleImage(imageView: ImageView, image: RecipientImage?) {
            image?.let {
                if (!it.imageUrl.isNullOrEmpty()) {
                    Picasso.get()
                        .load(it.imageUrl)
                        .noFade()
                        .transform(CircleTransform())
                        .into(imageView)
                } else if (it.drawable != null) {
                    imageView.setImageDrawable(it.drawable)
                }
            }
        }

        @BindingAdapter("android:typeface")
        @JvmStatic
        fun setTypeface(textView: TextView, style: String) {
            when (style) {
                "bold" -> textView.setTypeface(null, Typeface.BOLD)
                else -> textView.setTypeface(null, Typeface.NORMAL)
            }
        }

        @JvmStatic
        @BindingAdapter("isBold")
        fun setTypeface(view: TextView, isBold: Boolean) {
            if (isBold) {
                view.setTypeface(view.typeface, Typeface.BOLD)
            } else {
                view.setTypeface(view.typeface, Typeface.NORMAL)
            }
        }






    }
}