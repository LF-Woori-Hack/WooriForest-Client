package mashup.littleforest.wooriforest.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.findNavController
import com.tistory.blackjinbase.base.BaseFragment
import com.tistory.blackjinbase.ui.LoadingDialog

abstract class WFFragment<B : ViewDataBinding>(
    @LayoutRes private val layoutId: Int
) : BaseFragment<B>(layoutId), BaseUi {

    private val loadingDialog by lazy {
        LoadingDialog(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewModelSetup()
    }

    override fun onViewModelSetup() {}

    override fun showLoadingDialog() {
        loadingDialog.show()
    }

    override fun hideLoadingDialog() {
        loadingDialog.dismiss()
    }

    fun navigate(resId: Int) {
        findNavController().navigate(resId)
    }
}