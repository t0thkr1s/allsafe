package infosecadventures.allsafe.utils

import android.app.Activity
import com.google.android.material.snackbar.Snackbar
import infosecadventures.allsafe.R

object SnackUtil {

    fun simpleMessage(activity: Activity, message: String) {
        val s = Snackbar.make(activity.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        s.setAction(android.R.string.ok) { s.dismiss() }
        s.setBackgroundTint(activity.getColor(R.color.primaryColor))
        s.setTextColor(activity.getColor(R.color.white))
        s.setActionTextColor(activity.getColor(R.color.secondaryLightColor))
        s.show()
    }

    fun confirmExit(activity: Activity) {
        val s = Snackbar.make(activity.findViewById(android.R.id.content), "Press EXIT, to close the application.", Snackbar.LENGTH_SHORT)
        s.setAction("EXIT") { activity.finish() }
        s.setBackgroundTint(activity.getColor(R.color.primaryColor))
        s.setTextColor(activity.getColor(R.color.white))
        s.setActionTextColor(activity.getColor(R.color.secondaryLightColor))
        s.show()
    }
}
