package infosecadventures.allsafe.utils

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import infosecadventures.allsafe.R

object DialogUtil {

    fun simpleAlert(activity: Activity, title: String, message: String) {
        AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setIcon(R.mipmap.ic_launcher_round)
                .setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
                .show()
    }

    fun confirmExit(activity: Activity) {
        AlertDialog.Builder(activity)
                .setTitle("Confirm exit")
                .setMessage("Are you sure want to leave the application?")
                .setIcon(R.mipmap.ic_launcher_round)
                .setPositiveButton(android.R.string.ok) { _, _ -> activity.finish() }
                .setNegativeButton(android.R.string.cancel) { dialog, _ -> dialog.dismiss() }
                .show()
    }
}
