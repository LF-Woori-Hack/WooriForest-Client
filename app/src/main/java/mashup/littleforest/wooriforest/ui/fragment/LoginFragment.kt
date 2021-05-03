package mashup.littleforest.wooriforest.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.tistory.blackjinbase.util.Dlog
import mashup.littleforest.wooriforest.R
import mashup.littleforest.wooriforest.WoorisForestApplication
import mashup.littleforest.wooriforest.base.WFFragment
import mashup.littleforest.wooriforest.data.model.request.LoginRequest
import mashup.littleforest.wooriforest.databinding.FragmentLoginBinding
import mashup.littleforest.wooriforest.utils.PrefUtil

class LoginFragment : WFFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    override var logTag = "LoginFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButton()

        //check home
        val isMain = PrefUtil.get(PrefUtil.PREF_USER_MAIN, false)
        if (isMain) {
            goToHome()
            return
        }

        //check auto login
        val accessToken = PrefUtil.get(PrefUtil.PREF_ACCESS_TOKEN, "")
        Dlog.d("accessToken : $accessToken")
        if (TextUtils.isEmpty(accessToken).not()) {
            goToHobbyCheck()
            return
        }
    }

    private fun initButton() {
        binding.btnKakaoLogin.setOnClickListener {
            //fetchTest { goToHobbyCheck() }
            //return@setOnClickListener
            kakaoLogin()
        }
    }

    private fun kakaoLogin() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Dlog.e("로그인 실패 : $error")
            } else if (token != null) {
                Dlog.d("로그인 성공 ${token.accessToken}")
                login(token.accessToken)
            }
        }

        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
            UserApiClient.instance.loginWithKakaoTalk(requireContext(), callback = callback)
        } else {
            //기본 웹 브라우저를 통해 카카오계정으로 로그인
            UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
        }
    }

    private fun login(snsToken: String) {
        val request = LoginRequest(
            deviceId = WoorisForestApplication.deviceUuid,
            snsToken = snsToken
        )

        fetch {
            val response = wooriApi.login(request)
            Dlog.d("response : $response")
            goToHobbyCheck()
        }
    }

    private fun goToHobbyCheck() {
        navigate(R.id.action_loginFragment_to_hobbyCheckFragment)
    }

    private fun goToHome() {
        navigate(R.id.action_global_homeFragment)
    }
}