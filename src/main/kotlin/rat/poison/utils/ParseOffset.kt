package rat.poison.utils

import java.net.URL
import javax.net.ssl.HttpsURLConnection
import kotlin.reflect.KProperty

class ParseOffset {
    private var cached = -1
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        if (cached != -1) return cached
        try {
            val connection = URL("https://raw.githubusercontent.com/frk1/hazedumper/master/csgo.json") //fuck you
            val con = connection.openConnection() as HttpsURLConnection
            val text = connection.readText()
            con.disconnect()

            //this obviously is not a perfect solution
            val lines = text.split("\n")
            lines.forEach { line ->
                if ("dwbSendPackets" in line) {
                    val regex = "\\d+".toRegex()
                    val offset = regex.find(line)!!.value.toInt()
                    cached = offset
                    return offset
                }
            }
        }
        catch (e: Exception) { }
        return -1
    }
}
