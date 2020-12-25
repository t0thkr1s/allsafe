package infosecadventures.allsafe

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import infosecadventures.allsafe.utils.SnackUtil

class MainActivity : AppCompatActivity() {

    private var mAppBarConfiguration: AppBarConfiguration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE)

        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        mAppBarConfiguration = AppBarConfiguration.Builder(
                R.id.nav_main,
                R.id.nav_search,
                R.id.nav_insecure_logging,
                R.id.nav_hardcoded_credentials,
                R.id.nav_sql_injection,
                R.id.nav_deep_link_exploitation,
                R.id.nav_weak_crypto,
                R.id.nav_certificate_pinning,
                R.id.nav_pin_bypass,
                R.id.nav_root_detection,
                R.id.nav_secure_flag_bypass,
                R.id.nav_vulnerable_web_view,
                R.id.nav_smali_patch,
                R.id.nav_native_library,
                R.id.nav_about)
                .setOpenableLayout(drawer)
                .build()
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration!!)
        NavigationUI.setupWithNavController(navigationView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        return (NavigationUI.navigateUp(navController, mAppBarConfiguration!!)
                || super.onSupportNavigateUp())
    }

    override fun onBackPressed() {
        when {
            mAppBarConfiguration?.openableLayout?.isOpen!! -> mAppBarConfiguration!!.openableLayout?.close()
            supportFragmentManager.backStackEntryCount > 1 -> supportFragmentManager.popBackStack()
            else -> SnackUtil.confirmExit(this)
        }
    }
}