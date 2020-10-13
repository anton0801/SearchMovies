package app.beer.searchmovies.utils

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.Toast
import app.beer.searchmovies.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.row_add_item.*
import kotlinx.android.synthetic.main.row_add_item.view.*

class BottomSheet() : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.row_add_item, container, false)

        val buttonAdd = view.add_item_btn
        buttonAdd.setOnClickListener {
            val text = text_input_layout.editText?.text.toString()
            if (!TextUtils.isEmpty(text)) {
                dismiss()
            } else {
                Toast.makeText(context, "Введите название item'а", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return super.onCreateAnimation(transit, enter, nextAnim)
    }

}