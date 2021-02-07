package infosecadventures.allsafe

import android.app.Application
import android.os.Build
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import dalvik.system.DexClassLoader
import java.io.File


class ArbitraryCodeExecution : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        invokePlugins()
        invokeUpdate()
    }

    private fun invokePlugins() {
        for (packageInfo in packageManager.getInstalledPackages(0)) {
            val packageName: String = packageInfo.packageName
            if (packageName.startsWith("infosecadventures.allsafe")) {
                try {
                    val packageContext = createPackageContext(packageName,
                            CONTEXT_INCLUDE_CODE or CONTEXT_IGNORE_SECURITY)
                    packageContext.classLoader
                            .loadClass("infosecadventures.allsafe.plugin.Loader")
                            .getMethod("loadPlugin")
                            .invoke(null)
                } catch (ignored: Exception) {
                }
            }
        }
    }

    private fun invokeUpdate() {
        try {
            val file = File(Environment.DIRECTORY_DOWNLOADS + "/allsafe_updater.apk")
            if (file.exists() && file.isFile) {
                val dexClassLoader = DexClassLoader(file.absolutePath, cacheDir.absolutePath, null, classLoader)
                val version = dexClassLoader.loadClass("infosecadventures.allsafe.updater.VersionCheck")
                        .getDeclaredMethod("getLatestVersion")
                        .invoke(null) as Int
                if (Build.VERSION.SDK_INT < version) {
                    Toast.makeText(this, "Update required!", Toast.LENGTH_LONG).show()
                }
            }
        } catch (ignored: Exception) {
        }
    }
}