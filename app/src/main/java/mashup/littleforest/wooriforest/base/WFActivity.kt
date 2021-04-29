package mashup.littleforest.wooriforest.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.tistory.blackjinbase.base.BaseActivity
import com.tistory.blackjinbase.ui.LoadingDialog

abstract class WFActivity<B : ViewDataBinding>(
    @LayoutRes private val layoutId: Int
) : BaseActivity<B>(layoutId), BaseUi {

    private val loadingDialog by lazy {
        LoadingDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onViewModelSetup()
    }

    override fun onViewModelSetup() {}

    override fun showLoadingDialog() {
        loadingDialog.show()
    }

    override fun hideLoadingDialog() {
        loadingDialog.dismiss()
    }
}