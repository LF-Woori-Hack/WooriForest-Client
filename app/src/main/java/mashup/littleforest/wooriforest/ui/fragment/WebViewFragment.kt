package mashup.littleforest.wooriforest.ui.fragment

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import mashup.littleforest.wooriforest.R
import mashup.littleforest.wooriforest.base.WFFragment
import mashup.littleforest.wooriforest.databinding.FragmentWebViewBinding

class WebViewFragment : WFFragment<FragmentWebViewBinding>(R.layout.fragment_web_view) {

    override var logTag = "WebViewFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebView()
        initButton()
    }

    private fun initWebView() {
        with(binding.webview) {
            with(settings) {
                setSupportMultipleWindows(false) // 새창 띄우기 허용 여부
                setSupportZoom(false) // 화면 줌 허용 여부

                cacheMode = WebSettings.LOAD_DEFAULT // 브라우저 캐시 허용 여부
                javaScriptEnabled = true // 웹페이지 자바스클비트 허용 여부
                javaScriptCanOpenWindowsAutomatically = false; // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
                loadWithOverviewMode = true // 메타태그 허용 여부
                useWideViewPort = true // 화면 사이즈 맞추기 허용 여부
                builtInZoomControls = false; // 화면 확대 축소 허용 여부
                domStorageEnabled = true // 로컬저장소 허용 여부
            }

            webChromeClient = WebChromeClient()
            val url = "https://m.wooribank.com/mw/mws?withyou=MWPRD0005&AR_CD=001#AR_CD=002&PLM_PDCD=P010002353&SEL_PRD_YN=Y"
            loadUrl(url)
        }
    }

    private fun initButton() {
        binding.btnRegisterPocketMoney.setOnClickListener {
            navigate(R.id.action_webViewFragment_to_pocketMoneyRegisterFragment)
        }
    }
}