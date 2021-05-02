package mashup.littleforest.wooriforest.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.tistory.blackjinbase.base.BaseFragment
import com.tistory.blackjinbase.ui.LoadingDialog
import com.tistory.blackjinbase.util.Dlog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mashup.littleforest.wooriforest.data.ApiProvider

abstract class WFFragment<B : ViewDataBinding>(
    @LayoutRes private val layoutId: Int
) : BaseFragment<B>(layoutId), BaseUi {

    val wooriApi by lazy { ApiProvider.provideWooriApi() }

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

    fun navigate(directions: NavDirections) {
        findNavController().navigate(directions)
    }

    fun fetchTest(listener: () -> Unit) {
        lifecycleScope.launch {
            try {
                showLoadingDialog()
                delay(1000)
                hideLoadingDialog()
                listener.invoke()
            } catch (e: Exception) {
                Dlog.e(e.message)
                hideLoadingDialog()
            }
        }
    }

    fun fetch(listener: suspend () -> Unit) {
        lifecycleScope.launch {
            try {
                showLoadingDialog()
                listener.invoke()
                hideLoadingDialog()
            } catch (e: Exception) {
                Dlog.e(e.message)
                hideLoadingDialog()
            }
        }
    }
}