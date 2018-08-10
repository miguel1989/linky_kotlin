package linky.api

import org.apache.tomcat.util.codec.binary.Base64
import java.nio.charset.StandardCharsets

abstract class BaseApi {
    var localUrl = ""
//    fun setLocalUrl(url: String) {
//        this.localUrl = url
//    }

    fun buildBasicAuth(email: String, pass: String): String {
        val auth = "$email:$pass"
        val encodedAuth = Base64.encodeBase64(auth.toByteArray(StandardCharsets.UTF_8))
        val str = String(encodedAuth)
        return "Basic $str"
    }
}