package infosecadventures.allsafe.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import infosecadventures.allsafe.BuildConfig
import infosecadventures.allsafe.Constants
import infosecadventures.allsafe.R
import infosecadventures.allsafe.utils.ClipUtil
import infosecadventures.allsafe.utils.SnackUtil
import kotlinx.android.synthetic.main.fragment_about.view.*

class About : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)
        view.version.text = "Version: ${BuildConfig.VERSION_NAME}"
        view.blog.setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(Constants.MEDIUM))) }
        view.github.setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(Constants.GITHUB))) }
        view.twitter.setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(Constants.TWITTER))) }
        view.bitcoin.setOnClickListener {
            ClipUtil.copyToClipboard(requireActivity(), Constants.BTC_ADDRESS)
            SnackUtil.simpleMessage(requireActivity(), "Bitcoin address copied to clipboard!")
        }
        view.ethereum.setOnClickListener {
            ClipUtil.copyToClipboard(requireActivity(), Constants.ETH_ADDRESS)
            SnackUtil.simpleMessage(requireActivity(), "Ethereum address copied to clipboard!")
        }
        return view
    }
}