package infosecadventures.allsafe.challenges

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.scottyab.rootbeer.RootBeer
import infosecadventures.allsafe.R
import infosecadventures.allsafe.utils.DialogUtil

class RootDetection : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_root_detection, container, false)
        view.findViewById<View>(R.id.check).setOnClickListener {
            if (RootBeer(context).isRooted) {
                DialogUtil.simpleAlert(requireActivity(), "Access Denied", "It looks like your device is rooted!")
            } else {
                DialogUtil.simpleAlert(requireActivity(), "Access Granted", "Good job!")
            }
        }
        return view
    }
}