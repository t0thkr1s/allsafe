package infosecadventures.allsafe.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

object ClipUtil {
    fun copyToClipboard(context: Context, text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip: ClipData = ClipData.newPlainText("result", text)
        clipboard.setPrimaryClip(clip)
    }
}