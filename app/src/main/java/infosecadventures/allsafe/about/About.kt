package infosecadventures.allsafe.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import infosecadventures.allsafe.Constants
import infosecadventures.allsafe.R
import infosecadventures.allsafe.utils.ClipUtil
import infosecadventures.allsafe.utils.SnackUtil
import androidx.core.net.toUri

class About : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)
        val version = "Version: ${requireContext().applicationContext.packageManager.getPackageInfo(requireContext().applicationContext.packageName, 0).versionName}"
        view.findViewById<TextView>(R.id.version).text = version
        view.findViewById<LinearLayout>(R.id.blog).setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW,
            Constants.MEDIUM.toUri())) }
        view.findViewById<LinearLayout>(R.id.github).setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW,
            Constants.GITHUB.toUri())) }
        view.findViewById<LinearLayout>(R.id.x).setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW,
            Constants.X.toUri())) }
        view.findViewById<LinearLayout>(R.id.bitcoin).setOnClickListener {
            ClipUtil.copyToClipboard(requireActivity(), Constants.BTC_ADDRESS)
            SnackUtil.simpleMessage(requireActivity(), "Bitcoin address copied to clipboard!")
        }
        view.findViewById<LinearLayout>(R.id.ethereum).setOnClickListener {
            ClipUtil.copyToClipboard(requireActivity(), Constants.ETH_ADDRESS)
            SnackUtil.simpleMessage(requireActivity(), "Ethereum address copied to clipboard!")
        }
        return view
    }
}