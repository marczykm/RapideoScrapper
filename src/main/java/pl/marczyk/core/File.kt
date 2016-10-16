package pl.marczyk.core

import java.net.URL
import java.util.*

/**
 * Created by mm on 16.10.2016.
 */
data class File(
        var name: String) {
    var mainUrl: String? = null
    var backUpUrls: List<String>? = null
    var expirationDate: Date? = null
    var size: Long? = null
}
