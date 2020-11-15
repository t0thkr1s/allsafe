package infosecadventures.allsafe

import android.app.Application


class ArbitraryCodeExecution : Application() {

    override fun onCreate() {
        super.onCreate()
        invokePlugins()
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
}