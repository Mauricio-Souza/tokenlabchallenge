package msousa.dev.tokenlab_challenge.presentation.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import kotlinx.android.synthetic.main.sticker_message_view.view.*
import msousa.dev.tokenlab_challenge.R
import java.lang.ref.WeakReference

class StickerMessageView(context: Context, attributeSet: AttributeSet?) : LinearLayout(context, attributeSet) {

    private lateinit var stickerImage: WeakReference<AppCompatImageView>
    private lateinit var stickerTitle: WeakReference<AppCompatTextView>
    private lateinit var stickerMessage: WeakReference<AppCompatTextView>

    private val theme = context.theme

    init {
        View.inflate(context,
            R.layout.sticker_message_view, this@StickerMessageView)
        bindViews()

        attributeSet?.let {
            theme.obtainStyledAttributes(attributeSet,
                R.styleable.StickerMessageView, 0, 0).apply {
                try {
                    setTextSize(stickerTitle.get()!!, getInt(R.styleable.StickerMessageView_title_size, 15))
                    setTextSize(stickerMessage.get()!!, getInt(R.styleable.StickerMessageView_message_size, 15))
                    setTitleColor(getResourceId(R.styleable.StickerMessageView_title_color, 0))
                    setMessageColor(getResourceId(R.styleable.StickerMessageView_message_color, 0))
                    setTitle(getString(R.styleable.StickerMessageView_sticker_title))
                    setTitle(getResourceId(R.styleable.StickerMessageView_sticker_title, 0))
                    setMessage(getString(R.styleable.StickerMessageView_sticker_message))
                    setMessage(getResourceId(R.styleable.StickerMessageView_sticker_message, 0))
                    setImage(getDrawable(R.styleable.StickerMessageView_sticker_image))
                    setImage(getResourceId(R.styleable.StickerMessageView_sticker_image, 0))
                    hideLayout(getBoolean(R.styleable.StickerMessageView_hide_layout, false))
                } finally {
                    recycle()
                }
            }
        }
    }

    private fun bindViews() {
        stickerImage = WeakReference(icon)
        stickerTitle = WeakReference(title)
        stickerMessage = WeakReference(message)
    }

    private fun hideLayout(hide: Boolean) {
        if (hide) layoutNoProposals.visibility = View.GONE
    }

    private fun setTextSize(textView: AppCompatTextView, textSize: Int) {
        if (textSize > 0) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize.toFloat())
        }
    }

    fun setImage(resId: Int) {
        if (resId > 0)
            stickerImage.get()?.setImageResource(resId)
    }

    fun setImage(drawable: Drawable?) {
        drawable?.let {
            stickerImage.get()?.setImageDrawable(it)
        }
    }

    fun setTitle(title: String?) {
        title?.let {
            stickerTitle.get()?.setText(it)
        }
    }

    fun setTitle(title: Int) {
        if (title > 0) {
            stickerTitle.get()?.setText(title)
        }
    }

    fun setMessage(message: String?) {
        message?.let {
            stickerMessage.get()?.setText(it)
        }
    }

    fun setMessage(message: Int) {
        if (message > 0)
            stickerMessage.get()?.setText(message)
    }

    fun setTitleColor(colorRes: Int) {
        if (colorRes > 0)
            stickerTitle.get()?.setTextColor(colorRes)
    }

    fun setMessageColor(colorRes: Int) {
        if (colorRes > 0)
            stickerMessage.get()?.setTextColor(colorRes)
    }
}