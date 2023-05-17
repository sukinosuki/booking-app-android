package com.example.bookingapp.ui.main.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bookingapp.R
import com.example.bookingapp.databinding.FragmentHomeAreaBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

//class BottomSheet : BottomSheetDialogFragment() {
class HomeAreaBottomSheetFragment(private val onItemClickListener: OnItemClickListener) :
    BottomSheetDialogFragment() {

    private lateinit var binding: FragmentHomeAreaBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 设置styles.xml定义的样式，目的是去除bottomSheetDialogFragment的默认白色背景
        // refer: https://stackoverflow.com/questions/62100100/how-can-i-make-the-corners-of-my-bottom-sheet-dialog-rounded
        // refer: https://www.bilibili.com/video/BV1ce411T714/?spm_id_from=333.788.top_right_bar_window_history.content.click&vd_source=8ff6b441a219d24f755c45001bf375ec
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeAreaBottomSheetBinding.inflate(inflater)

        return binding.root
    }

    fun interface OnItemClickListener {
        fun onClick(text: String, text2: String)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.downloadText.setOnClickListener {
            // TODO: ?
            dismissAllowingStateLoss()
            onItemClickListener.onClick("download", "param2")

            onItemClickListener2?.onClick("download2", "param2")
            Log.i("hanami", "initView: 点击了download $onItemClickListener2")
            onItemClick("download3")

        }
        binding.shareText.setOnClickListener {
            dismissAllowingStateLoss()
            onItemClickListener.onClick("share", "param2")
            Log.i("hanami", "initView: 点击了share $onItemClickListener2")
            onItemClickListener2?.onClick("share2", "param2")

            onItemClick("share3")
        }
    }

//    val setOnClickListener = { text: String -> Unit }

    private var onItemClickListener2: OnItemClickListener? = null
//    var onItemClickListener2: OnItemClickListener? = object : OnItemClickListener {
//        override fun onClick(text: String) {
//
//        }
//
//    }

    fun setOnClickListener(l: OnItemClickListener) {
        onItemClickListener2 = l
    }

    //    fun setOnClickListener2(l: OnItemClickListener) = OnItemClickListener {
//        l.onClick(it)
//    }
    fun setOnClickListener2(l: (String, String) -> Unit) {
        onItemClickListener2 = OnItemClickListener { text, text2 -> l(text, text2) }
    }

    var onItemClick: (String) -> Unit = {

    }

//    fun setOnClickListener(f: (String) -> Unit) {
//        onItemClickListener2 = object : OnItemClickListener {
//            override fun onClick(text: String) {
//                f(text)
//            }
//        }
//    }

//    fun setOnClickListener2(l: (String) -> Unit) {
//        onItemClick = l
//    }

    companion object {
        @JvmStatic
        fun newInstance(onItemClickListener: OnItemClickListener): HomeAreaBottomSheetFragment {
            return HomeAreaBottomSheetFragment(onItemClickListener)
        }
    }
}
